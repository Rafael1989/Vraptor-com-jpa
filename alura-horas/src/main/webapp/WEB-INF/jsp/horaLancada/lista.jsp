<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="alura"%>
<c:import url="/WEB-INF/jsp/header.jsp"/>
<a href="${linkTo[HoraLancadaController].formulario()}"></a>
<table class="table table-hover">
	<thead>
		<tr>
			<th>Nome</th>
			<th>Data</th>
			<th>Hora Inicial</th>
			<th>Hora Final</th>
			<th>Dura��o</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${horas}" var="hora">
			<tr>
				<td>${hora.usuario.nome}</td>
				<td><fmt:formatDate pattern="dd/MM/yyyy" value="${hora.data.time}" /></td>
				<td>${hora.horaInicial}</td>
				<td>${hora.horaFinal}</td>
				<td><fmt:formatNumber type="number" maxFractionDigits="0" value="${hora.duracao}" /></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<c:import url="/WEB-INF/jsp/footer.jsp"/>