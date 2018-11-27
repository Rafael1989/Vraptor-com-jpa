package br.com.alura.horas.controller;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import br.com.alura.horas.dao.HoraLancadaDao;
import br.com.alura.horas.model.HoraLancada;
import br.com.alura.horas.model.RelatorioDeHoras;
import br.com.alura.horas.security.UsuarioLogado;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.IncludeParameters;
import br.com.caelum.vraptor.validator.SimpleMessage;
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
		horaLancada.setUsuario(usuarioLogado.getUsuario());
		if(Integer.parseInt(horaLancada.getHoraInicial().substring(0, 1)) > Integer.parseInt(horaLancada.getHoraFinal().substring(0, 1)) || 
				Integer.parseInt(horaLancada.getHoraInicial().substring(0, 1)) == Integer.parseInt(horaLancada.getHoraFinal().substring(0, 1)) && 
				Integer.parseInt(horaLancada.getHoraInicial().substring(3, 4)) > Integer.parseInt(horaLancada.getHoraFinal().substring(3, 4))) {
			validator.add(new SimpleMessage("horainicial_invalida", "A hora inicial deve ser menor que a final"));
			validator.onErrorRedirectTo(this).formulario();
		}
		validator.onErrorRedirectTo(this).formulario();
		horaLancadaDao.adiciona(horaLancada);
		result.redirectTo(this).lista();
	}
	
	@Path("/horaLancada/preparaEdita/{id}")
	public void preparaEdita(int id) {
		result.include("horaLancada",horaLancadaDao.getHoraLancadaById(id));
		result.redirectTo(this).formulario();
	}
	
	@Path("/horaLancada/remove/{id}")
	public void remove(int id) {
		horaLancadaDao.removeHoraLancada(id);
		result.redirectTo(this).lista();
	}
	
	public void lista() {
		result.include("horas",horaLancadaDao.lista());
	}
	
	public void relatorioHoras() {
		List<HoraLancada> horas = horaLancadaDao.horasDoUsuario(usuarioLogado.getUsuario());
		RelatorioDeHoras relatorioDeHoras = new RelatorioDeHoras(horas);
		result.include("relatorio",relatorioDeHoras);
	}
}
