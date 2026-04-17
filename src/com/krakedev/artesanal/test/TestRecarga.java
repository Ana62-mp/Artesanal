package com.krakedev.artesanal.test;

import com.krakedev.artesanal.Maquina;

public class TestRecarga {

	public static void main(String[] args) {
		Maquina rubia = new Maquina("Club", "cerveza verde",0.02,8000, "zzz2");
		boolean resultado;
		
		System.out.println("--------ESTADO INICIAL----------");
		rubia.imprimir();
		
		System.out.println("--------RECARGA 1----------");
		resultado = rubia.recargarCerveza(3000);
		System.out.println("Recarga hecha?: " + resultado);
		
		rubia.imprimir();
		
		System.out.println("--------RECARGA 2----------");
		resultado = rubia.recargarCerveza(5000);
		System.out.println("Recarga hecha?: " + resultado);
		
		rubia.imprimir();

	}

}
