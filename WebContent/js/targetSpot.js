/** Google Direction API **/

function initMap() {

	var origin = {lat: currentSpot.latitude, lng: currentSpot.longitude};
	var destination = {lat: aimSpot.latitude, lng: aimSpot.longitude};

	var map = new google.maps.Map(document.getElementById('map'), {
		center: origin,
		scrollwheel: false,
		zoom: 7
	});

	var directionsDisplay = new google.maps.DirectionsRenderer({
		map: map
	});

	// Set destination, origin and travel mode.
	var request = {
			destination: destination,
			origin: origin,
			travelMode: 'DRIVING'
	};

	// Pass the directions request to the directions service.
	var directionsService = new google.maps.DirectionsService();
	directionsService.route(request, function(response, status) {
		if (status == 'OK') {
			// Display the route on the map.
			directionsDisplay.setDirections(response);
		}
	});
}

/** Others Methods **/

function logOut(){
	$('#main-container').fadeOut('slow');
	$('#main-menu').fadeOut('slow',function(){
		window.location.replace("/SeGarer.com/signOut")});
}

function removeSpot(spotWasFree){
	var success = function(){
		alert('merci a bientot');
	}

	$.ajax({
		url : 'holdSpot',
		type : 'POST',
		data : {spotId:destination.id},
		success : success
	});
}

function backToMenu(){
	window.location.replace("/SeGarer.com/main.jsp");
}

function init(){
	$('#main-container').fadeIn('slow');
	$('#main-menu').fadeIn('slow');
	setInterval(routine, 10000);
}

function routine(){
	checkIsFree();
}

function checkIsFree(){

	var error = function(jqXHR,textStatus,errorThrown){
		if(jqXHR.status == 493){
			$('#modal').modal('toggle');
		}
	}

	$.ajax({
		url : 'isFree',
		type : 'GET',
		data : {spotId:aimSpot.id},
		error: error,
		success: function(){console.log('libre')}
	});
}

$(document).ready(init());