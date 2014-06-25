package untref.aydoo.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import net.lingala.zip4j.exception.ZipException;

import org.junit.Test;

import untref.aydoo.procesador.ManejadorArchivos;
import untref.aydoo.procesador.RecorridoPorBicicleta;

public class ManejadorArchivosTest {

	@Test
	public void verificarQueElTamanioDeLaListaDeRecorridosEsElEsperado()
			throws IOException, ZipException {

		ManejadorArchivos manejador = new ManejadorArchivos("data");

		List<RecorridoPorBicicleta> recorridos = new ArrayList<RecorridoPorBicicleta>();

		recorridos = manejador.cargarRecorridos();

		// Zip de prueba con 10 registros, por el salteo del encabezado es 9
		int cantidadEsperada = 9;

		Assert.assertEquals(cantidadEsperada, recorridos.size());
	}
}