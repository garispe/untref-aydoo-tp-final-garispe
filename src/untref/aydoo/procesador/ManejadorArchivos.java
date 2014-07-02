package untref.aydoo.procesador;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import org.ho.yaml.Yaml;

import au.com.bytecode.opencsv.CSVReader;

public class ManejadorArchivos {

	File directorio;

	public ManejadorArchivos(String rutaDir) {

		this.directorio = new File(rutaDir);
	}

	public void escribirYML(Resultado resultado) throws IOException {

		Yaml.dump(resultado, new File(directorio.getPath() + "/resultado.yml"));
	}

	public void descomprimirZIP(File archivo) throws IOException, ZipException {

		ZipFile zip = new ZipFile(archivo);
		zip.extractAll(directorio.getPath());
		System.out.println("*** [Extraccion de Archivos finalizada] ***\n");
	}

	public List<File> getListaZIPs() {

		File[] archivos = directorio.listFiles();
		List<File> ZIPs = new ArrayList<File>();

		for (int i = 0; i < archivos.length; i++) {

			if (archivos[i].isFile() && archivos[i].getName().endsWith(".zip")) {

				ZIPs.add(archivos[i]);
			}
		}

		return ZIPs;
	}

	public List<RecorridoDeBicicleta> cargarRecorridos() throws ZipException,
			IOException {

		File[] archivos;
		List<RecorridoDeBicicleta> recorridos = new ArrayList<RecorridoDeBicicleta>();

		archivos = directorio.listFiles();

		for (int i = 0; i < archivos.length; i++) {

			if (archivos[i].isFile() && archivos[i].getName().endsWith(".zip")) {

				recorridos.addAll(this.obtenerRecorridos(archivos[i]));
			}
		}

		return recorridos;
	}

	public List<RecorridoDeBicicleta> obtenerRecorridos(File archivoZip)
			throws IOException, ZipException {

		List<RecorridoDeBicicleta> recorridos = new ArrayList<RecorridoDeBicicleta>();
		File[] archivos;

		descomprimirZIP(archivoZip);

		archivos = directorio.listFiles();

		for (int i = 0; i < archivos.length; i++) {

			if (archivos[i].isFile() && archivos[i].getName().endsWith(".csv")) {

				recorridos.addAll(this.parsearCSV(archivos[i]));
			}
		}

		return recorridos;

	}

	public List<RecorridoDeBicicleta> parsearCSV(File archivo)
			throws IOException, ZipException {

		List<RecorridoDeBicicleta> recorridos = new ArrayList<RecorridoDeBicicleta>();

		CSVReader reader = new CSVReader(new FileReader(archivo), ';');

		// Salteo la primer fila
		reader.readNext();

		String[] linea;

		while ((linea = reader.readNext()) != null) {

			RecorridoDeBicicleta recorrido = new RecorridoDeBicicleta();

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