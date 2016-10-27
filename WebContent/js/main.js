function logOut(){
	window.location.replace("/DAR/signOut");
}

function createRequestForReverseGeocoding(lat,lon){
	return "https://maps.googleapis.com/maps/api/geocode/json?latlng="+lat+","+lon+"&key=AIzaSyDaDyG8ylVLCq-sjdbLeBn07LtN81zWETo";

}

function geolocalize(){
	if(navigator.geolocation){
		navigator.geolocation.getCurrentPosition(function(position)
				{
				$.ajax({
					url:createRequestForReverseGeocoding(position.coords.latitude,position.coords.longitude),
					type:"GET",
					dataType:"json",
					success:function(data){
						$("#localisationInput").val(data.results[0].formatted_address);
					}
				})
			
				});
	}
	else
		alert("Votre navigateur ne prend pas en compte la géolocalisation");
}

