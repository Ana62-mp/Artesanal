package com.krakedev.artesanal.test;

import com.krakedev.artesanal.Maquina;

public class TestLlenar {

	public static void main(String[] args) {
		Maquina rubia = new Maquina("Club", "cerveza verde",0.02,8000, "zzz");
		Maquina negra = new Maquina("Pilsener", "cerveza café",0.03, "zzz");
		rubia.imprimir();
		System.out.println("------");
		rubia.llenarMaquina();
		rubia.imprimir();
		System.out.println("------");
		negra.imprimir();
		negra.llenarMaquina();
		System.out.println("------");
		negra.imprimir();

	}

}
