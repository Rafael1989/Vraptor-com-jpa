<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="alura"%>
<c:import url="/WEB-INF/jsp/header.jsp"/>
<a href="${linkTo[HoraLancadaController].formulario()}"></a>
<select name="mes" id="mes">
	<option value="01">Janeiro</option>
	<option value="02">Fevereiro</option>
	<option value="03">Março</option>
	<option value="04">Abril</option>
	<option value="05">Maio</option>
	<option value="06">Junho</option>
	<option value="07">Julho</option>
	<option value="08">Agosto</option>
	<option value="09">Setembro</option>
	<option value="10">Outubro</option>
	<option value="11">Novembro</option>
	<option value="12">Dezembro</option>
</select>
<table class="table table-hover" id="horasTableId">
	<thead>
		<tr>
			<th>Nome</th>
			<th>Data</th>
			<th>Hora Inicial</th>
			<th>Saída para o almoço</th>
			<th>Volta do almoço</th>
			<th>Hora Final</th>
			<th>Duração</th>
			<th></th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${horas}" var="hora">
			<tr>
				<td>${hora.usuario.nome}</td>
				<td><fmt:formatDate pattern="dd/MM/yyyy" value="${hora.data.time}" /></td>
				<td>${hora.horaInicial}</td>
				<td>${hora.saidaAlmoco}</td>
				<td>${hora.voltaAlmoco}</td>
				<td>${hora.horaFinal}</td>
				<td>${hora.hora}:${hora.minutos}</td>
				<td><a href="${linkTo[HoraLancadaController].preparaEdita(hora.id)}">Editar</a></td>
				<td><a href="${linkTo[HoraLancadaController].remove(hora.id)}">Excluir</a></td>
			</tr>
		</c:forEach>
	</tbody>
	<tfoot>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<c:if test="${horasTotais > 0 || minutosTotais > 0}">
			<td>Total:</td>
				<td>${horasTotais}:${minutosTotais}</td>
			</c:if>
			<td></td>
			<td></td>
		</tr>
	</tfoot>
</table>

<c:import url="/WEB-INF/jsp/footer.jsp"/>