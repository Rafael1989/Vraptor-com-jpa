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
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.IncludeParameters;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;

@Controller
public class HoraLancadaController {

	private Result result;
	private Validator validator;
	private HoraLancadaDao horaLancadaDao;
	private UsuarioLogado usuarioLogado;
	private double somaTotal = 0;

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
	
	@Post("/horaLancada/listaPorMes")
	public void listaPorMes(int mes) {
		List<HoraLancada> lista = horaLancadaDao.listaPorMes(mes);
		result.use(Results.json()).withoutRoot().from(lista).include("usuario").serialize();
	}
	
	@Path("/horaLancada/remove/{id}")
	public void remove(int id) {
		horaLancadaDao.removeHoraLancada(id);
		result.redirectTo(this).lista();
	}
	
	public void lista() {
		List<HoraLancada> lista = horaLancadaDao.lista();
		for(HoraLancada horaLancada : lista) {
			somaTotal += horaLancada.getDuracao();
		}
		String horasTotais = ""+somaTotal;
		if(horasTotais.length()<2) {
			horasTotais = "0"+horasTotais;
		}
		result.include("horasTotais",horasTotais);
		String minutosTotais = ""+(somaTotal * 60) % 60;
		if(minutosTotais.length()<2) {
			minutosTotais = "0"+minutosTotais;
		}
		result.include("minutosTotais",minutosTotais);
		result.include("horas",lista);
	}
	
	public void relatorioHoras() {
		List<HoraLancada> horas = horaLancadaDao.horasDoUsuario(usuarioLogado.getUsuario());
		RelatorioDeHoras relatorioDeHoras = new RelatorioDeHoras(horas);
		result.include("relatorio",relatorioDeHoras);
	}
}
