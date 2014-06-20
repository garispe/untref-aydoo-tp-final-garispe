package untref.aydoo.procesador;

import java.io.File;
import java.io.IOException;
import java.util.List;

import net.lingala.zip4j.exception.ZipException;

public class ProcesadorEstadistico {

	private ManejadorArchivos manejadorArchivos;
	private Resultado resultado;

	public ProcesadorEstadistico() {

		this.manejadorArchivos = new ManejadorArchivos();
	}

	public ManejadorArchivos getManejadorArchivos() {

		return this.manejadorArchivos;
	}

	public int getBicicletaMasUsada(List<RecorridoPorBicicleta> recorridos) {

		int ID_bicicletaMasUsada = 0;
		int maximo = 0;
		int ID_auxiliar = 0;
		int cantidadIguales;

		for (int i = 0; i < recorridos.size(); i++) {

			ID_auxiliar = recorridos.get(i).getID_bicicleta();
			cantidadIguales = -1;

			for (int j = 0; j < recorridos.size(); j++) {

				if (recorridos.get(j).getID_bicicleta() == ID_auxiliar) {

					cantidadIguales++;
				}
			}

			if (cantidadIguales > maximo) {

				maximo = cantidadIguales;
				ID_bicicletaMasUsada = ID_auxiliar;

			}
			// COMPARAR POR TIEMPOS EN CASO DE SER IGUALES, O CANTIDAD = 0
		}

		return ID_bicicletaMasUsada;
	}

	public int getBicicletaMenosUsada(List<RecorridoPorBicicleta> recorridos) {

		int id_bicicletaMenosUsada = 0;
		int minimo = recorridos.size();
		int ID_auxiliar;
		int cantidadIguales;

		for (int i = 0; i < recorridos.size(); i++) {

			ID_auxiliar = recorridos.get(i).getID_bicicleta();
			cantidadIguales = -1;

			for (int j = 0; j < recorridos.size(); j++) {

				if (recorridos.get(j).getID_bicicleta() == ID_auxiliar) {

					cantidadIguales++;
				}
			}

			if (cantidadIguales < minimo) {

				minimo = cantidadIguales;
				id_bicicletaMenosUsada = ID_auxiliar;
			}

			// COMPARAR POR TIEMPOS EN CASO DE SER IGUALES, O CANTIDAD = 0
		}

		return id_bicicletaMenosUsada;
	}

	public double getTiempoPromedioUso(List<RecorridoPorBicicleta> recorridos) {

		double tiempoTotal = 0.0;

		for (int i = 0; i < recorridos.size(); i++) {

			tiempoTotal = tiempoTotal + recorridos.get(i).getTiempoUso();
		}

		return tiempoTotal / recorridos.size();
	}

	public String getRecorridoMasRealizado(
			List<RecorridoPorBicicleta> recorridos) {

		String recorridoMasRealizado = "---";
		RecorridoPorBicicleta recorridoAuxiliar;
		int maximo = 0;
		int cantidadIguales;

		for (int i = 0; i < recorridos.size(); i++) {

			recorridoAuxiliar = recorridos.get(i);
			cantidadIguales = -1;

			for (int j = 0; j < recorridos.size(); j++) {

				if (recorridos.get(j).getID_estacionOrigen() == recorridoAuxiliar
						.getID_estacionOrigen()
						&& recorridos.get(j).getID_estacionDestino() == recorridoAuxiliar
								.getID_estacionDestino()) {

					cantidadIguales++;
				}
			}

			if (cantidadIguales > maximo) {

				maximo = cantidadIguales;
				recorridoMasRealizado = recorridoAuxiliar.getParOrigenDestino();
			}
		}

		return recorridoMasRealizado;
	}

	public void imprimirResultado() throws IOException, ZipException {

		String rutaSalida = "data/test.yml";
		File archivo = new File("data/recorridos-2013-aux.zip");

		List<RecorridoPorBicicleta> recorridos = getManejadorArchivos()
				.obtenerRecorridos(archivo);

		resultado = new Resultado();

		resultado.setID_bicicletaMasUsada(getBicicletaMasUsada(recorridos));

		resultado.setID_bicicletaMenosUsada(getBicicletaMenosUsada(recorridos));

		resultado.setTiempoPromedioUso(getTiempoPromedioUso(recorridos));

		resultado.setRecorridoMasRealizado(getRecorridoMasRealizado(recorridos));

		getManejadorArchivos().escribirYML(rutaSalida, resultado);

	}

	public Resultado getResultado() {

		return this.resultado;
	}
}
