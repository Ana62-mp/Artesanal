package com.krakedev.artesanal.testJUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.krakedev.artesanal.Maquina;

public class TestServirCervezaAI {

    private static final double TOLERANCIA = 0.0001;

    @Test
    public void testServirCervezaConCantidadSuficiente() {
        // Valida que si hay suficiente cerveza, se descuente la cantidad
        // y se retorne correctamente el valor a pagar
        Maquina maquina = new Maquina("IPA", "Cerveza artesanal", 0.02, 5000, "M001");
        maquina.llenarMaquina();

        double cantidadInicial = maquina.getCantidadActual();

        double valor = maquina.servirCerveza(200);

        assertEquals(4.0, valor, TOLERANCIA);
        assertEquals(cantidadInicial - 200, maquina.getCantidadActual(), TOLERANCIA);
    }

    @Test
    public void testServirCervezaCantidadExactaDisponible() {
        // Valida que si se sirve exactamente toda la cerveza disponible,
        // la cantidad actual quede en 0 y el valor sea correcto
        Maquina maquina = new Maquina("Stout", "Oscura", 0.01, 1000, "M002");
        maquina.recargarCerveza(500);

        double cantidadInicial = maquina.getCantidadActual();

        double valor = maquina.servirCerveza(cantidadInicial);

        assertEquals(cantidadInicial * 0.01, valor, TOLERANCIA);
        assertEquals(0, maquina.getCantidadActual(), TOLERANCIA);
    }

    @Test
    public void testServirCervezaSinCantidadSuficiente() {
        // Valida que si no hay suficiente cerveza, no se sirva nada,
        // retorne 0 y no cambie la cantidad actual
        Maquina maquina = new Maquina("Lager", "Rubia", 0.02, "M003");
        maquina.recargarCerveza(100);

        double cantidadInicial = maquina.getCantidadActual();

        double valor = maquina.servirCerveza(200);

        assertEquals(0, valor, TOLERANCIA);
        assertEquals(cantidadInicial, maquina.getCantidadActual(), TOLERANCIA);
    }

    @Test
    public void testServirCervezaCuandoNoHayCerveza() {
        // Valida que si la máquina está vacía, no se sirva nada,
        // retorne 0 y la cantidad actual permanezca en 0
        Maquina maquina = new Maquina("Porter", "Negra", 0.03, "M004");

        double cantidadInicial = maquina.getCantidadActual();

        double valor = maquina.servirCerveza(100);

        assertEquals(0, valor, TOLERANCIA);
        assertEquals(cantidadInicial, maquina.getCantidadActual(), TOLERANCIA);
    }

    @Test
    public void testServirCervezaVariasVeces() {
        // Valida que la máquina pueda servir varias veces seguidas
        // descontando correctamente y calculando bien cada valor
        Maquina maquina = new Maquina("Pale Ale", "Cerveza clara", 0.02, 3000, "M005");
        maquina.recargarCerveza(500);

        double valor1 = maquina.servirCerveza(100);
        double valor2 = maquina.servirCerveza(200);

        assertEquals(2.0, valor1, TOLERANCIA);
        assertEquals(4.0, valor2, TOLERANCIA);
        assertEquals(200, maquina.getCantidadActual(), TOLERANCIA);
    }
}