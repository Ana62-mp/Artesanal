package com.krakedev.artesanal.testJUnit;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.krakedev.artesanal.Maquina;

public class TestLlenarJUnit {

	@Test
	public void testllenarMaquina() {

		Maquina rubia = new Maquina("Club", "cerveza verde", 0.02, 8000, "ab3n");

		rubia.llenarMaquina();

		assertEquals(7900, rubia.getCantidadActual(), 0.0001);
	}
}