package untref.aydoo.tests;

import java.io.IOException;

import junit.framework.Assert;
import net.lingala.zip4j.exception.ZipException;

import org.junit.Test;

import untref.aydoo.procesador.ProcesadorEstadistico;
import untref.aydoo.procesador.Resultado;

public class ProcesadorEstadisticoTest {

	@Test
	public void laBicicletaMasUsadaDeberiaSerLaEsperadaSegunLosRecorridosCargados()
			throws IOException, ZipException {

		ProcesadorEstadistico procesador = new ProcesadorEstadistico();
		procesador.cargarRecorridos("data/zip_prueba.zip");

		int ID_esparado = 1205;

		Assert.assertEquals(ID_esparado, procesador.getBicicletaMasUsada());
	}

	@Test
	public void laBicicletaMenosUsadaDeberiaSerLaEsperadaSegunLosRecorridosCargados()
			throws IOException, ZipException {

		ProcesadorEstadistico procesador = new ProcesadorEstadistico();
		procesador.cargarRecorridos("data/zip_prueba.zip");

		int ID_esparado = 1524;

		Assert.assertEquals(ID_esparado, procesador.getBicicletaMenosUsada());
	}

	@Test
	public void elTiempoPromedioDeberiaSerElEsperadoSegunLosRecorridosCargados()
			throws IOException, ZipException {

		ProcesadorEstadistico procesador = new ProcesadorEstadistico();
		procesador.cargarRecorridos("data/zip_prueba.zip");

		double tiempoEsperado = 29.88888888888889;

		Assert.assertEquals(tiempoEsperado, procesador.getTiempoPromedioUso());
	}

	@Test
	public void elRecorridoMasRealizadoDeberiaSerElEsperadoSegunLosRecorridosCargados()
			throws IOException, ZipException {

		ProcesadorEstadistico procesador = new ProcesadorEstadistico();
		procesador.cargarRecorridos("data/zip_prueba.zip");

		String recorridoEsperado = "20: ONCE - 7: PLAZA ROMA";

		Assert.assertEquals(recorridoEsperado,
				procesador.getRecorridoMasRealizado());
	}

	@Test
	public void elResultadoDeberiaSerElEsperado() throws IOException,
			ZipException {

		ProcesadorEstadistico procesador = new ProcesadorEstadistico();
		procesador.cargarRecorridos("data/zip_prueba.zip");

		int id_bicicletaMasUsadaEsparado = 1205;
		int id_bicicletaMenosUsadaEsperado = 1524;
		String recorridoMasRealizadoEsperado = "20: ONCE - 7: PLAZA ROMA";
		double tiempoPromedioEsperado = 29.88888888888889;

		Resultado resultado = procesador.getResultado();

		Assert.assertEquals(id_bicicletaMasUsadaEsparado,
				resultado.getID_bicicletaMasUsada());
		Assert.assertEquals(id_bicicletaMenosUsadaEsperado,
				resultado.getID_bicicletaMenosUsada());
		Assert.assertEquals(recorridoMasRealizadoEsperado,
				resultado.getRecorridoMasRealizado());
		Assert.assertEquals(tiempoPromedioEsperado,
				resultado.getTiempoPromedioUso());

	}
}
