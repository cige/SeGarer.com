
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
	Spot spot = DaoFactory.getInstance().getSpotDao().getSpotFromId(id);
	
	if(spot == null){
		response.sendRedirect("main.jsp");
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

		<div class='panel panel-info''>
			<div class='panel-heading'>
				<%=spot.getAddress().getFormattedAddress()%>
			</div>
			<div class='panel-body'>
				<div id='map'></div>
			</div>
			<div class='panel-footer'>
				<button type='button' onClick='removeSpot(true)'
					class='btn btn-warning'>La place est libre, je me gare</button>
				<button type='button' onClick='removeSpot(false)'
					class='btn btn-danger pull-right'>La place est déjà
					occupée</button>
			</div>
		</div>
		<button type='button' onClick='backToMain()' class='btn btn-info'>
			<span class='glyphicon glyphicon glyphicon-chevron-left
						'></span>Trouver
			une autre place
		</button>
	</div>

	<div class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div id="modal" class="modal-content">
				<div class="modal-body">
					<p>Malheureusement la place n'est plus disponible, nous vous
						invitons à refaire une recherche</p>
				</div>
				<div class="modal-footer">
					<button type="button" onClick="backToMain()"
						class="btn btn-default">Retour à la recherche</button>
				</div>
			</div>

		</div>
	</div>

	<script type='text/javascript'>
		var currentSpot = {};
		currentSpot.latitude =
	<%=latitude%>
		;
		currentSpot.longitude =
	<%=longitude%>
		;
		var aimSpot = {};
		aimSpot.latitude =
	<%=spot.getAddress().getLatitude()%>
		;
		aimSpot.longitude =
	<%=spot.getAddress().getLongitude()%>
		;
		aimSpot.id =
	<%=spot.getId()%>
		
	</script>
	
	<script src="js/jquery-3.1.1.min.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
	<script src="js/targetSpot.js"></script>
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA-f3l4e9XyeJ6_rDns4imuqQnpmL6Lvi4&callback=initMap"
		async defer></script>

</body>
</html>