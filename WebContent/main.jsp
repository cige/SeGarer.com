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

<link rel="stylesheet" type="text/css"
	href="https://fonts.googleapis.com/css?family=Lobster">
<link rel="stylesheet" type="text/css"
	href="https://fonts.googleapis.com/css?family=Raleway">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="css/main.css" rel="stylesheet">
</head>
<body>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header brand">
				<h2>SeGarer.com
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right navbar-btn">

					<li class="dropdown"><a href="#"
						class="dropdown-toggle btn btn-default" id="usertools"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false"> <span class="glyphicon glyphicon-user"></span>
							<%=user.getPseudo()%> <span class="caret"></span>
					</a>
						<ul class="dropdown-menu">
							<li><a onClick="logOut()">Se déconnecter</a></li>
							<li><a href="#">Gérer mes vehicules</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</nav>
	<div id="main-container" class="container-fluid">

		<div id="input-container" class="form-horizontal form-group">
			<input id="localisationInput" type=text
				class="form-control col-xs-11">
			<button id="geolocalizeButton" type="button"
				class="btn btn-default col-xs-1" onClick="geolocalize()">
				<span class="glyphicon glyphicon-screenshot" aria-hidden="true"></span>
			</button>
		</div>



		<div id="list-group banner-container">

			<div
				class="list-group-item list-group-item-success banner banner-proposal">
				Pour commencer à utiliser l'application, veuillez saisir votre
				adresse actuelle, ou appuyer sur <span
					class="glyphicon glyphicon-screenshot" aria-hidden="true"></span>

			</div>
			<div class="list-group-item list-group-item-info banner banner-info">
				<div class="row">
					<div class="col-xs-7">65 rue Paul vaillant couturier Malakoff</div>
					<div class="col-xs-5">
						Libéré il y a 45 minutes par <a href="#">Robert2000</a>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-8">
						<span class="glyphicon glyphicon glyphicon-road"
							aria-hidden="true"></span> 20 min (3,5km)
					</div>
					<div class="col-xs-4">
						<button type="button" class="btn btn-info pull-right">J'y
							fonce !</button>
					</div>
				</div>
			</div>

			<div
				class="list-group-item list-group-item-warning banner banner-release">
				<div class="row">
					<div class="col-xs-12">
						Vous quittez une place de stationnement ? Faites en profiter la
						communauté !
						<button type="button" class="btn btn-warning pull-right"
							onClick="geolocalize()">Libérer ma place</button>
					</div>


				</div>

			</div>
		</div>

		<script src="js/jquery-3.1.1.min.js"></script>
		<script src="bootstrap/js/bootstrap.min.js"></script>
		<script src="js/main.js"></script>
</body>
</html>