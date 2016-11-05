<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%
	//check if the user is well logged
	HttpSession userSession = request.getSession(false);
	if(userSession == null || userSession.getAttribute("user") == null){
		response.sendRedirect("connection.jsp");
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

<link href="css/bootstrap.min.css" rel="stylesheet">

</head>
<body>

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<form>
					<input id="localisationInput" type=text />
					<button type="button" class="btn btn-default" onClick="geolocalize()">
						<span class="glyphicon glyphicon-screenshot" aria-hidden="true"></span>
					</button>

					<button type="button" class="btn btn-default">Trouver une
						place</button>
					<button type="button" class="btn btn-default">Libérer une
				place</button>
				</form>
			</div>


			

		</div>
	</div>

	<script src="js/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/main.js"></script>

</body>
</html>