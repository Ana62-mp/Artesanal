package com.krakedev.artesanal.testNegocio;

import com.krakedev.artesanal.Maquina;
import com.krakedev.artesanal.Negocio;

public class TestNegocio {

	public static void main(String[] args) {
		Maquina nuevaMaquina = new Maquina("Cerveza Club","Bien fría",0.02,"45BGx");
		Negocio negocio1 = new Negocio("Mi negocio",nuevaMaquina);
		System.out.println("Nombre: "+negocio1.getNombre());
		System.out.println("Maquina: "+negocio1.getMaquinaA());
		
		Maquina m1 = negocio1.getMaquinaA();
		double capacidadMaxima = m1.getCapacidadMaxima();
		
	}

}
