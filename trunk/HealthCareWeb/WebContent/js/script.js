//DOM READY loop
$(function() {
	$(".hidden").removeClass("hidden");
	renderOverView();
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
		if(structure[i].section.title==="Payers Section") {
			return structure[i];
		}
	};
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
	$("#payer").html('<table cellpadding="0" cellspacing="0" border="0" class="display table" id="payers"></table>');
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

var parsePayers = function() {
	var payers = [];
	var payersRaw = currentPatient.component.structuredBody.component[0].section.text.table.tbody;
	for (var i=0;i<payersRaw.tr.length;i++) { 
		payers[i] = payersRaw.tr[i].td;
	}
	
	return payers;
};

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

var retrieveBirthday = function() {
	var birthDateRaw = currentPatient.recordTarget.patientRole.patient.birthTime.value;
	var birthDate = new Date(birthDateRaw.substring(0,4),birthDateRaw.substring(4,6),birthDateRaw.substring(6,8));

	return birthDate;
};