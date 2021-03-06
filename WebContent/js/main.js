/** Global variables **/

var currentSpot = null;
var aimedSpot = null;
var resultsSpots = null;
var autocomplete = null;
var isGeolocalized = false;

/** HTML Content **/

var gift = "<span class='glyphicon glyphicon-gift' aria-hidden='true'></span>";
var geoloc = "<span class='glyphicon glyphicon-screenschot' aria-hidden='true'></span>";
var welcome = "<div class='alert alert-success'>Pour commencer à utiliser l'application, veuillez <label for='main-input'><mark>saisir votre adresse actuelle</mark></label> ou utilisez la <label for='geoloc-button'><mark>géolocalisation</mark></label>";
var noresults = "<div class='alert alert-success'>Malheureusement, aucune place n'a été trouvée près de votre localisation, essayez de <label for='search-button'><mark>relancer la recherche</mark></label> dans quelques instants ...";
var choice = "<div class='alert alert-success'>Souhaitez vous <label for='search-button'><mark>trouver une place</mark></label> ou bien <label for='release-button'><mark>signaler une place libre</mark></label> ?";
var thanks = "<div class='alert alert-warning'>Libération enregistrée ! La communauté vous remercie pour cette place de stationnement !</div>";
var release =  "<span class='search-spot glyphicon glyphicon-log-out' aria-hidden='true'></span>";
var search = "<span class='search-spot glyphicon glyphicon-log-in' aria-hidden='true'></span>";
var geoloc = "<span class='glyphicon glyphicon-screenshot' aria-hidden='true'></span>";


var getSpotResult = function(spot,i){
	var id = "spot-banner" + i;
	var res="<div id='"+id+"' class='panel panel-info''>";
	res += "<div class='panel-heading'>" +spot.address + "<div class='pull-right'> Certitude: "+spot.purcentage+"%</div></div>";
	res += "<div class='panel-body'>";
	res += "<span class='glyphicon glyphicon glyphicon-road' aria-hidden='true'></span> ";
	res += spot.duration+" ("+spot.distance+")<button type='button' onClick='aimSpot("+i+")' class='btn btn-default pull-right'>";
	res += "J'y vais ";
	if(spot.intersted == 1)
		res+="<span title=\"Quelqu'un est déjà sur le coup!\" class='badge'>"+spot.intersted+"</span>";
	else if(spot.intersted > 1)
		res+="<span title='Déjà "+spot.intersted+" utilisateurs intéressés, faites vite!' class='badge'>"+spot.intersted+"</span>";
	res+="</button></div></div>";
	return res;
}

/** Display methods **/

function hideThenClearContainer(container,callback){
	var clear = function(){
		container.empty();
		if(callback!=undefined)
			callback();
	}
	hideContainer(container,clear);
}

function hideContainer(container,callback){
	container.fadeOut('slow',callback);
}

function displayContainer(container,callback){
	container.fadeIn('slow',callback);
}

/** Results Display **/

var resultsContainer =  $('#results-container');

function displayResults(){

	if(resultsSpots == null || resultsSpots.length == 0){
		return;
	}

	for(var i=0;i<resultsSpots.length;i++){
		resultsContainer.append(getSpotResult(resultsSpots[i],i));
	}

	displayContainer(resultsContainer);
}

/** Alerts Display **/

var alertsContainer = $('#alerts-container');

function initAlerts(){
	alertsContainer.html(welcome);
	displayContainer(alertsContainer);
}

function displayChoiceAlert(){
	var callback = function(){
		alertsContainer.html(choice);
		displayContainer(alertsContainer);
	}
	hideThenClearContainer(alertsContainer, callback);

}

function displayNoResultAlert(){
	var callback = function(){
		alertsContainer.html(noresults);
		displayContainer(alertsContainer);
	}
	hideThenClearContainer(alertsContainer, callback);	
}

function displayThanksAlert(){
	var callback = function(){
		alertsContainer.html(thanks);
		displayContainer(alertsContainer);
	}
	hideThenClearContainer(alertsContainer, callback);	
}

/** Buttons Display **/

var releaseButton =  $('#release-button');
var searchButton = $('#search-button');
var geolocButton = $('#geoloc-button');

function disableButton(button){
	button.addClass('disabled');
	button.unbind('click');
}

function enableButton(button,fun){
	button.removeClass('disabled');
	button.on("click",fun)
}

function setButtonValue(button,content){
	button.html(content);
}

function resetAllButtons(){
	setButtonValue(releaseButton,release);
	setButtonValue(searchButton,search);
	setButtonValue(geolocButton,geoloc);
}

function setLoading(button){
	button.html("<i class='fa fa-spinner fa-spin'></i>");
}

function enableAllButtons(){
	enableButton(releaseButton,releaseSpot);
	enableButton(searchButton,findSpots);
}

function disableAllButtons(){
	disableButton(releaseButton);
	disableButton(searchButton);
}

/** Google auto-complete **/

function initAutocomplete() {
	// Create the autocomplete object, restricting the search to geographical
	// location types.
	autocomplete = new google.maps.places.Autocomplete(
			/** @type {!HTMLInputElement} */(document.getElementById('main-input')),
			{types: ['geocode']});

	// When the user selects an address from the dropdown, populate the address
	// fields in the form.
	autocomplete.addListener('place_changed', fillInAddress);
}

function fillInAddress() {
	// Get the place details from the autocomplete object.
	var place = autocomplete.getPlace();
	if(place==undefined){
		currentSpot = null;
		return;
		disableAllButtons();
	}
	currentSpot ={};
	currentSpot.latitude = place.geometry.location.lat;
	currentSpot.longitude = place.geometry.location.lng;
	currentSpot.address = $("#main-input").val();
	enableAllButtons();
	alertsContainer.html(choice);
}

/** Other methods **/

function inputBlurred(){
	if(currentSpot != null){
		if($('#main-input').val()==""){
			resetApp();
		}
		else
			$("#main-input").val(currentSpot.address);
	}
	displayContainer(alertsContainer);
	displayContainer(resultsContainer);
}

function resetApp(){
	currentSpot = null;
	resultsSpots = null;
	resetAllButtons();
	enableAllButtons();
	hideThenClearContainer(alertsContainer);
	hideThenClearContainer(resultsContainer);
	initAlerts();
}

function createRequestForReverseGeocoding(lat,lon){
	return "https://maps.googleapis.com/maps/api/geocode/json?latlng="+lat+","+lon+"&key=AIzaSyDaDyG8ylVLCq-sjdbLeBn07LtN81zWETo";
}

function aimSpot(i){

	if(currentSpot == null)
		return;
	if(resultsSpots == null)
		return;
	aimedSpot = resultsSpots[i];
	if(aimedSpot == null)
		return;

	var success = function(){
		window.location.replace("/SeGarer.com/targetSpot.jsp?idSpot="+aimedSpot.id+"&lat="+currentSpot.latitude+"&lon="+currentSpot.longitude);
	}

	$.ajax({
		url : 'targetSpot',
		type : 'POST',
		data : {spotId:aimedSpot.id},
		success : success
	});

}

function geolocalize(){

	if(!navigator.geolocation){
		alert("Votre navigateur ne permet pas la géolocalisation");
		return;
	}

	setLoading(geolocButton);
	hideThenClearContainer(alertsContainer);
	hideThenClearContainer(resultsContainer);

	navigator.geolocation.getCurrentPosition(function(position)
			{
		currentSpot ={};
		currentSpot.latitude = position.coords.latitude;
		currentSpot.longitude = position.coords.longitude;
		var request = createRequestForReverseGeocoding(currentSpot.latitude,currentSpot.longitude);
		$.ajax({
			url:request,
			type:"GET",
			dataType:"json",
			success:function(data){
				if(data.results.length == 0){
					$("#main-input").val(currentSpot.longitude+","+currentSpot.latitude);
				}
				else{
					currentSpot.address = data.results[0].formatted_address;
					$("#main-input").val(currentSpot.address);
				}
				isGeolocalized = true;
				setButtonValue(geolocButton,geoloc);
				enableAllButtons();
				displayChoiceAlert();
			},
			error:function(){setButtonValue(geolocButton,geoloc)}
		})

			});
}

function releaseSpot(){

	if(currentSpot == null){
		alert('veuillez saisir votre position');
		return;
	}

	setLoading(releaseButton);
	disableAllButtons();

	hideThenClearContainer(resultsContainer);
	hideThenClearContainer(alertsContainer);

	var success = function(jqXHR,textStatus,errorThrown){
		setTimeout(function(){
			setButtonValue(releaseButton,release)
			displayThanksAlert();
		},1500);
	}

	var error = function(jqXHR,textStatus,errorThrown){
		setButtonValue(releaseButton,release);
		alert('error');
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

	setLoading(searchButton);
	disableAllButtons();

	hideThenClearContainer(resultsContainer);
	hideThenClearContainer(alertsContainer);


	var success = function(data){
		setTimeout(function(){
			setButtonValue(searchButton,search);
			resultsSpots = data.results;
			if(resultsSpots.length == 0){
				displayNoResultAlert();
				resetAllButtons();
				enableAllButtons();
			}
			else{
				displayResults();
			}
		},1000);
	}

	var error = function(jqXHR,textStatus,errorThrown){
		alert('error');
		setButtonValue(searchButton,search);
		enableAllButtons();
	}

	$.ajax({
		url : 'find',
		type : 'GET',
		data : currentSpot,
		success : success,
		error: error
	});
}

function init(){
	var fun = function(){
		if(currentSpot == null){
			initAlerts();
			return;
		}
		var request = createRequestForReverseGeocoding(currentSpot.latitude,currentSpot.longitude);
		$.ajax({
			url:request,
			type:"GET",
			dataType:"json",
			success:function(data){
				if(data.results.length == 0){
					$("#main-input").val(currentSpot.longitude+","+currentSpot.latitude);
				}
				else{
					currentSpot.address = data.results[0].formatted_address;
					$("#main-input").val(currentSpot.address);
				}
				findSpots();
			}
		});

	}
	$('#main-container').fadeIn('slow',fun);
	$('#main-menu').fadeIn('slow');
}

function logOut(){
	$('#main-container').fadeOut('slow');
	$('#main-menu').fadeOut('slow',function(){
		window.location.replace("/SeGarer.com/signOut")});
}


$(document).ready(init());

