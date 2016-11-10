/** Banners **/

var getBannerFromSpot = function(spot){
	var res="<div class='list-group-item list-group-item-info banner banner-info'>";
	res +=	"<div class='row'>";
	res +="<div class='col-xs-7'>";
	res += spot.address;
	res += "</div>";
	res += "<div class='col-xs-5'>";
	res += "Libéré il y a +"+ spot.time +" minutes par <a href='#'>"+ spot.user +"</a>";
	res += "</div></div><div class='row'><div class='col-xs-8'>";
	res += "<span class='glyphicon glyphicon glyphicon-road' aria-hidden='true'></span>";
	res += "-- min (-,-km)";
	res += "</div><div class='col-xs-4'><button type='button' class='btn btn-info pull-right'>";
	res += "J'y fonce !</button></div></div></div>";
	return res;
}

var getInfoBanner = function(){
	var res ="<div class='list-group-item list-group-item-success banner banner-info'>";
	res += "Pour commencer à utiliser l'application, veuillez saisir votre adresse actuelle,";
	res +="ou appuyer sur <span class='glyphicon glyphicon-screenshot' aria-hidden='true'></span></div>";
	return res;
}

var getNoResultBanner = function(){
	var res ="<div class='list-group-item list-group-item-danger banner banner-no-result'>";
	res +=	"<div class='row'>";
	res +=	"<div class='col-xs-9'>";
	res += "Malheureusement, nous n'avons trouvé aucune place près de vous...";
	res += "</div>";
	res +=	"<div class='col-xs-3'>";
	res +="<button onClick='findSpots()'type='button' class='btn btn-danger pull-right'>";
	res += "Relancer</button></div>";
	res +="</div>";
	res +="</div>";
	return res;
}

/** Local variables **/

var currentSpot = null;
var closestSpots = null;

/** Other methods **/

function logOut(){
	window.location.replace("/DAR/signOut");
}

function createRequestForReverseGeocoding(lat,lon){
	return "https://maps.googleapis.com/maps/api/geocode/json?latlng="+lat+","+lon+"&key=AIzaSyDaDyG8ylVLCq-sjdbLeBn07LtN81zWETo";

}

function updateBannerContainer(){
	var container = $("#banner-container");
	container.empty();

	if(currentSpot == null){
		container.append(getInfoBanner());
		return;
	}

	if(closestSpots != null){
		var length = closestSpots.length;
		if(length==0){
			container.append(getNoResultBanner());
		}else{
			for(var i=0;i<length;i++){
				container.append(getBannerFromSpot(closestSpots[i]));
			}
		}
	}


}

function geolocalize(){
	if(!navigator.geolocation){
		alert("Votre navigateur ne permet pas la géolocalisation");
		return;
	}

	navigator.geolocation.getCurrentPosition(function(position)
			{
		currentSpot ={};
		currentSpot.latitude = position.coords.latitude;
		currentSpot.longitude = position.coords.longitude;
		var request = createRequestForReverseGeocoding(currentSpot.latitude,currentSpot.longitude);
		console.log(request);
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
		closestSpots = data.results;
		updateBannerContainer();
	}

	var error = function(jqXHR,textStatus,errorThrown){
		alert('error');
	}

	console.log('appel servlet');
	$.ajax({
		url : 'find',
		type : 'GET',
		data : currentSpot,
		success : success,
		error: error
	});
}

