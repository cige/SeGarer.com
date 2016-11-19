
<%@page import="model.dao.DaoFactory"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.entities.User"%>


<%
	//check if the user is not already logged
	HttpSession userSession = request.getSession(false);
	if (userSession == null) {
		response.sendRedirect("connection.jsp");
		return;
	}

	User user = (User) userSession.getAttribute("user");

	if (user == null) {
		response.sendRedirect("connection.jsp");
		return;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>SeGarer.com</title>

<meta name="description" content="">
<meta name="author" content="">

<link href="https://fonts.googleapis.com/css?family=Lobster|Raleway"
	rel="stylesheet">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link
	href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.1.0/css/font-awesome.min.css"
	rel="stylesheet">
<link href="css/main.css" rel="stylesheet">
</head>
<body>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header brand">
				<h2>
					<strong>SeGarer</strong>.com
				</h2>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">

					<li id='main-menu' class="dropdown"><a class="dropdown-toggle"
						id="usertools" data-toggle="dropdown" role="button"
						aria-haspopup="true" aria-expanded="false"> <span
							class='glyphicon glyphicon-user
						'></span> <span
							class="caret"></span>
					</a>
						<ul class="dropdown-menu">
							<li><a onClick="logOut()">Se déconnecter</a></li>
							<li><a class="disabled" href="#">Gérer mes vehicules</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</nav>
	<div id="main-container" class="container-fluid">

		<div id="input-container" class="input-group">
			<span class="input-group-btn">
				<button id="geoloc-button" title="Se géolocaliser"
					class="btn btn-default" onClick="geolocalize()"
					data-loading-text="<i class='fa fa-spinner fa-spin'></i>"
					type="button">
					<span class="glyphicon glyphicon-screenshot" aria-hidden="true"></span>
				</button>
			</span> <input id="main-input" type="text" class="form-control"
				placeholder="Indiquez votre adresse..."
				onFocus="hideContainer(alertsContainer);hideContainer(resultsContainer)"
				onBlur="inputBlurred()"> <span class="input-group-btn">
				<button id='search-button' title="Rechercher une place"
					class="btn btn-info disabled" type="button">
					<span class="search-spot glyphicon glyphicon-log-in"
						aria-hidden="true"></span>
				</button>
				<button id='release-button' title="Libérer une place"
					class="btn btn-warning disabled" type="button">
					<span class="release-spot glyphicon glyphicon-log-out"
						aria-hidden="true"></span>
				</button>
			</span>
		</div>

		<div id="alerts-container"></div>

		<div id="results-container" class="panel-group"></div>

	</div>

	<script src="js/jquery-3.1.1.min.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
	<script src="js/main.js"></script>
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAQrG6Qo0cixNmFOTWgmxwEq_WFV5eiGn0&signed_in=true&libraries=places&callback=initAutocomplete"
		defer></script>

</body>
</html>