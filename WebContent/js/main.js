/** Global variables **/

var currentSpot = null;
var closestSpots = null;

/** Banners **/

var getSpotBanner = function(spot,i){
	var res="<div id='spotBanner"+i+"' class='list-group-item list-group-item-info banner banner-info'>";
	res +=	"<div class='row'>";
	res +="<div class='col-xs-9'>";
	res += spot.address;
	res += "</div>";
	res += "<div class='col-xs-3'>";
	res += "Libéré il y a "+ spot.time +" minutes par <a href='#'>"+ spot.user +"</a>";
	res += "</div></div><div class='row'><div class='col-xs-8'>";
	res += "<span class='glyphicon glyphicon glyphicon-road' aria-hidden='true'></span>";
	res += "-- min (-,-km)";
	res += "</div><div class='col-xs-4'><button type='button' class='btn btn-info pull-right'>";
	res += "J'y fonce !<span class='badge'>"+spot.intersted+"</span></button></div></div></div>";
	return res;
}

var getLoadingBanner = function () {
	var res ="<div id='loadingBanner' class='list-group-item list-group-item-info banner banner-info'>";
	res += "<i class='fa fa-spinner fa-spin'></i> Recherche des places disponibles à proximité </div>";
	return res;
}

var getInfoBanner = function(){
	var res ="<div id='infoBanner' class='list-group-item list-group-item-success banner banner-info'>";
	res += "Pour commencer à utiliser l'application, veuillez saisir votre adresse actuelle,";
	res +="ou appuyer sur <span class='glyphicon glyphicon-screenshot' aria-hidden='true'></span></div>";
	return res;
}

var getReleaseBanner = function(){
	var res ="<div id='releaseBanner' class='list-group-item list-group-item-warning banner banner-release'>";
	res += "<div class='row'><div class='col-xs-12'>";
	res += "Vous quittez une place de stationnement ? Faites en profiter la communauté ! ";
	res += "<button id='releaseSpotButton' onClick='releaseSpot()' type='button' class='btn btn-warning pull-right' ";
	res+= "data-loading-text=\"<i class='fa fa-spinner fa-spin'></i>\">Libérer ma place</button>";
	res += "</div></div></div>";
	return res;
}

var getNoResultBanner = function(){
	var res ="<div id='noResultBanner' class='list-group-item list-group-item-danger banner banner-no-result'>";
	res +=	"<div class='row'>";
	res +=	"<div class='col-xs-9'>";
	res += "Malheureusement, nous n'avons trouvé aucune place près de vous...";
	res += "</div>";
	res +=	"<div class='col-xs-3'>";
	res +="<button id='searchAgainButton' onClick='searchAgain();' type='button' class='btn btn-danger pull-right'>";
	res += "Relancer</button></div>";
	res +="</div>";
	res +="</div>";
	return res;
}

function emptyBannerContainer(){
	console.log('empty');
	$('#banner-container').queue('banner',function(){
		$('#banner-container').children().fadeOut('slow',function(){
			$('#banner-container').empty();	
		});});
	$('#banner-container').dequeue('banner');
}

function updateBannerContainer(){

	console.log('update');

	if(currentSpot!= null && ! $('#releaseBanner').length){
		$('#banner-container').queue('banner',function(){$('#banner-container').append(getReleaseBanner());$('#releaseBanner').fadeIn('slow');$('#banner-container').dequeue('banner');});
	}

	if(closestSpots == null && ! $('#loadingBanner').length){
		$('#banner-container').queue('banner',function(){console.log('loadingFadeIn');$('#banner-container').append(getLoadingBanner());$('#loadingBanner').fadeIn('slow');$('#banner-container').dequeue('banner');});
		$('#banner-container').dequeue('banner');
		return;
	}

	if($('#loadingBanner').length){
		$('#banner-container').queue('banner',function(){console.log('loadingFadeOut');$('#loadingBanner').fadeOut('slow');
		$('#banner-container').dequeue('banner');})}

	var length = closestSpots.length;

	if(length==0){
		$('#banner-container').queue('banner',function(){$('#banner-container').append(getNoResultBanner());$('#noResultBanner').fadeIn('slow');$('#banner-container').dequeue('banner');});
	}
	else{

		$('#banner-container').queue('banner',function(){
			for(var i=0;i<closestSpots.length;i++){
				$('#banner-container').append(getSpotBanner(closestSpots[i],i));
				console.log('spot'+i);$('#spotBanner'+i).fadeIn('slow');
			}});
	}
	$('#banner-container').dequeue('banner');
}

function initBannerContainer(){

	console.log('init');

	if(currentSpot == null){

		$('#banner-container').queue('banner',function(){$('#banner-container').empty();$('#banner-container').append(getInfoBanner());$('#infoBanner').fadeIn();$('#banner-container').dequeue('banner');});
	}

	$('#banner-container').dequeue('banner');
}

/** Other methods **/

function logOut(){
	window.location.replace("/DAR/signOut");
}

function createRequestForReverseGeocoding(lat,lon){
	return "https://maps.googleapis.com/maps/api/geocode/json?latlng="+lat+","+lon+"&key=AIzaSyDaDyG8ylVLCq-sjdbLeBn07LtN81zWETo";
}

function geolocalize(){

	$('#geolocalizeButton').button('loading');
	emptyBannerContainer();

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
				$('#geolocalizeButton').button('reset');
				updateBannerContainer();
				findSpots();
			},
			error:function(){$('#geolocalizeButton').button('reset');initBannerContainer();}
		})

			});
}

function releaseSpot(){

	$('#releaseSpotButton').button('loading');

	if(currentSpot == null){
		alert('veuillez saisir votre position');
		return;
	}

	var success = function(jqXHR,textStatus,errorThrown){
		$('#releaseSpotButton').button('reset');
		alert('merci');
	}

	var error = function(jqXHR,textStatus,errorThrown){
		$('#releaseSpotButton').button('reset');
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

	console.log('findSpots');

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

	$.ajax({
		url : 'find',
		type : 'GET',
		data : currentSpot,
		success : success,
		error: error
	});
}

$(document).ready(initBannerContainer());

