/** Spot object definition**/

var Spot = function(longitude,latitude,address){
	this.longitude = longitude;
	this.latitude = latitude;
	this.address = address;
}

Spot.prototype.getHtml = function(){
	var res="<div class='list-group-item list-group-item-info banner banner-info'>";
	res +=	"<div class='row'>";
	res +="<div class='col-xs-7'>";
	res+=this.address;
	res +="</div>";
	res+="<div class='col-xs-5'>";
	res+="Libéré il y a 45 minutes par <a href='#'>Robert2000</a>"
		res+="</div></div><div class='row'><div class='col-xs-8'>";
	res+="<span class='glyphicon glyphicon glyphicon-road' aria-hidden='true'></span>";
	res+="-- min (-,-km)";
	res+="</div><div class='col-xs-4'><button type='button' class='btn btn-info pull-right'>";
	res+="J'y fonce !</button></div></div></div>";
}

/** Local variables **/

var currentSpot;
var closestSpots;

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
		$.ajax({
			url:request,
			type:"GET",
			dataType:"json",
			success:function(data){
				if(data.results.length == 0){
					$("#localisationInput").val(currentSpot.longitude+","+currentSpot.latitude);
				}
				else{
					currentSpot.address = data.results[0].formatted_address;
					$("#localisationInput").val(currentSpot.address);
				}
				findSpots();
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

	if(currentSpot == null){
		alert('veuillez saisir votre position');
		return;
	}

	var success = function(data){
		alert(data);
		var obj = JSON.parse(data);
		alert(obj);
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

