<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="alura"%>
<c:import url="/WEB-INF/jsp/header.jsp"/>

<form action="${linkTo[HoraLancadaController].adiciona(null)}" method="post">
	<label for="data">Data:</label>
	<input type="text" class="form-control" id="data" name="horaLancada.data" value="${horaLancada.data.time}"/>
	<alura:validationMessage name="horaLancada.data"/>
	
	<label for="horaInicial">Hora inicial:</label>
	<input type="text" class="form-control" id="horaInicial" name="horaLancada.horaInicial" value="${horaLancada.horaInicial}"/>
	<alura:validationMessage name="horaLancada.horaInicial"/>
	
	<label for="horaFinal">Hora final:</label>
	<input type="text" class="form-control" id="horaFinal" name="horaLancada.horaFinal" value="${horaLancada.horaFinal}"/>
	<alura:validationMessage name="horaLancada.horaFinal"/>
	
	<input type="submit" value="Cadastrar" class="btn"/>
</form>

<c:import url="/WEB-INF/jsp/footer.jsp"/>