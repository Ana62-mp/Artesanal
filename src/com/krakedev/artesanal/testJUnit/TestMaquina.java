package com.krakedev.artesanal.testJUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.krakedev.artesanal.Maquina;

public class TestMaquina {

	@Test
	@DisplayName("Constructor con 4 parámetros debe asignar capacidad máxima de 10000 y cantidad actual en 0")
	public void testConstructor4Parametros() {
		Maquina maquina = new Maquina("IPA", "Cerveza amarga", 0.05, "M-10");

		assertEquals("IPA", maquina.getNombreCerveza());
		assertEquals("Cerveza amarga", maquina.getDescripcion());
		assertEquals(0.05, maquina.getPrecioPorMl(), 0.0001);
		assertEquals(10000, maquina.getCapacidadMaxima(), 0.0001);
		assertEquals(0, maquina.getCantidadActual(), 0.0001);
		assertEquals("M-10", maquina.getCodigo());
	}

	@Test
	@DisplayName("Constructor con 5 parámetros debe asignar la capacidad máxima enviada y cantidad actual en 0")
	public void testConstructor5Parametros() {
		Maquina maquina = new Maquina("Stout", "Cerveza oscura", 0.08, 8000, "M-20");

		assertEquals("Stout", maquina.getNombreCerveza());
		assertEquals("Cerveza oscura", maquina.getDescripcion());
		assertEquals(0.08, maquina.getPrecioPorMl(), 0.0001);
		assertEquals(8000, maquina.getCapacidadMaxima(), 0.0001);
		assertEquals(0, maquina.getCantidadActual(), 0.0001);
		assertEquals("M-20", maquina.getCodigo());
	}

	@Test
	@DisplayName("llenarMaquina debe dejar la cantidad actual en capacidad máxima menos 100")
	public void testLlenarMaquina() {
		Maquina maquina = new Maquina("Lager", "Rubia suave", 0.03, "M-30");

		maquina.llenarMaquina();

		assertEquals(9900, maquina.getCantidadActual(), 0.0001);
	}

	@Test
	@DisplayName("recargarCerveza debe retornar true y sumar cantidad cuando no supera el límite permitido")
	public void testRecargarCervezaCorrecto() {
		Maquina maquina = new Maquina("IPA", "Amarga", 0.05, "M-40");

		boolean resultado = maquina.recargarCerveza(500);

		assertTrue(resultado);
		assertEquals(500, maquina.getCantidadActual(), 0.0001);
	}

	@Test
	@DisplayName("recargarCerveza debe retornar false cuando la cantidad supera el límite permitido")
	public void testRecargarCervezaExcedido() {
		Maquina maquina = new Maquina("IPA", "Amarga", 0.05, "M-41");

		boolean resultado = maquina.recargarCerveza(10000);

		assertFalse(resultado);
		assertEquals(0, maquina.getCantidadActual(), 0.0001);
	}

	@Test
	@DisplayName("servirCerveza debe descontar cantidad y retornar el valor cuando hay suficiente cerveza")
	public void testServirCervezaCorrecto() {
		Maquina maquina = new Maquina("Porter", "Oscura", 0.02, "M-50");
		maquina.llenarMaquina();

		double valor = maquina.servirCerveza(100);

		assertEquals(2.0, valor, 0.0001);
		assertEquals(9800, maquina.getCantidadActual(), 0.0001);
	}

	@Test
	@DisplayName("servirCerveza debe retornar cero cuando no hay suficiente cerveza")
	public void testServirCervezaSinCantidadSuficiente() {
		Maquina maquina = new Maquina("Porter", "Oscura", 0.02, "M-51");

		double valor = maquina.servirCerveza(100);

		assertEquals(0, valor, 0.0001);
		assertEquals(0, maquina.getCantidadActual(), 0.0001);
	}
}