<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="alura"%>
<c:import url="/WEB-INF/jsp/header.jsp"/>

<form action="${linkTo[HoraLancadaController].adiciona(null)}" method="post" autocomplete="off">
    <alura:validationMessage name="horainicial_invalida"/>
	<input type="hidden" name="horaLancada.id" value="${horaLancada.id}"/>
	<label for="data">Data:</label>
	<fmt:formatDate pattern="dd/MM/yyyy" value="${horaLancada.data.time}" var="dataFormatada"/>
	<input type="text" class="form-control" id="data" name="horaLancada.data" value="${dataFormatada}"/>
	<alura:validationMessage name="horaLancada.data"/>
	
	<label for="horaInicial">Hora inicial:</label>
	<input type="text" class="form-control" id="horaInicial" name="horaLancada.horaInicial" value="${horaLancada.horaInicial}"/>
	<alura:validationMessage name="horaLancada.horaInicial"/>
	
	<label for="saidaAlmoco">Saída para o almoço:</label>
	<input type="text" class="form-control" id="saidaAlmoco" name="horaLancada.saidaAlmoco" value="${horaLancada.saidaAlmoco}"/>
	<alura:validationMessage name="horaLancada.saidaAlmoco"/>
	
	<label for="voltaAlmoco">Volta do almoço:</label>
	<input type="text" class="form-control" id="voltaAlmoco" name="horaLancada.voltaAlmoco" value="${horaLancada.voltaAlmoco}"/>
	<alura:validationMessage name="horaLancada.voltaAlmoco"/>
	
	<label for="horaFinal">Hora final:</label>
	<input type="text" class="form-control" id="horaFinal" name="horaLancada.horaFinal" value="${horaLancada.horaFinal}"/>
	<alura:validationMessage name="horaLancada.horaFinal"/>
	<br/>
	<input type="submit" value="Cadastrar" class="btn"/>
</form>

<c:import url="/WEB-INF/jsp/footer.jsp"/>