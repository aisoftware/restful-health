//DOM READY loop
$(function(){
	if($('body#login').exists()){
		$('form .btn').bind('click', function(){
			$.ajax({
				url:'http://localhost:8080/healthcare/login'
			});
		});
	}
});