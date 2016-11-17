<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	//check if the user is not already logged
	HttpSession userSession = request.getSession(false);
	if (userSession != null && userSession.getAttribute("user") != null)
		response.sendRedirect("main.jsp");
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>SeGarer.com</title>

<meta name="description" content="">
<meta name="author" content="">

<link href="https://fonts.googleapis.com/css?family=Lobster|Raleway"
	rel="stylesheet">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="css/main.css" rel="stylesheet">
<link
	href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.1.0/css/font-awesome.min.css"
	rel="stylesheet">

</head>
<body>

	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header brand">
				<h2>
					SeGarer.com
				</h2>
			</div>
		</div>
	</nav>

	<div id="form-container" class="container-fluid">
		<div class="form row">
			<div class="form-group">

				<label for="login" class="control-label">Pseudo ou E-mail </label> <input
					type="text" class="form-control" id="login" />

			</div>
			<div class="form-group">

				<label for="password" class="control-label"> Mot de passe </label> <input
					type="password" class="form-control" id="password" />

			</div>
			<div class="form-group">
				<a href="#" id="signInButton" type="button"
					data-loading-text="Se connecter<i class='fa fa-spinner fa-spin'></i>"
					class="btn btn-success">Se connecter</a> <a href="#" id="registerButton"
					type="button" class="btn btn-danger pull-right">Pas encore
					inscrit ?</span>
				</a>
			</div>
		</div>
	</div>

	<script src="js/jquery-3.1.1.min.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
	<script src="js/connection.js"></script>

</body>
</html>