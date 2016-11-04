function signUp(){
	
	var ok = true;
	
	var email = document.getElementById('email').value;
	var password = document.getElementById('password').value;
	var password2 = document.getElementById('password2').value;
	
	if(email == ""){
		ok = false;
	}
	
	if(password == ""){
		ok = false;
	}
	
	if(password2 == ""){
		ok = false;
	}
	
	if(password != password2){
		ok = false;
	}
	
	if(!ok){
		alert("error somewhere");
		return;
	}
	
	var data = {
			email : email,
			password : password		
	}
	
	var success = function(){
		window.location.replace("main.jsp");
	}
	
	var error = function(){
		alert('error');
	}
	
	$.ajax({
	       url : 'signUp',
	       type : 'POST',
	       data : data,
	       success : success,
	       error: error
	    });
}