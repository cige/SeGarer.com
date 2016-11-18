$('#signInButton').on('click',function() {
	var $btn = $(this);
	$btn.button('loading');
	signIn();
});

$('#registerButton').on('click',function() {
	$('#form-container').fadeOut('slow',function(){window.location.replace("/SeGarer.com/register.jsp");});
});

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
		$('#signInButton').button('reset');
		alert("error somewhere");
		return;
	}

	var data = {
			login : login,
			password : password		
	}

	var success = function(){
		$('#signInButton').button('reset');
		$('#form-container').fadeOut('slow',function(){window.location.replace("/SeGarer.com/main.jsp");});
	}

	var error = function(jqXHR,textStatus,errorThrown){
		
		$('#signInButton').button('reset');

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

function display(){
	$('#form-container').fadeIn('slow');
	$('[data-toggle="tooltip"]').tooltip();
}

$(document).ready(display());

