<%@tag description="Simple Template" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="body_area" fragment="true" %>

 <!DOCTYPE HTML>
<!--
	Linear by TEMPLATED
    templated.co @templatedco
    Released for free under the Creative Commons Attribution 3.0 license (templated.co/license)
-->
<html>
	<head>
		<title>NSY135</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta name="description" content="" />
		<meta name="keywords" content="" />
		<link href='http://fonts.googleapis.com/css?family=Roboto:400,100,300,700,500,900' rel='stylesheet' type='text/css'>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/skel.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/skel-panels.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/init.js"></script>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/skel-noscript.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style-desktop.css" />
	
	</head>
	<body class="homepage">

	<!-- Header -->
		<div id="header">
			<jsp:invoke fragment="header"/>
		</div>

	<!-- Featured -->
		<div id="featured">
			<div class="container">
				<header>
					<h2>Applications Orientées Données.</h2>
				</header>
        </hr>
				<p style="margin-bottom:20px;">Le contenu de ce bloc est le même sur toutes les pages du site... <br>
				On va utiliser ce template pour présenter les mécanismes vus dans le
        cours avec la base Webscope.</p>
				<div class="row">
					<section class="6u">
						<a href="#" class="image full"><img src="${pageContext.request.contextPath}/images/pic01.jpg" alt=""></a>
						<header>
							<h2>Un exemple constant</h2>
						</header>
						<p>Un lien.</p>
					</section>				
					<section class="6u">
						<a href="#" class="image full"><img src="${pageContext.request.contextPath}/images/pic02.jpg" alt=""></a>
						<header>
							<h2>Un autre exemple</h2>
						</header>
						<p>Un autre lien</p>
					</section>				
				</div>
				
			</div>
		</div>

	<!-- Main -->
		<div id="main">
			<div id="content" class="container">

<jsp:invoke fragment="body_area"/>
			
			</div>
		</div>

		<div id="tweet">
			<div class="container">
				<section>
					<blockquote>&ldquo; Il faut savoir s'instruire dans la gaieté. Le
            savoir triste est un savoir mort. L'intelligence est joie.&rdquo;
            Voltaire.</blockquote>
				</section>
			</div>
		</div>

	<!-- Footer -->
		<div id="footer">
			<div class="container"><section>
					<header>
						<h2>Contact</h2>
          <span></span>
					<ul>
            <li>Mail de<a href="mailto:philippe.rigaux@cnam.fr"><span>Philippe Rigaux</span></a></li>
        <li>Mail de<a href="mailto:fournier@cnam.fr"><span>Raphaël Fournier-S'niehotta </span></a></li>
						<!--<li><a href="#" class="fa fa-twitter"><span>Twitter</span></a></li>-->
						<!--<li class="active"><a href="#" class="fa fa-facebook"><span>Facebook</span></a></li>-->
						<!--<li><a href="#" class="fa fa-dribbble"><span>Pinterest</span></a></li>-->
						<!--<li><a href="#" class="fa fa-tumblr"><span>Google+</span></a></li>-->
					</ul>
				
		</section>
			</div>
		</div>

	<!-- Copyright -->
		<div id="copyright">
			<div class="container">
			Design: <a href="http://templated.co">TEMPLATED</a> Images: <a href="http://unsplash.com">Unsplash</a> (<a href="http://unsplash.com/cc0">CC0</a>)
				</div>
		</div>

	</body>
</html>
