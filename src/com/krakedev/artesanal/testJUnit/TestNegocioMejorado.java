package com.krakedev.artesanal.testJUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.krakedev.artesanal.Cliente;
import com.krakedev.artesanal.Maquina;
import com.krakedev.artesanal.NegocioMejorado;
import com.krakedev.artesanal.NegocioMejoradoPrueba;

public class TestNegocioMejorado {

	@Test
	@DisplayName("generarCodigo debe retornar un código con formato M-numero")
	public void testGenerarCodigoFormato() {
		NegocioMejorado negocio = new NegocioMejorado();

		String codigo = negocio.generarCodigo();

		assertNotNull(codigo);
		assertTrue(codigo.matches("M-\\d{1,3}"));
	}

	@Test
	@DisplayName("agregarMaquina debe agregar una máquina correctamente")
	public void testAgregarMaquina() {
		NegocioMejorado negocio = new NegocioMejorado();

		boolean resultado = negocio.agregarMaquina("IPA", "Cerveza amarga", 0.05);

		assertTrue(resultado);
		assertEquals(1, negocio.getMaquinas().size());

		Maquina maquina = negocio.getMaquinas().get(0);
		assertEquals("IPA", maquina.getNombreCerveza());
		assertEquals("Cerveza amarga", maquina.getDescripcion());
		assertEquals(0.05, maquina.getPrecioPorMl(), 0.0001);
		assertNotNull(maquina.getCodigo());
	}

	@Test
	@DisplayName("recuperarMaquina debe retornar la máquina cuando el código existe")
	public void testRecuperarMaquinaExistente() {
		NegocioMejorado negocio = new NegocioMejorado();
		Maquina maquina = new Maquina("Lager", "Rubia suave", 0.03, "M-10");
		negocio.getMaquinas().add(maquina);

		Maquina resultado = negocio.recuperarMaquina("M-10");

		assertNotNull(resultado);
		assertEquals("Lager", resultado.getNombreCerveza());
		assertEquals("M-10", resultado.getCodigo());
	}

	@Test
	@DisplayName("recuperarMaquina debe retornar null cuando el código no existe")
	public void testRecuperarMaquinaNoExistente() {
		NegocioMejorado negocio = new NegocioMejorado();

		Maquina resultado = negocio.recuperarMaquina("M-99");

		assertNull(resultado);
	}

	@Test
	@DisplayName("agregarMaquina debe retornar false si el código generado ya existe")
	public void testAgregarMaquinaDuplicada() {
		NegocioMejoradoPrueba negocio = new NegocioMejoradoPrueba();

		boolean primera = negocio.agregarMaquina("Amber", "Cerveza rojiza", 0.04);
		boolean segunda = negocio.agregarMaquina("Porter", "Cerveza oscura", 0.06);

		assertTrue(primera);
		assertFalse(segunda);
		assertEquals(1, negocio.getMaquinas().size());
	}

	@Test
	@DisplayName("cargarMaquinas debe llenar todas las máquinas hasta capacidad máxima menos 100")
	public void testCargarMaquinas() {
		NegocioMejorado negocio = new NegocioMejorado();
		negocio.getMaquinas().add(new Maquina("IPA", "Amarga", 0.05, "M-1"));
		negocio.getMaquinas().add(new Maquina("Stout", "Oscura", 0.07, "M-2"));

		negocio.cargarMaquinas();

		assertEquals(9900, negocio.getMaquinas().get(0).getCantidadActual(), 0.0001);
		assertEquals(9900, negocio.getMaquinas().get(1).getCantidadActual(), 0.0001);
	}

	@Test
	@DisplayName("registrarCliente debe crear cliente con código consecutivo")
	public void testRegistrarCliente() {
		NegocioMejorado negocio = new NegocioMejorado();

		negocio.registrarCliente("Ana", "1723456789");
		negocio.registrarCliente("Luis", "1711111111");

		Cliente c1 = negocio.buscarClientePorCedula("1723456789");
		Cliente c2 = negocio.buscarClientePorCedula("1711111111");

		assertNotNull(c1);
		assertNotNull(c2);
		assertEquals(100, c1.getCodigo());
		assertEquals(101, c2.getCodigo());
	}
 
	@Test
	@DisplayName("buscarClientePorCedula debe retornar el cliente correcto")
	public void testBuscarClientePorCedula() {
		NegocioMejorado negocio = new NegocioMejorado();
		negocio.registrarCliente("Carla", "1234567890");

		Cliente cliente = negocio.buscarClientePorCedula("1234567890");

		assertNotNull(cliente);
		assertEquals("Carla", cliente.getNombre());
	}

	@Test
	@DisplayName("buscarClientePorCedula debe retornar null si no encuentra coincidencia")
	public void testBuscarClientePorCedulaNoExiste() {
		NegocioMejorado negocio = new NegocioMejorado();

		Cliente cliente = negocio.buscarClientePorCedula("0000000000");

		assertNull(cliente);
	}

	@Test
	@DisplayName("buscarClientePorCodigo debe retornar el cliente correcto")
	public void testBuscarClientePorCodigo() {
		NegocioMejorado negocio = new NegocioMejorado();
		negocio.registrarCliente("Pedro", "1111111111");

		Cliente cliente = negocio.buscarClientePorCodigo(100);

		assertNotNull(cliente);
		assertEquals("Pedro", cliente.getNombre());
		assertEquals("1111111111", cliente.getCedula());
	}

	@Test
	@DisplayName("buscarClientePorCodigo debe retornar null si el código no existe")
	public void testBuscarClientePorCodigoNoExiste() {
		NegocioMejorado negocio = new NegocioMejorado();

		Cliente cliente = negocio.buscarClientePorCodigo(999);

		assertNull(cliente);
	}

	@Test
	@DisplayName("registrarConsumo debe acumular el valor consumido en el cliente")
	public void testRegistrarConsumo() {
		NegocioMejorado negocio = new NegocioMejorado();
		negocio.registrarCliente("Maria", "2222222222");

		negocio.registrarConsumo(100, 5.5);
		negocio.registrarConsumo(100, 3.0);

		Cliente cliente = negocio.buscarClientePorCodigo(100);

		assertNotNull(cliente);
		assertEquals(8.5, cliente.getTotalConsumido(), 0.0001);
	}

	@Test
	@DisplayName("consumirCerveza debe actualizar cliente, máquina y valor consumido correctamente")
	public void testConsumirCerveza() {
		NegocioMejorado negocio = new NegocioMejorado();

		Maquina maquina = new Maquina("Pilsener", "Suave", 0.02, "M-50");
		negocio.getMaquinas().add(maquina);
		maquina.llenarMaquina(); // queda en 9900

		negocio.registrarCliente("Jose", "3333333333");

		negocio.consumirCerveza(100, "M-50", 100);

		Cliente cliente = negocio.buscarClientePorCodigo(100);
		Maquina maquinaActualizada = negocio.recuperarMaquina("M-50");

		assertEquals(9800, maquinaActualizada.getCantidadActual(), 0.0001);
		assertEquals(2.0, cliente.getTotalConsumido(), 0.0001);
	}

	@Test
	@DisplayName("consultarValorVendido debe sumar el total consumido de todos los clientes")
	public void testConsultarValorVendido() {
		NegocioMejorado negocio = new NegocioMejorado();

		negocio.registrarCliente("A", "111");
		negocio.registrarCliente("B", "222");
		negocio.registrarCliente("C", "333");

		negocio.registrarConsumo(100, 10.0);
		negocio.registrarConsumo(101, 20.5);
		negocio.registrarConsumo(102, 4.5);

		double total = negocio.consultarValorVendido();

		assertEquals(35.0, total, 0.0001);
	}
	
	@Test
	@DisplayName("consumirCerveza debe descontar cerveza de la máquina")
	public void testConsumirCervezaDescuentaMaquina() {
		NegocioMejorado negocio = new NegocioMejorado();

		Maquina maquina = new Maquina("IPA", "Amarga", 0.02, "M-10");
		negocio.getMaquinas().add(maquina);
		maquina.llenarMaquina(); // 9900

		negocio.registrarCliente("Ana", "1111111111");

		negocio.consumirCerveza(100, "M-10", 100);

		Maquina maquinaRecuperada = negocio.recuperarMaquina("M-10");
		assertEquals(9800, maquinaRecuperada.getCantidadActual(), 0.0001);
	}

	@Test
	@DisplayName("consumirCerveza debe acumular el valor consumido en el cliente")
	public void testConsumirCervezaActualizaCliente() {
		NegocioMejorado negocio = new NegocioMejorado();

		Maquina maquina = new Maquina("Lager", "Suave", 0.05, "M-11");
		negocio.getMaquinas().add(maquina);
		maquina.llenarMaquina();

		negocio.registrarCliente("Luis", "2222222222");

		negocio.consumirCerveza(100, "M-11", 200);

		Cliente cliente = negocio.buscarClientePorCodigo(100);
		assertEquals(10.0, cliente.getTotalConsumido(), 0.0001);
	}

	@Test
	@DisplayName("consumirCerveza debe afectar correctamente máquina, cliente y total vendido")
	public void testConsumirCervezaCompleto() {
		NegocioMejorado negocio = new NegocioMejorado();

		Maquina maquina = new Maquina("Stout", "Oscura", 0.04, "M-12");
		negocio.getMaquinas().add(maquina);
		maquina.llenarMaquina(); // 9900

		negocio.registrarCliente("Carla", "3333333333");

		negocio.consumirCerveza(100, "M-12", 150);

		Cliente cliente = negocio.buscarClientePorCodigo(100);
		Maquina maquinaRecuperada = negocio.recuperarMaquina("M-12");
		double totalVendido = negocio.consultarValorVendido();

		assertEquals(9750, maquinaRecuperada.getCantidadActual(), 0.0001);
		assertEquals(6.0, cliente.getTotalConsumido(), 0.0001);
		assertEquals(6.0, totalVendido, 0.0001);
	}

	@Test
	@DisplayName("consumirCerveza no debe sumar consumo si la máquina no tiene suficiente cerveza")
	public void testConsumirCervezaSinStock() {
		NegocioMejorado negocio = new NegocioMejorado();

		Maquina maquina = new Maquina("Amber", "Rojiza", 0.03, "M-13");
		negocio.getMaquinas().add(maquina);

		negocio.registrarCliente("Pedro", "4444444444");

		negocio.consumirCerveza(100, "M-13", 100);

		Cliente cliente = negocio.buscarClientePorCodigo(100);
		Maquina maquinaRecuperada = negocio.recuperarMaquina("M-13");

		assertEquals(0, cliente.getTotalConsumido(), 0.0001);
		assertEquals(0, maquinaRecuperada.getCantidadActual(), 0.0001);
	}
}