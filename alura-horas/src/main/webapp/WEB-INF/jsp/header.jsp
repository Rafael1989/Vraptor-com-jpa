<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Sistema de horas</title>
		<link rel="stylesheet" href="<c:url value="../css/bootstrap.css"/>" type="text/css"/>
		<link rel="stylesheet" href="<c:url value="../css/site.css"/>" type="text/css"/>
		<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
		<script src="../js/site.js"></script>
	</head>
	<body>
		<header>
		
		</header>
		<nav>
			<ul class="nav nav-tabs menu-principal">
				<li><a href="<c:url value="/"/>">Home</a></li>
				<li><a href="<c:url value="/usuario/lista"/>">Usuários</a></li>
				<li><a href="<c:url value="/horaLancada/lista"/>">Horas cadastradas</a></li>
				<li><a href="<c:url value="/horaLancada/formulario"/>">Cadastrar horas</a></li>
				<li><a href="<c:url value="/login/desloga"/>">Logout</a></li>
			</ul>
		</nav>
		<div class="container">
			<main class="col-sm-8">