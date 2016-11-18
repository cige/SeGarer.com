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

<title>SeGarer.com - Créer un compte</title>

<meta name="description" content="">
<meta name="author" content="">

<link href="https://fonts.googleapis.com/css?family=Lobster|Raleway"
	rel="stylesheet">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="css/main.css" rel="stylesheet">

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

				<label for="email" class="control-label"> Email </label>
					<input type="email" class="form-control" id="email" />
			</div>
			<div class="form-group">

				<label for="pseudo" class="control-label"> Pseudo </label>
					<input type="text" class="form-control" id="pseudo" />
			</div>
			<div class="form-group">

				<label for="password" class="control-label"> Mot de passe </label> <input
					type="password" class="form-control" id="password" />
			</div>
			<div class="form-group">

				<label for="password2" class="control-label"> Confirmation
					mot de passe </label> <input type="password" class="form-control"
					id="password2" />
			</div>
			<div class="form-group">
				<a href="#" id="registerButton" type="button"
					data-loading-text="<i class='fa fa-spinner fa-spin'></i> S'inscrire " 
					class="btn btn-info">S'inscrire</a><a href="#" id="signInButton" type="button"
					class="btn btn-warning pull-right">Déjà un compte ?</a>
			</div>
		</div>
	</div>

	<script src="js/jquery-3.1.1.min.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
	<script src="js/register.js"></script>
</body>
</html>