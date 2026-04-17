package com.krakedev.artesanal.testJUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.krakedev.artesanal.Maquina;

public class TestRecargaJUnit {
	@Test
    public void testRecargaExitosa() {

        Maquina rubia = new Maquina("Pilsener", "cerveza", 0.02, 8000, "zzz2");

        boolean resultado = rubia.recargarCerveza(3000);

        assertTrue(resultado);
        assertEquals(3000, rubia.getCantidadActual(), 0.0001);
    }
    @Test
    public void testRecargaFallidaPorDesborde() {

        Maquina negra = new Maquina("Club", "cerveza fría", 0.03, 8000, "zzz2");
        
        negra.recargarCerveza(7000);

        boolean resultado = negra.recargarCerveza(3000);

        assertTrue(resultado);
        assertEquals(3000, negra.getCantidadActual(), 0.0001);
    }
}