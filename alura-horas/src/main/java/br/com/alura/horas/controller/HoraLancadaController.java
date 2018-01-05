package br.com.alura.horas.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import br.com.alura.horas.dao.HoraLancadaDao;
import br.com.alura.horas.model.HoraLancada;
import br.com.alura.horas.security.UsuarioLogado;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.IncludeParameters;
import br.com.caelum.vraptor.validator.Validator;

@Controller
public class HoraLancadaController {

	private Result result;
	private Validator validator;
	private HoraLancadaDao horaLancadaDao;
	private UsuarioLogado usuarioLogado;

	@Deprecated
	HoraLancadaController() {
		this(null,null,null,null);
	}
	
	@Inject
	public HoraLancadaController(Result result, Validator validator, HoraLancadaDao horaLancadaDao, UsuarioLogado usuarioLogado) {
		this.result = result;
		this.validator = validator;
		this.horaLancadaDao = horaLancadaDao;
		this.usuarioLogado = usuarioLogado;
	}
	
	public void formulario() {
		
	}
	
	@IncludeParameters
	public void adiciona(@Valid HoraLancada horaLancada) {
		validator.onErrorRedirectTo(this).formulario();
		horaLancadaDao.adiciona(horaLancada);
		result.redirectTo(this).lista();
	}
	
	public void lista() {
		result.include("horas",horaLancadaDao.lista());
	}
}
