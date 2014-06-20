package untref.aydoo.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import untref.aydoo.procesador.ManejadorArchivos;
import untref.aydoo.procesador.RecorridoPorBicicleta;

public class ManejadorArchivosTest {

	@Test
	public void verificarQueElTamañoDeLaListaDeRecorridosEsElEsperado()
			throws IOException {

		// El registro recortado posee 15000 registros
		String ruta = "data/recorridos-recortado.csv";
		
		ManejadorArchivos manejador = new ManejadorArchivos();

		List<RecorridoPorBicicleta> recorridos = new ArrayList<RecorridoPorBicicleta>();

		recorridos = manejador.obtenerRecorridos(ruta);

		// Por el salteo del encabezado
		int cantidadEsperada = 14999;

		Assert.assertEquals(cantidadEsperada, recorridos.size());
	}
}