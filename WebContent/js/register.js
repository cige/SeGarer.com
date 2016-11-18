$('#signInButton').on('click',function() {
	$('#form-container').fadeOut('slow',function(){window.location.replace("/SeGarer.com/connection.jsp");});
});

$('#registerButton').on('click',function() {
	$(this).button('loading');
	signUp();
});

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
		$('#registerButton').button('reset');
		return;
	}

	var data = {
			email : email,
			pseudo : pseudo,
			password : password,		
			password2 : password2	
	}

	var success = function(){
		$('#registerButton').button('reset');
		$('#form-container').fadeOut('slow',function(){window.location.replace("/SeGarer.com/main.jsp");});
	}

	var error = function(jqXHR,textStatus,errorThrown){
		$('#registerButton').button('reset');
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

function display(){
	$('#form-container').fadeIn('slow');
}

$(document).ready(display());
