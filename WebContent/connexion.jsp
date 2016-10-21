<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	//check if the user is already logged
	HttpSession userSession = request.getSession(false);
	if(userSession != null && userSession.getAttribute("user") != null)
		response.sendRedirect("main.html");
	
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

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/main.css" rel="stylesheet">

</head>
<body>

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="page-header">
					<h1>
						SeGarer.com <small>Une place, et vite !</small>
					</h1>
				</div>
				<form class="form-inline" action="signIn" method="POST">
					<div class="form-group">

						<label for="inputEmail3" class="control-label">
							Email </label>
						
							<input type="email" class="form-control" id="inputEmail3" />
						
					</div>
					<div class="form-group">

						<label for="inputPassword3" class="control-label">
							Mot de passe </label>
						
							<input type="password" class="form-control" id="inputPassword3" />
						
					</div>
					<div class="form-group">
						

							<button class="btn btn-success" type="submit">Se
								connecter</button> <a href="inscription.html" class="btn btn-danger">S'inscrire</a>
					</div>
				</form>
			</div>
		</div>
	</div>

	<script src="js/jquery-3.1.1.slim.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/main.js"></script>

</body>
</html>