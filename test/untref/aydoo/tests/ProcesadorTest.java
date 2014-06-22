package untref.aydoo.tests;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import untref.aydoo.procesador.ProcesadorEstadistico;
import untref.aydoo.procesador.RecorridoPorBicicleta;

public class ProcesadorTest {

	@Test
	public void elRecorridoMasRealizadoDeberiaSerElEsperadoSegunLosRecorridosCargados() {

		ProcesadorEstadistico procesador = new ProcesadorEstadistico();
		List<RecorridoPorBicicleta> recorridos = new ArrayList<RecorridoPorBicicleta>();

		RecorridoPorBicicleta recorrido1 = new RecorridoPorBicicleta();
		recorrido1.setID_estacionOrigen(1);
		recorrido1.setID_estacionDestino(10);

		RecorridoPorBicicleta recorrido2 = new RecorridoPorBicicleta();
		recorrido2.setID_estacionOrigen(2);
		recorrido2.setID_estacionDestino(20);

		RecorridoPorBicicleta recorrido3 = new RecorridoPorBicicleta();
		recorrido3.setID_estacionOrigen(3);
		recorrido3.setID_estacionDestino(30);

		RecorridoPorBicicleta recorrido4 = new RecorridoPorBicicleta();
		recorrido4.setID_estacionOrigen(1);
		recorrido4.setID_estacionDestino(10);

		RecorridoPorBicicleta recorrido5 = new RecorridoPorBicicleta();
		recorrido5.setID_estacionOrigen(4);
		recorrido5.setID_estacionDestino(40);

		recorridos.add(recorrido1);
		recorridos.add(recorrido2);
		recorridos.add(recorrido3);
		recorridos.add(recorrido4);
		recorridos.add(recorrido5);

		String resultadoEsperado = "1-10";

		Assert.assertEquals(resultadoEsperado,
				procesador.getRecorridoMasRealizado(recorridos));
	}

	@Test
	public void laBicicletaMasUsadaDeberiaSerLaEsperadaSegunLosRecorridosCargados() {

		ProcesadorEstadistico procesador = new ProcesadorEstadistico();
		List<RecorridoPorBicicleta> recorridos = new ArrayList<RecorridoPorBicicleta>();

		RecorridoPorBicicleta recorrido1 = new RecorridoPorBicicleta();
		recorrido1.setID_bicicleta(1);

		RecorridoPorBicicleta recorrido2 = new RecorridoPorBicicleta();
		recorrido2.setID_bicicleta(2);

		RecorridoPorBicicleta recorrido3 = new RecorridoPorBicicleta();
		recorrido3.setID_bicicleta(1);

		RecorridoPorBicicleta recorrido4 = new RecorridoPorBicicleta();
		recorrido4.setID_bicicleta(4);

		RecorridoPorBicicleta recorrido5 = new RecorridoPorBicicleta();
		recorrido5.setID_bicicleta(5);

		recorridos.add(recorrido1);
		recorridos.add(recorrido2);
		recorridos.add(recorrido3);
		recorridos.add(recorrido4);
		recorridos.add(recorrido5);

		int ID_esparado = 1;

		Assert.assertEquals(ID_esparado,
				procesador.getBicicletaMasUsada(recorridos));
	}

	@Test
	public void laBicicletaMenosUsadaDeberiaSerLaEsperadaSegunLosRecorridosCargados() {

		ProcesadorEstadistico procesador = new ProcesadorEstadistico();
		List<RecorridoPorBicicleta> recorridos = new ArrayList<RecorridoPorBicicleta>();

		RecorridoPorBicicleta recorrido1 = new RecorridoPorBicicleta();
		recorrido1.setID_bicicleta(3);

		RecorridoPorBicicleta recorrido2 = new RecorridoPorBicicleta();
		recorrido2.setID_bicicleta(3);

		RecorridoPorBicicleta recorrido3 = new RecorridoPorBicicleta();
		recorrido3.setID_bicicleta(2);

		RecorridoPorBicicleta recorrido4 = new RecorridoPorBicicleta();
		recorrido4.setID_bicicleta(4);

		RecorridoPorBicicleta recorrido5 = new RecorridoPorBicicleta();
		recorrido5.setID_bicicleta(4);

		recorridos.add(recorrido1);
		recorridos.add(recorrido2);
		recorridos.add(recorrido3);
		recorridos.add(recorrido4);
		recorridos.add(recorrido5);

		int ID_esparado = 2;

		Assert.assertEquals(ID_esparado,
				procesador.getBicicletaMenosUsada(recorridos));
	}

	@Test
	public void elTiempoDeberiaSerElEsperadoSegunLosRecorridosCargados() {

		ProcesadorEstadistico procesador = new ProcesadorEstadistico();
		List<RecorridoPorBicicleta> recorridos = new ArrayList<RecorridoPorBicicleta>();

		RecorridoPorBicicleta recorrido1 = new RecorridoPorBicicleta();
		recorrido1.setTiempoUso(15);

		RecorridoPorBicicleta recorrido2 = new RecorridoPorBicicleta();
		recorrido2.setTiempoUso(23);

		RecorridoPorBicicleta recorrido3 = new RecorridoPorBicicleta();
		recorrido3.setTiempoUso(65);

		RecorridoPorBicicleta recorrido4 = new RecorridoPorBicicleta();
		recorrido4.setTiempoUso(10);

		RecorridoPorBicicleta recorrido5 = new RecorridoPorBicicleta();
		recorrido5.setTiempoUso(8);

		recorridos.add(recorrido1);
		recorridos.add(recorrido2);
		recorridos.add(recorrido3);
		recorridos.add(recorrido4);
		recorridos.add(recorrido5);

		double tiempoEsperado = 24.2;

		Assert.assertEquals(tiempoEsperado,
				procesador.getTiempoPromedioUso(recorridos));
	}
}
