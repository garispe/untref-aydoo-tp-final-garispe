package untref.aydoo.procesador;

import java.io.File;
import java.io.IOException;
import java.util.List;

import net.lingala.zip4j.exception.ZipException;

public class ProcesadorEstadistico {

	private ManejadorArchivos manejadorArchivos;
	private List<RecorridoPorBicicleta> recorridos;

	public ProcesadorEstadistico() {

		this.manejadorArchivos = new ManejadorArchivos();
	}

	public ManejadorArchivos getManejadorArchivos() {

		return this.manejadorArchivos;
	}

	public void cargarRecorridos(String dir) throws IOException, ZipException {

		this.recorridos = this.manejadorArchivos
				.obtenerRecorridos(new File(dir));
	}

	public int getBicicletaMasUsada() {

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
		return ID_bicicletaMasUsada;
	}

	public int getBicicletaMenosUsada() {

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
		return ID_bicicletaMenosUsada;
	}

	public double getTiempoPromedioUso() {

		double tiempoTotal = .0f;
		double tiempoPromedio = .0f;

		for (int i = 0; i < recorridos.size(); i++) {

			tiempoTotal = tiempoTotal + recorridos.get(i).getTiempoUso();
		}

		tiempoPromedio = tiempoTotal / recorridos.size();

		return (tiempoPromedio);
	}

	public String getRecorridoMasRealizado() {

		RecorridoPorBicicleta recorridoMasRealizado = null;
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
				recorridoMasRealizado = recorridoAuxiliar;
			}
		}
		return recorridoMasRealizado.getParOrigenDestino();
	}

	public Resultado getResultado() {

		int id_bicicletaMasUsada = getBicicletaMasUsada();
		int id_bicicletaMenosUsada = getBicicletaMenosUsada();
		String recorridoMasRealizado = getRecorridoMasRealizado();
		double tiempoPromedio = getTiempoPromedioUso();

		Resultado resultado = new Resultado(id_bicicletaMasUsada,
				id_bicicletaMenosUsada, recorridoMasRealizado, tiempoPromedio);

		return resultado;
	}

	public void generarYMLConResultado(String dir) throws IOException,
			ZipException {

		getManejadorArchivos().escribirYML(dir, getResultado());

	}
}
