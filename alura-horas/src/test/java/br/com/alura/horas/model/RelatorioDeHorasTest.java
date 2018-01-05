package br.com.alura.horas.model;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

public class RelatorioDeHorasTest {
	
	@Test
	public void calculaRelatorioQuandoDatasSaoIguais() {
		Calendar data = new GregorianCalendar(2018,0,5);
		HoraLancada hora1 = novaHoraLancada("10:00","18:00",data);
		HoraLancada hora2 = novaHoraLancada("18:00","20:00",data);
		RelatorioDeHoras relatorioDeHoras = new RelatorioDeHoras(Arrays.asList(hora1, hora2));
		
		HorasPorDia horasPorDia = relatorioDeHoras.getHorasPorDia().get(0);
		assertEquals(1, relatorioDeHoras.getHorasPorDia().size());
		assertEquals(8.0, horasPorDia.getHorasNormais(),0.01);
		assertEquals(2.0, horasPorDia.getHorasExtras(),0.01);
		assertEquals(data, horasPorDia.getData());
	}
	
	@Test
	public void calculaRelatorioQuandoDatasSaoDiferentes() {
		Calendar data1 = new GregorianCalendar(2018,0,5);
		Calendar data2 = new GregorianCalendar(2018,0,4);
		
		HoraLancada hora1 = novaHoraLancada("9:00","18:00",data1);
		HoraLancada hora2 = novaHoraLancada("9:00","17:00",data2);
		
		RelatorioDeHoras relatorioDeHoras = new RelatorioDeHoras(Arrays.asList(hora1,hora2));
		
		HorasPorDia horasPorDia = relatorioDeHoras.getHorasPorDia().get(0);
		HorasPorDia horasPorDia2 = relatorioDeHoras.getHorasPorDia().get(1);
		
		assertEquals(2, relatorioDeHoras.getHorasPorDia().size());
		
		assertEquals(8.0, horasPorDia.getHorasNormais(),0.01);
		assertEquals(1.0, horasPorDia.getHorasExtras(),0.01);
		assertEquals(data1, horasPorDia.getData());
		
		assertEquals(8.0, horasPorDia2.getHorasNormais(),0.01);
		assertEquals(0.0, horasPorDia2.getHorasExtras(),0.01);
		assertEquals(data2, horasPorDia2.getData());
		
	}

	private HoraLancada novaHoraLancada(String horaInicial, String horaFinal, Calendar data) {
		HoraLancada horaLancada = new HoraLancada();
		horaLancada.setData(data);
		horaLancada.setHoraInicial(horaInicial);
		horaLancada.setHoraFinal(horaFinal);
		return horaLancada;
	}

}
