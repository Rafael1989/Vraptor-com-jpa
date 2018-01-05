package br.com.alura.horas.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HoraLancadaTest {
	
	@Test
	public void calculaHoraInicialMenorHoraFinal() {
		HoraLancada horaLancada = new HoraLancada();
		
		horaLancada.setHoraInicial("9:00");
		horaLancada.setHoraFinal("17:00");
		
		assertEquals(8.0, horaLancada.getDuracao(),0.01);
	}

}
