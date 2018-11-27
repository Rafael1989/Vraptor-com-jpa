package br.com.alura.horas.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import br.com.alura.horas.dao.UsuarioDao;
import br.com.alura.horas.model.Usuario;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.IncludeParameters;
import br.com.caelum.vraptor.validator.Validator;

@Controller
public class UsuarioController {

	private Validator validator;
	private UsuarioDao usuarioDAO;
	private Result result;

	@Inject
	public UsuarioController(Result result, UsuarioDao usuarioDAO, Validator validator) {
		this.result = result;
		this.usuarioDAO = usuarioDAO;
		this.validator = validator;
	}
	
	@Deprecated
	UsuarioController(){
		this(null,null,null);
	}
	
	public void formulario() {
		
	}
	
	@Post
	@IncludeParameters
	public void adiciona(@Valid Usuario usuario) {
		validator.onErrorRedirectTo(this).formulario();
		usuarioDAO.adiciona(usuario);
		result.redirectTo(this).lista();
	}
	
	@Path("/usuario/preparaEdita/{id}")
	public void preparaEdita(int id) {
		result.include("usuario",usuarioDAO.getUsuarioById(id));
		result.redirectTo(this).formulario();
	}
	
	@Path("/usuario/remove/{id}")
	public void remove(int id) {
		usuarioDAO.removeUsuario(id);
		result.redirectTo(this).lista();
	}

	public void lista() {
		result.include("usuarios",usuarioDAO.lista());
	}
}
