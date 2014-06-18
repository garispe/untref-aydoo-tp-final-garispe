package untref.aydoo.procesador;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.ho.yaml.Yaml;

import au.com.bytecode.opencsv.CSVReader;

public class ManejadorArchivos {

	public void escribirYML(String rutaSalida, Resultado resultado) throws IOException {

		Yaml.dump(resultado, new File(rutaSalida));
	}

	public List<RecorridoPorBicicleta> leerCSV(String ruta) throws IOException {

		CSVReader reader = new CSVReader(new FileReader(ruta), ';');

		List<RecorridoPorBicicleta> recorridos = new ArrayList<RecorridoPorBicicleta>();

		// Linea por linea
		String[] linea;

		// Salteo la primer fila
		 reader.readNext();

		while ((linea = reader.readNext()) != null) {

			RecorridoPorBicicleta recorrido = new RecorridoPorBicicleta();

			try {

				recorrido.setID_usuario(Integer.parseInt(linea[0]));
				recorrido.setID_bicicleta(Integer.parseInt(linea[1]));

				recorrido.setFechaOrigen(linea[2]);
				recorrido.setID_estacionOrigen(Integer.parseInt(linea[3]));
				recorrido.setNombreOrigen(linea[4]);

				recorrido.setFechaDestino(linea[5]);
				recorrido.setID_estacionDestino(Integer.parseInt(linea[6]));
				recorrido.setNombreDestino(linea[7]);

				recorrido.setTiempoUso(Integer.parseInt(linea[8]));

				recorridos.add(recorrido);

				// Por lineas con errores en ultimo campo
			} catch (NumberFormatException e) {

				recorrido.setTiempoUso(0);
			}
		}

		reader.close();

		return recorridos;
	}
}