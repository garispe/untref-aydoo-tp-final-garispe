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
		}

		System.out.println(ID_bicicletaMasUsada);
		return ID_bicicletaMasUsada;
	}

	public int getBicicletaMenosUsada(List<RecorridoPorBicicleta> recorridos) {

		int ID_bicicletaMenosUsada = 0;
		int minimo = recorridos.size();
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

			if (cantidadIguales < minimo) {

				minimo = cantidadIguales;
				ID_bicicletaMenosUsada = ID_auxiliar;
			}
		}

		System.out.println(ID_bicicletaMenosUsada);
		return ID_bicicletaMenosUsada;
	}

	public float getTiempoPromedioUso(List<RecorridoPorBicicleta> recorridos) {

		float tiempoTotal = .0f;
		float tiempoPromedio = .0f;

		for (int i = 0; i < recorridos.size(); i++) {

			tiempoTotal = tiempoTotal + recorridos.get(i).getTiempoUso();
		}

		tiempoPromedio = tiempoTotal / recorridos.size();

		System.out.println(tiempoPromedio);
		return (tiempoPromedio);
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

		System.out.println(recorridoMasRealizado);
		return recorridoMasRealizado;
	}

	public void imprimirResultado() throws IOException, ZipException {

		String rutaSalida = "data/test.yml";
		File archivo = new File("data/recorridos-5000.zip");
		List<RecorridoPorBicicleta> recorridos = getManejadorArchivos()
				.obtenerRecorridos(archivo);

		resultado = new Resultado();

		resultado.setID_bicicletaMasUsada(getBicicletaMasUsada(recorridos));

		resultado.setID_bicicletaMenosUsada(getBicicletaMenosUsada(recorridos));

		resultado.setTiempoPromedioUso(getTiempoPromedioUso(recorridos));

		resultado
				.setRecorridoMasRealizado(getRecorridoMasRealizado(recorridos));

		getManejadorArchivos().escribirYML(rutaSalida, resultado);

	}
}
