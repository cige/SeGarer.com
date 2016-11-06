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

<link href="css/bootstrap.min.css" rel="stylesheet">

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

				<form class="form-inline">
					<div class="form-group">

						<label for="inputEmail3" class="col-sm-2 control-label">
							Email </label>
						<div class="col-sm-10">
							<input type="email" class="form-control" id="email" />
						</div>
					</div>
					<div class="form-group">

						<label for="inputEmail3" class="col-sm-2 control-label">
							Pseudo </label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="pseudo" />
						</div>
					</div>
					<div class="form-group">

						<label for="inputPassword3" class="col-sm-2 control-label">
							Mot de passe </label>
						<div class="col-sm-10">
							<input type="password" class="form-control" id="password" />
						</div>
					</div>
					<div class="form-group">

						<label for="inputPassword3" class="col-sm-2 control-label">
							Confirmation mot de passe </label>
						<div class="col-sm-10">
							<input type="password" class="form-control" id="password2" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" onClick="signUp()" class="btn btn-danger">S'inscrire</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	<script src="js/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/register.js"></script>
</body>
</html>