package com.krakedev.artesanal.test;

import com.krakedev.artesanal.Maquina;

public class TestServir {

	public static void main(String[] args) {
		Maquina rubia = new Maquina("Club", "cerveza verde",0.02,8000, "zzz2");
		boolean resultado;
		System.out.println("--------ESTADO INICIAL----------");
		rubia.imprimir();
		
		System.out.println("--------RECARGA 1----------");
		resultado = rubia.recargarCerveza(3000);
		System.out.println("Recarga hecha?: " + resultado);
		
		System.out.println("--------Servir 1000ml----------");
		double valor;
		valor = rubia.servirCerveza(1000);
		System.out.println("Valor a Pagar: "+valor);
		rubia.imprimir();
		
		System.out.println("--------Servir 500ml----------");
		
		valor = rubia.servirCerveza(500);
		System.out.println("Valor a Pagar: "+valor);
		rubia.imprimir();
	}

}
