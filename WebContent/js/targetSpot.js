/** Google Direction API **/

function initMap() {
	var origin = {lat: currentSpot.latitude, lng: currentSpot.longitude};
	var destination = {lat: 39.79, lng: -86.14};

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

function init(){
	$('#main-container').fadeIn('slow');
	$('#main-menu').fadeIn('slow');
}

$(document).ready(init());