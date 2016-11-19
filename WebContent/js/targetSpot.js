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

var routineId;

function logOut(){
	$('#main-container').fadeOut('slow');
	$('#main-menu').fadeOut('slow',function(){
		window.location.replace("/SeGarer.com/signOut")});
}

function removeSpot(spotWasFree){
	var success = function(){
		stopRoutine();
		alert('merci a bientot');
	}

	$.ajax({
		url : 'holdSpot',
		type : 'POST',
		data : {spotId:aimSpot.id},
		success : success
	});
}

function backToMain(){
	stopRoutine();
	window.location.replace("/SeGarer.com/main.jsp?longitude="+currentSpot.longitude+"&latitude="+currentSpot.latitude);
}

function init(){
	$('#main-container').fadeIn('slow');
	$('#main-menu').fadeIn('slow');
	routineId = setInterval(routine, 10000);
}

function routine(){
	checkIsFree();
}

function stopRoutine(){
	clearInterval(routineId);
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