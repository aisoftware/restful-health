//DOM READY loop
$(function(){
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
	
	//register
	if($('body#register').exists()) {
		$('form .btn').bind('click', function() {
			var data = {};
			data.username=$('#usrName').val();
			data.password=$('#usrPassword').val();
			$.ajax({
				url:'http://localhost:8080/healthcare/register',
				data:data
			});
		});
	}
	
	//home
	if($('body#home').exists()) {
		
	}
	
	
});