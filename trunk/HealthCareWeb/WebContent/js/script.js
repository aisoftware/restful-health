//global variable go shoot yourself
//DOM READY loop
$(function() {
	$(".hidden").removeClass("hidden");
	loadPatients();
	$('html').quickTabs();
	//login
	if($('body#login').exists()) {
		$('form .btn').bind('click', function() {
			var data = {};
			data.username=$('#usrName').val();
			data.password=$('#usrPassword').val();
			$.ajax({
				url:'http://localhost:8080/healthcare/login',
				data:data
			});
		});
	}
});



var selectPatient = function(patientData) {
	$('#patientBrowser').hide();
	$('#patientMain').show();
	currentPatient = patientData;
	renderOverView();
	$('#reset').click();
};

var initializePatientView = function() {
	$('#patientBrowser').show();
	$('#patientMain').hide();
	$(".hidden").removeClass("hidden");
	$('#patientWrapper').show();
	$('#patientWrapper').html('<table class="table" id="patient-list"></table>');
	loadPatients();
};

var initializeList = function() {
	
	
};

var loadPatient = function(pid) {
	$.ajax({
        type: 'GET',
        url: 'http://localhost:8080/healthcare/service/labReport/patient/'+pid,
        success: function(data) {
        	selectPatient(JSON.parse(data));
        }
    });
};

var loadPatients = function() {
	$.ajax({
        type: 'GET',
        url: 'http://localhost:8080/healthcare/service/patient/list',
        success: function(data) {
        	renderPatients(JSON.parse(data));
        }
    });
};

var retrievePatientName = function() {
	var fullName;
	if(typeof currentPatient.recordTarget.patientRole.patient.name.given === "object") {
		fullName = currentPatient.recordTarget.patientRole.patient.name.given.join()+" "+currentPatient.recordTarget.patientRole.patient.name.family;
	}else{
		fullName = currentPatient.recordTarget.patientRole.patient.name.given+" "+currentPatient.recordTarget.patientRole.patient.name.family
	};
	return fullName;
};

var retrievePayerObject = function() {
	var structure = currentPatient.component.structuredBody.component;
	for (var i=0;i<structure.length;i++) {
		if(structure[i].section!==undefined) {
			if(structure[i].section.title==="Payers Section") {
				return structure[i];
			}
		}
	};
};
var retrieveVitalsObject = function() {
	var structure = currentPatient.component.structuredBody.component;
	for (var i=0;i<structure.length;i++) {
		if(structure[i].section!==undefined) {
			if(structure[i].section.title==="Vital Signs") {
				return structure[i];
			}
		}
	};
};
var retrieveLabsObject = function() {
	var structure = currentPatient.component.structuredBody.component;
	for (var i=0;i<structure.length;i++) {
		if(structure[i].section!==undefined) {
			if(structure[i].section.title==="Results") {
				return structure[i];
			}
		}
	};
};

var parseLabResults = function() {
	var labArray = retrieveLabsObject().section;
	var labResults=[];
	
	if(labArray.entry.length!==undefined) {
		for (var i=0;i<labArray.entry.length;i++) {
			//parent in here
			var drivEntry = labArray.entry[i];
			labResults[i] = [];
			for (var j=1;j<labArray.entry[i].organizer.component.length;j++) {
				//timestamp in here
				var observation = labArray.entry[i].organizer.component[j].observation;
				labResults[i][0]=new Date(observation.effectiveTime.value.substring(0,4),observation.effectiveTime.value.substring(4,6),observation.effectiveTime.value.substring(6,8));
					
				if(observation.code.code=='#LYMPH') {
					labResults[i][1] = observation.value['#text'];
				};
				
				if(observation.code.code=='#WBC') {
					labResults[i][2] = observation.value['#text'];
				};
			};
		};
	}else{
		//sometimes the entry is an object instead of array fix here later
	}
	
	return labResults;
};

var parseLabResultsWithRange = function() {
	var labArray = retrieveLabsObject().section;
	var labResults=[];
	
	if(labArray.entry.length!==undefined) {
		for (var i=0;i<labArray.entry.length;i++) {
			//parent in here
			var drivEntry = labArray.entry[i];
			labResults[i] = [];
			for (var j=1;j<labArray.entry[i].organizer.component.length;j++) {
				//timestamp in here
				var observation = labArray.entry[i].organizer.component[j].observation;
				labResults[i][0]=new Date(observation.effectiveTime.value.substring(0,4),observation.effectiveTime.value.substring(4,6),observation.effectiveTime.value.substring(6,8));
					
				if(observation.code.code=='#LYMPH') {
					labResults[i][1] = {
						high:observation.referenceRange.observationRange.value.high.value,
						observed:observation.value['#text'],
						low:observation.referenceRange.observationRange.value.low.value,
						unit:observation.referenceRange.observationRange.value.high.unit
					};
				};
				
				if(observation.code.code=='#WBC') {
					labResults[i][2] = {
						high:observation.referenceRange.observationRange.value.high.value,
						observed:observation.value['#text'],
						low:observation.referenceRange.observationRange.value.low.value,
						unit:observation.referenceRange.observationRange.value.high.unit
					};
				};
			};
		};
	}else{
		//sometimes the entry is an object instead of array fix here later
	}
	
	return labResults;
};

var parseVitalSigns = function() {
	var vitalsArray = retrieveVitalsObject().section;
	if(vitalsArray.entry.length!==undefined) {
		for (var i=0;i<1;i++) {
			//parent in here
			var batteryEntry = vitalsArray.entry[i];
			
			for (var j=0;j<vitalsArray.entry[i].organizer.component.length;j++) {
				//timestamp in here
				var observation = vitalsArray.entry[i].organizer.component[j];
			};
		};
	}else{
		//sometimes the entry is an object instead of array fix here later
	}
	
};

//Overview is rendered on patient init always
var renderOverView = function() {
	//name
	var patientOverview = currentPatient.recordTarget.patientRole;
	var patientBirthday = retrieveBirthday();
	$("#pName").html(retrievePatientName());
	if(typeof patientOverview.patient.name.given === "object") {
		$('#fname').html(patientOverview.patient.name.given[0]);
		$('#mname').html(patientOverview.patient.name.given[1]);
	}else{
		patientOverview.patient.name.given;
		$('#mname').addClass("hidden");
	};
	$("#lname").html(patientOverview.patient.name.family);
	//id
	$("#pid").html(patientOverview.id.root);
	$("#pidext").html(patientOverview.id.extension);
	//birthday
	
	$("#bday").html(renderBirthday());
	$("#age").html(getAge(retrieveBirthday()));
	//gender
	$("#gender").html(patientOverview.patient.administrativeGenderCode.displayName);
	
	//address
	$("#addcity").html(patientOverview.addr.city);
	$("#addstate").html(patientOverview.addr.state);
	$("#addzip").html(patientOverview.addr.postalCode);
	$("#addstreet").html(patientOverview.addr.streetAddressLine);
	
	//telephone
	$("#wp").html(currentPatient.recordTarget.patientRole.telecom[0].value.substring(5));
	$("#hp").html(currentPatient.recordTarget.patientRole.telecom[1].value.substring(5));
};

var renderPayer = function() {
	var payerArray = parsePayers();
	$("#payer").html('<div class="hero-unit"><table cellpadding="0" cellspacing="0" border="0" class="display table" id="payers"></table></div>');
	$("#payers").dataTable( {
        "aaData": payerArray,
	       "aoColumns": [
	           { "sTitle": "Payer" },
	           { "sTitle": "Plan Name","sClass": "center"  },
	           { "sTitle": "Group ID","sClass": "center"  },
	           { "sTitle": "Member ID", "sClass": "center" },
	           { "sTitle": "Coverage Start Date", "sClass": "center" },
	           { "sTitle": "Coverage End Date", "sClass": "center" }
	       ]
   });
};

var renderPatients = function(data) {
	var patients = data;
	var patientArray = [];
	for (var i=0;i<patients.length;i++) {
		patientArray[i]=[];
		patientArray[i][0]=patients[i].PersonJSON.name.name.family;
		patientArray[i][1]=patients[i].PersonJSON.name.name.given;
		patientArray[i][2]=patients[i].PersonJSON.gender;
		patientArray[i][3]=getAge(new Date(patients[i].PersonJSON.birthDate));
		patientArray[i][4]=patients[i].personID;
	}
	
	var patientsTable = $("#patient-list").dataTable( {
		"aaData": patientArray,
		"aoColumns": [
	           { "sTitle": "Family Name" },
	           { "sTitle": "First Name","sClass": "center" },
	           { "sTitle": "Gender","sClass": "center", "bSortable":false  },
	           { "sTitle": "Age","sClass": "center" },
	           { "sTitle": "pid","sClass": "hidden" }
       ]
	});
	
	$('#patient-list tbody tr').click(function() {
		loadPatient($(this).find('.hidden').html());
	});
};

var renderLabs = function() {
	var labsArray = parseLabResults();
	var unitlabsArray = parseLabResultsWithRange();
	$("#lab-result .hero-unit.datatable").html('<table cellpadding="0" cellspacing="0" border="0" class="display table" id="lab-results"></table');
	var labsTables = $("#lab-results").dataTable( {
        "aaData": labsArray,
        "aoColumns": [
       	           { "sTitle": "Date" },
       	           { "sTitle": "Lymph","sClass": "center", "bSortable":false  },
       	           { "sTitle": "WBC","sClass": "center", "bSortable":false  }
       	       ],
   	    "bPaginate": false,
   	    "aaSorting": [[ 0, "desc" ]],
		 "fnRowCallback": function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
		     // Bold the grade for all 'A' grade browsers
		       $('td:eq(0)', nRow).html(stringifyDate($('td:eq(0)', nRow).html()));
		   }
	});

	$('#lab-results td, #lab-results th').hover(function(){
		var iCol = $('td, th').index(this) % 3;
		if(iCol===1 || iCol===2){
			$('td:nth-child('+(iCol+1)+'),th:nth-child('+(iCol+1)+')').addClass("highlighted");
		}
	}, function(){
		var iCol = $('td, th').index(this) % 3;
		$('td:nth-child('+(iCol+1)+'),th:nth-child('+(iCol+1)+')').removeClass("highlighted");
	});
	
	//TODO what if there is no units or high or low values, catch all the null exceptions man
	$('#lab-results td, #lab-results th').click(function(){
		var iCol = $('td, th').index(this) % 3;
		$('.activated').removeClass('activated');
		if(iCol===1) {
			$('#lab-result .graphTitle').html('Lymph Lab Results Timeline<span class="grey"> (Click on a column to switch graphs)</span>');
			var graphArray = "Date,High,Lymph Count,Low\n";
			
			for (var i=0;i<unitlabsArray.length;i++) {
				graphArray = graphArray+sanitizeDate(unitlabsArray[i][0])+","+unitlabsArray[i][1].high+','+unitlabsArray[i][1].observed+','+unitlabsArray[i][1].low+"\n";			};
			g = new Dygraph(document.getElementById("graphdiv"), graphArray,{colors:["#AF0000","#0061AF","#AF0000"]});
		}else if(iCol===2) {
			$('#lab-result .graphTitle').html('WBC Lab Results Timeline<span class="grey"> (Click on a column to switch graphs)</span>');
			var graphArray = "Date,High,WBC,Low\n";
			
			for (var i=0;i<unitlabsArray.length;i++) {
				graphArray = graphArray+sanitizeDate(unitlabsArray[i][0])+","+unitlabsArray[i][2].high+','+unitlabsArray[i][2].observed+','+unitlabsArray[i][2].low+"\n";
			};
			g = new Dygraph(document.getElementById("graphdiv"), graphArray,{colors:["#AF0000","#0061AF","#AF0000"]});
		}
		if(iCol===1 || iCol===2){
			$('td:nth-child('+(iCol+1)+'),th:nth-child('+(iCol+1)+')').addClass("activated");
		}
	});
	
	var graphArray = "Date,High,Lymph Count,Low\n";
	
	for (var i=0;i<unitlabsArray.length;i++) {
		graphArray = graphArray+sanitizeDate(unitlabsArray[i][0])+","+unitlabsArray[i][1].high+','+unitlabsArray[i][1].observed+','+unitlabsArray[i][1].low+"\n"
	};
	g = new Dygraph(document.getElementById("graphdiv"), graphArray,{colors:["#AF0000","#0061AF","#AF0000"]});
	$('td:nth-child(2),th:nth-child(2)').addClass("activated");
};



var parsePayers = function() {
	var payers = [];
	var payersRaw = currentPatient.component.structuredBody.component[0].section.text.table.tbody;
	for (var i=0;i<payersRaw.tr.length;i++) { 
		payers[i] = payersRaw.tr[i].td;
	}
	
	return payers;
};
//Yes there are 3 differnt functions for date formatting, i am lazy as hell TODO FIX THIS HORROR
var renderBirthday = function() {
	var m_names = new Array("January", "February", "March", 
			"April", "May", "June", "July", "August", "September", 
			"October", "November", "December");

			var d = retrieveBirthday();
			var curr_date = d.getDate();
			var curr_month = d.getMonth();
			var curr_year = d.getFullYear();
			var bday = m_names[curr_month]+ " " +curr_date + " " + curr_year;
			return bday;
};

var stringifyDate = function(date) {
	var m_names = new Array("January", "February", "March", 
			"April", "May", "June", "July", "August", "September", 
			"October", "November", "December");

			var d = new Date(date);
			var curr_date = d.getDate();
			var curr_month = d.getMonth();
			var curr_year = d.getFullYear();
			var stringDate = m_names[curr_month]+ " " +curr_date + " " + curr_year;
			return stringDate;
};

var sanitizeDate = function(date) {
			var d = new Date(date);
			var curr_date = d.getDate();
			var curr_month = d.getMonth();
			var curr_year = d.getFullYear();
			var stringDate = curr_year + "-" + curr_month + "-" + curr_date;
			return stringDate;
};

var retrieveBirthday = function() {
	var birthDateRaw = currentPatient.recordTarget.patientRole.patient.birthTime.value;
	var birthDate = new Date(birthDateRaw.substring(0,4),birthDateRaw.substring(4,6),birthDateRaw.substring(6,8));

	return birthDate;
};

var parseVitalsResults = function() {
	var labArray = retrieveVitalsObject().section;
	var labResults=[];
	
	if(labArray.entry.length!==undefined) {
		for (var i=0;i<labArray.entry.length;i++) {
			//parent in here
			var drivEntry = labArray.entry[i];
			labResults[i] = [];
			for (var j=1;j<labArray.entry[i].organizer.component.length;j++) {
				//timestamp in here
				var observation = labArray.entry[i].organizer.component[j].observation;
				labResults[i][0]=new Date(observation.effectiveTime.value.substring(0,4),observation.effectiveTime.value.substring(4,6),observation.effectiveTime.value.substring(6,8));
					
				if(observation.code.code=='8867-4') {
					labResults[i][1] = observation.value.value;
				};
				
				if(observation.code.code=='9279-1') {
					labResults[i][2] = observation.value.value;
				};
			};
		};
	}else{
		//sometimes the entry is an object instead of array fix here later
	}
	
	return labResults;
};

var renderVitals = function() {
	var labsArray = parseVitalsResults();
	$("#vital-sign .hero-unit.datatable").html('<table cellpadding="0" cellspacing="0" border="0" class="display table" id="vital-signs"></table');
	var labsTables = $("#vital-signs").dataTable( {
        "aaData": labsArray,
        "aoColumns": [
       	           { "sTitle": "Date" },
       	           { "sTitle": "Heart Beat","sClass": "center", "bSortable":false  },
       	           { "sTitle": "Respiration","sClass": "center", "bSortable":false  }
       	       ],
   	    "bPaginate": false,
   	    "aaSorting": [[ 0, "desc" ]],
		 "fnRowCallback": function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
		     // Bold the grade for all 'A' grade browsers
		       $('td:eq(0)', nRow).html(stringifyDate($('td:eq(0)', nRow).html()));
		   }
	});

	$('#vital-signs td, #vital-signs th').hover(function(){
		var iCol = $('td, th').index(this) % 3;
		if(iCol===1 || iCol===2){
			$('td:nth-child('+(iCol+1)+'),th:nth-child('+(iCol+1)+')').addClass("highlighted");
		}
	}, function(){
		var iCol = $('td, th').index(this) % 3;
		$('td:nth-child('+(iCol+1)+'),th:nth-child('+(iCol+1)+')').removeClass("highlighted");
	});
	
	//TODO what if there is no units or high or low values, catch all the null exceptions man
	$('#vital-signs td, #vital-signs th').click(function(){
		var iCol = $('td, th').index(this) % 3;
		$('.activated').removeClass('activated');
		if(iCol===1) {
			$('#lab-result .graphTitle').html('Heart Beats Timeline<span class="grey"> (Click on a column to switch graphs)</span>');
			var graphArray = "Date,Heart Beat\n";
			
			for (var i=0;i<labsArray.length;i++) {
				graphArray = graphArray = graphArray+sanitizeDate(labsArray[i][0])+","+labsArray[i][1]+"\n"
			};
			h = new Dygraph(document.getElementById("graphvitalsdiv"), graphArray,{colors:["#0061AF"]});
		}else if(iCol===2) {
			$('#lab-result .graphTitle').html('Respiration Timeline<span class="grey"> (Click on a column to switch graphs)</span>');
			var graphArray = "Date,Respiration\n";
			
			for (var i=0;i<labsArray.length;i++) {
				graphArray = graphArray+sanitizeDate(labsArray[i][0])+","+labsArray[i][2]+"\n"
			};
			h = new Dygraph(document.getElementById("graphvitalsdiv"), graphArray,{colors:["#0061AF"]});
		}
		if(iCol===1 || iCol===2){
			$('td:nth-child('+(iCol+1)+'),th:nth-child('+(iCol+1)+')').addClass("activated");
		}
	});
	
	var graphArray = "Date,Heart Beat\n";
	
	for (var i=0;i<labsArray.length;i++) {
		graphArray = graphArray+sanitizeDate(labsArray[i][0])+","+labsArray[i][1]+"\n"
	};
	h = new Dygraph(document.getElementById("graphvitalsdiv"), graphArray,{colors:["#0061AF"]});
	$('td:nth-child(2),th:nth-child(2)').addClass("activated");
};
