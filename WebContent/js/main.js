$(document).ready(function(){
        $('.dropdown-toggle').dropdown()
    });

function logOut(){
	window.location.replace("/DAR/signOut");
}

function geolocalize(){
	if(navigator.geolocation){
		navigator.geolocation.getCurrentPosition(function(position)
			    {
			        $("#localisationInput").val(position.coords.latitude + "," + position.coords.longitude);
			    });
	}
	else
	    alert("Votre navigateur ne prend pas en compte la géolocalisation");
}