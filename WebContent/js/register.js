function signUp(){
	
	var ok = true;
	
	var email = document.getElementById('email').value;
	var pseudo = document.getElementById('pseudo').value;
	var password = document.getElementById('password').value;
	var password2 = document.getElementById('password2').value;
	
	if(email == ""){
		ok = false;
	}
	
	if(pseudo == ""){
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
			pseudo : pseudo,
			password : password		
	}
	
	var success = function(){
		window.location.replace("main.jsp");
	}
	
	var error = function(jqXHR,textStatus,errorThrown){
		if(jqXHR.status == 498){
			alert("cet email est deja utilise");
			return;
		}
		if(jqXHR.status == 497){
			alert("ce pseudo est deja utilise");
			return;
		}
	}
	
	$.ajax({
	       url : 'signUp',
	       type : 'POST',
	       data : data,
	       success : success,
	       error: error
	    });
}