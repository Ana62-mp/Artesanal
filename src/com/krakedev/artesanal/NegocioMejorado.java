package com.krakedev.artesanal;

import java.util.ArrayList;

public class NegocioMejorado {
	ArrayList<Maquina> maquinas;
	ArrayList<Cliente> clientes;
	private int ultimoCodigo=100;
	
	
	

	public NegocioMejorado() {
		maquinas = new ArrayList<>();
		clientes = new ArrayList<>();
	}

	public ArrayList<Maquina> getMaquinas() {
		return maquinas;
	}

	public void setMaquinas(ArrayList<Maquina> maquinas) {
		this.maquinas = maquinas;
	}
	
	public String generarCodigo() {
	    int numero = (int)(Math.random() * 100) + 1;
	    return "M-" + numero;
	}
	
	public boolean agregarMaquina(String nombreCer, String desc, double precioxMl) {
		String codigo = generarCodigo();
		Maquina maquina = recuperarMaquina(codigo);
		if(maquina == null) {
			Maquina maquinaNueva = new Maquina(nombreCer, desc, precioxMl,codigo);
			maquinas.add(maquinaNueva);
			return true;
		}else {
			return false;
		}
	}
	
	
	public void cargarMaquinas() {
		for(int i=0;i<maquinas.size();i++) {
			maquinas.get(i).llenarMaquina();
		}
	}
	
	
	public Maquina recuperarMaquina(String codigoM) {
		for(Maquina maquina : maquinas) {
			if(maquina.getCodigo().equals(codigoM)) {
				return maquina;
			}
		}
		return null;
	}
	
	public void registrarCliente(String nombre, String cedula) {
		Cliente cliente = new Cliente(nombre, cedula);
		cliente.setCodigo(ultimoCodigo);
		ultimoCodigo++;
		clientes.add(cliente);
	}
	
	
	public Cliente buscarClientePorCedula(String cedula) {
		for(int i=0;i<clientes.size();i++) {
			if(clientes.get(i).getCedula().equals(cedula)) {
				return clientes.get(i);
			}
		}
		return null;
	}
	
	public Cliente buscarClientePorCodigo(int codigo) {
		for(int i=0;i<clientes.size();i++) {
			if(clientes.get(i).getCodigo() == codigo) {
				return clientes.get(i);
			}
		}
		return null;
	}
	
	public void consumirCerveza(int codigoC, String codigoM, double cantidad) {
		Maquina maquinaR = recuperarMaquina(codigoM);
		Cliente clienteR = buscarClientePorCodigo(codigoC);

		if(maquinaR != null && clienteR != null) {
			double valorPagar = maquinaR.servirCerveza(cantidad);
			registrarConsumo(codigoC, valorPagar);
		}
	}
	
	public void registrarConsumo(int codigo, double valor) {
		Cliente cliente = buscarClientePorCodigo(codigo);
		double totalConsumido = cliente.getTotalConsumido()+valor;
		cliente.setTotalConsumido(totalConsumido);
	}
	
	public double consultarValorVendido() {
		double acumuladoConsumos=0;
		for(int i=0;i<clientes.size();i++) {
			acumuladoConsumos += clientes.get(i).getTotalConsumido();
		}
		return acumuladoConsumos;
	}
	
}
