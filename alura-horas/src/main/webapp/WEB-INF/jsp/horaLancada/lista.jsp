<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="alura"%>
<c:import url="/WEB-INF/jsp/header.jsp"/>
<a href="${linkTo[HoraLancadaController].formulario()}"></a>
<table class="table table-hover">
	<thead>
		<tr>
			<th>Id</th>
			<th>Data</th>
			<th>Hora Inicial</th>
			<th>Hora Final</th>
			<th>Duração</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${horas}" var="hora">
			<tr>
				<td>${hora.id}</td>
				<td>${hora.data.time}</td>
				<td>${hora.horaInicial}</td>
				<td>${hora.horaFinal}</td>
				<td>${hora.duracao}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<c:import url="/WEB-INF/jsp/footer.jsp"/>