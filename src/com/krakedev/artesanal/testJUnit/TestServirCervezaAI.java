package com.krakedev.artesanal.testJUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.krakedev.artesanal.Maquina;

public class TestServirCervezaAI {
	// tolerancia para comparar doubles
    private static final double TOLERANCIA = 0.0001;

    
    @Test
    public void testServirCervezaConCantidadSuficiente() {

        // Arrange
        // Se crea la máquina usando el constructor completo
        Maquina maquina = new Maquina("IPA", "Cerveza artesanal", 0.02, 5000, "hgs3");
        
        // llenar la máquina
        maquina.llenarMaquina();

        double cantidadInicial = maquina.getCantidadActual();

        // Act
        double valor = maquina.servirCerveza(200);

        // Assert
        // se verifica que el valor retornado sea cantidad * precio
        assertEquals(4.0, valor, TOLERANCIA);

        // se verifica que la cantidad actual haya disminuido
        assertEquals(cantidadInicial - 200, maquina.getCantidadActual(), TOLERANCIA);
    }

    
    @Test
    public void testServirCervezaCantidadExactaDisponible() {

        // Arrange
        Maquina maquina = new Maquina("Stout", "Oscura", 0.01, 1000, "zz6z");

        maquina.recargarCerveza(500);

        double cantidadInicial = maquina.getCantidadActual();

        // Act
        double valor = maquina.servirCerveza(cantidadInicial);

        // Assert
        // debe cobrar correctamente
        assertEquals(cantidadInicial * 0.01, valor, TOLERANCIA);

        // la cantidad restante debe quedar en 0
        assertEquals(0, maquina.getCantidadActual(), TOLERANCIA);
    }

    
    @Test
    public void testServirCervezaSinCantidadSuficiente() {

        // Arrange
        Maquina maquina = new Maquina("Lager", "Rubia", 0.02, "you7");

        maquina.recargarCerveza(100);

        double cantidadInicial = maquina.getCantidadActual();

        // Act
        double valor = maquina.servirCerveza(200);

        // Assert
        // no debe cobrar nada
        assertEquals(0, valor, TOLERANCIA);

        // la cantidad actual no debe cambiar
        assertEquals(cantidadInicial, maquina.getCantidadActual(), TOLERANCIA);
    }

    
    @Test
    public void testServirCervezaCuandoNoHayCerveza() {

        // Arrange
        Maquina maquina = new Maquina("Porter", "Negra", 0.03, "jky5");

        // no se recarga cerveza

        double cantidadInicial = maquina.getCantidadActual();

        // Act
        double valor = maquina.servirCerveza(100);

        // Assert
        // no debe cobrar nada
        assertEquals(0, valor, TOLERANCIA);

        // la cantidad debe seguir en 0
        assertEquals(cantidadInicial, maquina.getCantidadActual(), TOLERANCIA);
    }

    
    @Test
    public void testServirCervezaVariasVeces() {

        // Arrange
        Maquina maquina = new Maquina("Pale Ale", "Cerveza clara", 0.02, 3000, "uuy4");

        maquina.recargarCerveza(500);

        // Act
        double valor1 = maquina.servirCerveza(100);
        double valor2 = maquina.servirCerveza(200);

        // Assert
        assertEquals(2.0, valor1, TOLERANCIA);
        assertEquals(4.0, valor2, TOLERANCIA);

        // debe quedar 200 ml
        assertEquals(200, maquina.getCantidadActual(), TOLERANCIA);
    }
}
