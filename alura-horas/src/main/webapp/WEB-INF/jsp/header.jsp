<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Sistema de horas</title>
		<link rel="stylesheet" href="<c:url value="../css/jquery-ui.min.css"/>"  type="text/css" />
		<link rel="stylesheet" href="<c:url value="css/bootstrap.css"/>" type="text/css"/>
		<link rel="stylesheet" href="<c:url value="css/site.css"/>" type="text/css"/>
		<link rel="stylesheet" href="<c:url value="../css/bootstrap.css"/>" type="text/css"/>
		<link rel="stylesheet" href="<c:url value="../css/jquery.timepicker.min.css"/>" type="text/css"/>
		<link rel="stylesheet" href="<c:url value="../css/datatables.min.css"/>" type="text/css"/>
		<link rel="stylesheet" href="<c:url value="../css/site.css"/>" type="text/css"/>
		<script src="../js/jquery.js"></script>
		<script src="../js/jquery-ui.min.js"></script>
		<script src="../js/jquery.timepicker.min.js"></script>
		<script src="../js/datatables.min.js"></script>
		<script src="../js/site.js"></script>
	</head>
	<body>
		<header>
		
		</header>
		<nav>
			<ul class="nav nav-tabs menu-principal">
				<li><a href="<c:url value="/"/>">Home</a></li>
				<li><a href="<c:url value="/usuario/lista"/>">Usu�rios</a></li>
				<li><a href="<c:url value="/horaLancada/lista"/>">Horas cadastradas</a></li>
				<li><a href="<c:url value="/horaLancada/formulario"/>">Cadastrar horas</a></li>
				<li><a href="<c:url value="/login/desloga"/>">Logout</a></li>
			</ul>
		</nav>
		<div class="container">
			<main class="col-sm-8">