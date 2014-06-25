package untref.aydoo.procesador;

import java.io.IOException;

import net.lingala.zip4j.exception.ZipException;

public class ProcesadorMain {

	private static ProcesadorEstadistico procesador;

	public static void main(String[] args) throws IOException, ZipException {

		boolean daemon = false;
		String directorio = "";

		if (args.length > 0) {

			directorio = args[0];

			if (args[1].equals("-d") || args[1].equals("-daemon")) {

				daemon = true;

			} else {

				daemon = false;
			}

		} else {

			System.out.println("Parametros no especificados");
		}

		procesador = new ProcesadorEstadistico(directorio);
		procesador.setDaemon(daemon);

		if (daemon) {

			procesador.modoDaemon();

		} else {

			procesador.modoOnDemand();
		}
	}

	public static ProcesadorEstadistico getProcesadorEstadistico() {

		return procesador;
	}
}
