package untref.aydoo.tests;

import java.io.File;
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

		// El registro recortado posee 15000 registros
		File archivo = new File("data/recorridos-15000.zip");

		ManejadorArchivos manejador = new ManejadorArchivos();

		List<RecorridoPorBicicleta> recorridos = new ArrayList<RecorridoPorBicicleta>();

		recorridos = manejador.obtenerRecorridos(archivo);
		
		// Por el salteo del encabezado
		int cantidadEsperada = 14999;

		Assert.assertEquals(cantidadEsperada, recorridos.size());
	}
}