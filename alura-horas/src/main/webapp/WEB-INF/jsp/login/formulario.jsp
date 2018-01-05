<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="alura" %>
<c:import url="/WEB-INF/jsp/header.jsp"/>
<form action="${linkTo[LoginController].autentica(null)}" method="post">
	<alura:validationMessage name="login_invalido"/>
	
	<label for="login">Login:</label>
	<input type="text" name="usuario.login" id="login" class="form-control"/>
	
	<label for="senha">Senha:</label>
	<input type="password" name="usuario.senha" id="senha" class="form-control"/>
	
	<input type="submit" value="Logar" class="btn"/>
	
</form>
<c:import url="/WEB-INF/jsp/footer.jsp"/>