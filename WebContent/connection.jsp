<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	//check if the user is not already logged
	HttpSession userSession = request.getSession(false);
	if(userSession != null && userSession.getAttribute("user") != null)
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

<link href="https://fonts.googleapis.com/css?family=Lobster|Raleway" rel="stylesheet">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="css/main.css" rel="stylesheet">
<link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">

</head>
<body>

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="page-header">
					<h1>
						<font face="Lobster" color="green">SeGarer.com</font> <small>Une place, et vite !</small>
					</h1>
				</div>
			</div>
		</div>
		<div class="form row">
			<div class="col-md-12">
				<div class="form-inline">
					<div class="form-group">

						<label for="login" class="control-label">Identifiant ou
							E-mail </label> <input type="text" class="form-control" id="login" />

					</div>
					<div class="form-group">

						<label for="password" class="control-label"> Mot de passe
						</label> <input type="password" class="form-control" id="password" />

					</div>
					<div class="form-group">
						<a id="signInButton" href="#" type="button" data-loading-text="<i class='fa fa-spinner fa-spin'></i>"
							class="btn btn-success">Se connecter</a> <a href="register.jsp"
							class="btn btn-danger">S'inscrire</a>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script src="js/jquery-3.1.1.min.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
	<script src="js/connection.js"></script>

</body>
</html>