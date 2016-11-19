
<%@page import="model.dao.DaoFactory"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.entities.User"%>
<%@ page import="model.entities.Spot"%>


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

	String idSpot = null, longitude = null, latitude = null;
	idSpot = request.getParameter("idSpot");
	longitude = request.getParameter("lon");
	latitude = request.getParameter("lat");

	if (idSpot == null || longitude == null || latitude == null) {
		response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		return;
	}

	Long id = Long.valueOf(idSpot);
	Spot spot = DaoFactory.getInstance().getSpotDao().getSpotById(id);
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
			<div class="navbar-header">
				<h2>SeGarer.com</h2>
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

		<div class='panel panel-info''>
			<div class='panel-heading'>
				Quai François Mauriac, 75013 Paris, France
				<div class='pull-right'>Certitude: 50%</div>
			</div>
			<div class='panel-body'>
				<div id='map'></div>
			</div>
			<div class='panel-footer'>
				<button type='button' onClick='' class='btn btn-warning'>La
					place est libre, je me gare</button>
				<button type='button' onClick='' class='btn btn-danger pull-right'>La
					place est déjà occupée</button>
			</div>
		</div>
		<button type='button' onClick='' class='btn btn-info'>
			<span class='glyphicon glyphicon glyphicon-chevron-left
						'></span>Trouver
			une autre place
		</button>
	</div>

	<script type='text/javascript'>
		var currentSpot = {};
		currentSpot.latitude =
	<%=latitude%>
		;
		currentSpot.longitude =
	<%=longitude%>
		;
		var destination = {};
		destination.latitude =
	<%=spot.getAddress().getLatitude()%>
		;
		destination.longitude =
	<%=spot.getAddress().getLongitude()%>
		;
	</script>
	<script src="js/jquery-3.1.1.min.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
	<script src="js/targetSpot.js"></script>
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA-f3l4e9XyeJ6_rDns4imuqQnpmL6Lvi4&callback=initMap"
		async defer></script>

</body>
</html>