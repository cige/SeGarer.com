/** Spot object definition**/

var Spot = function(longitude,latitude,address){
	this.longitude = longitude;
	this.latitude = latitude;
	this.address = address;
}

/** Local variables **/

var currentSpot;

/** Other methods **/

function logOut(){
	window.location.replace("/DAR/signOut");
}

function createRequestForReverseGeocoding(lat,lon){
	return "https://maps.googleapis.com/maps/api/geocode/json?latlng="+lat+","+lon+"&key=AIzaSyDaDyG8ylVLCq-sjdbLeBn07LtN81zWETo";

}

function geolocalize(){
	if(!navigator.geolocation){
		alert("Votre navigateur ne permet pas la géolocalisation");
		return;
	}

	navigator.geolocation.getCurrentPosition(function(position)
			{
		currentSpot = new Spot(position.coords.latitude,position.coords.longitude);
		var request = createRequestForReverseGeocoding(currentSpot.longitude,currentSpot.latitude);
		console.log(request);
		$.ajax({
			url:request,
			type:"GET",
			dataType:"json",
			success:function(data){
				console.log(data);
				if(data.results.length == 0){
					$("#localisationInput").val(currentSpot.longitude+","+currentSpot.latitude);
					return;
				}
				currentSpot.address = data.results[0].formatted_address;
				$("#localisationInput").val(currentSpot.address);
			}
		})

			});
}

function releaseSpot(){

	if(currentSpot == null){
		alert('veuillez saisir votre position');
		return;
	}

	var success = function(jqXHR,textStatus,errorThrown){
	}

	var error = function(jqXHR,textStatus,errorThrown){
	}

	$.ajax({
		url : 'releaseSpot',
		type : 'POST',
		data : currentSpot,
		success : success,
		error: error
	});
}

function findSpots(){

	if(currentSpot == null)
		currentSpot = new Spot(2,2,"55 rue issy");

	var success = function(data){
		alert(data);
	}

	var error = function(jqXHR,textStatus,errorThrown){
		alert('error');
	}

	$.ajax({
		url : 'find',
		type : 'GET',
		data : currentSpot,
		success : success,
		error: error
	});
}

