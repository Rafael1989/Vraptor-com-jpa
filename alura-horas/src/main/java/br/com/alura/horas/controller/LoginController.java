package br.com.alura.horas.controller;

import javax.inject.Inject;

import br.com.alura.horas.annotation.Open;
import br.com.alura.horas.dao.UsuarioDao;
import br.com.alura.horas.model.Usuario;
import br.com.alura.horas.security.UsuarioLogado;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;

@Controller
public class LoginController {
	
	private Validator validator;
	private UsuarioLogado usuarioLogado;
	private UsuarioDao usuarioDAO;
	private Result result;

	@Inject
	public LoginController(UsuarioLogado usuarioLogado, UsuarioDao usuarioDAO, Result result, Validator validator) {
		this.usuarioLogado = usuarioLogado;
		this.usuarioDAO = usuarioDAO;
		this.result = result;
		this.validator = validator;
	}
	
	@Deprecated
	LoginController() {
		this(null,null,null,null);
	}
	
	@Open
	public void formulario() {
		
	}
	
	@Open
	public void autentica(Usuario usuario) {
		Usuario usuario2 = usuarioDAO.busca(usuario);
		if(usuario2 != null) {
			usuarioLogado.setUsuario(usuario2);
			result.redirectTo(IndexController.class).index();
		}else {
			validator.add(new SimpleMessage("login_invalido", "Login ou senha incorretos."));
			validator.onErrorRedirectTo(this).formulario();
		}
	}
	
	public void desloga() {
		usuarioLogado.desloga();
		result.redirectTo(this).formulario();
	}

}
