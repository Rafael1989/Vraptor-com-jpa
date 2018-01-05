<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/WEB-INF/jsp/header.jsp"/>
<h2>Relat�rio de horas cadastradas</h2>

<table class="table table-hover">
	<thead>
		<tr>
			<th>Data</th>
			<th>Horas Normais</th>
			<th>Horas Extras</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${relatorio.horasPorDia}" var="hora">	
			<tr>
				<td>${hora.data.time}</td>
				<td>${hora.horasNormais}</td>
				<td>${hora.horasExtras}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<c:import url="/WEB-INF/jsp/footer.jsp"/>
