function signIn(){

	var ok = true;

	var login = document.getElementById('login').value;
	var password = document.getElementById('password').value;

	if(login == ""){
		ok = false;
	}

	if(password == ""){
		ok = false;
	}

	if(!ok){
		alert("error somewhere");
		return;
	}

	var data = {
			login : login,
			password : password		
	}

	var success = function(){
		window.location.replace("main.jsp");
	}

	var error = function(jqXHR,textStatus,errorThrown){
		
		if(jqXHR.status == 496){
			alert("mot de passe incorrect");
			return;
		}
		
		if(jqXHR.status == 495){
			alert("login incorrect");
			return;
		}
		
		alert('error: ' + jqXHR.status);
		
	}

	$.ajax({
		url : 'signIn',
		type : 'POST',
		data : data,
		success : success,
		error: error
	});
}