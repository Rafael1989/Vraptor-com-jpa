package br.com.alura.horas.security;

import javax.inject.Inject;
import javax.management.openmbean.OpenDataException;

import br.com.alura.horas.annotation.Open;
import br.com.alura.horas.controller.LoginController;
import br.com.caelum.vraptor.Accepts;
import br.com.caelum.vraptor.AroundCall;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.interceptor.SimpleInterceptorStack;

@Intercepts
public class AutorizadorInterceptor {

	private UsuarioLogado usuarioLogado;
	private Result result;
	private ControllerMethod controllerMethod;
	
	@Deprecated
	AutorizadorInterceptor() {
		this(null,null,null);
	}
	
	@Inject
	public AutorizadorInterceptor(UsuarioLogado usuarioLogado, Result result, ControllerMethod controllerMethod) {
		this.usuarioLogado = usuarioLogado;
		this.result = result;
		this.controllerMethod = controllerMethod;
	}
	
	@AroundCall
	public void intercept(SimpleInterceptorStack stack) {
		if(usuarioLogado.getUsuario() != null) {
			stack.next();
		}else {
			result.redirectTo(LoginController.class).formulario();
		}
	}
	
	@Accepts
	public boolean accepts() {
		return !this.controllerMethod.containsAnnotation(Open.class);
	}
}
