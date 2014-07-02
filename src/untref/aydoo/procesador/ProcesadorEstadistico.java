package untref.aydoo.procesador;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.lingala.zip4j.exception.ZipException;

public class ProcesadorEstadistico {

	private ManejadorArchivos manejadorArchivos;
	private List<RecorridoDeBicicleta> recorridos;
	private boolean daemon;

	public ProcesadorEstadistico(String rutaDirectorio) {

		this.manejadorArchivos = new ManejadorArchivos(rutaDirectorio);
		this.recorridos = new ArrayList<RecorridoDeBicicleta>();
	}

	public void cargarRecorridos() throws IOException, ZipException {

		this.recorridos = this.manejadorArchivos.cargarRecorridos();
	}

	public void setDaemon(boolean daemon) {

		this.daemon = daemon;
	}

	public boolean esModoDaemon() {

		return this.daemon;
	}

	public int getBicicletaMasUsada() {

		int ID_bicicletaMasUsada = 0;
		int maximo = 0;
		int ID_auxiliar = 0;
		int cantidadIDIguales;

		for (int i = 0; i < recorridos.size(); i++) {

			ID_auxiliar = recorridos.get(i).getID_bicicleta();
			cantidadIDIguales = -1;

			for (RecorridoDeBicicleta recorrido : recorridos) {

				if (recorrido.getID_bicicleta() == ID_auxiliar) {

					cantidadIDIguales++;
				}
			}

			if (cantidadIDIguales > maximo) {

				maximo = cantidadIDIguales;
				ID_bicicletaMasUsada = ID_auxiliar;

			}
		}
		return ID_bicicletaMasUsada;
	}

	public int getBicicletaMenosUsada() {

		int ID_bicicletaMenosUsada = 0;
		int minimo = recorridos.size();
		int ID_auxiliar = 0;
		int cantidadIDIguales;

		for (int i = 0; i < recorridos.size(); i++) {

			ID_auxiliar = recorridos.get(i).getID_bicicleta();
			cantidadIDIguales = -1;

			// for (int j = 0; j < recorridos.size(); j++) {

			for (RecorridoDeBicicleta recorrido : recorridos) {

				if (recorrido.getID_bicicleta() == ID_auxiliar) {

					cantidadIDIguales++;
				}
			}

			if (cantidadIDIguales < minimo) {

				minimo = cantidadIDIguales;
				ID_bicicletaMenosUsada = ID_auxiliar;
			}
		}
		return ID_bicicletaMenosUsada;
	}

	public double getTiempoPromedioUso() {

		double tiempoTotal = .0f;
		double tiempoPromedio = .0f;

		for (RecorridoDeBicicleta recorrido : recorridos) {

			tiempoTotal = tiempoTotal + recorrido.getTiempoUso();
		}

		tiempoPromedio = tiempoTotal / recorridos.size();

		return tiempoPromedio;
	}

	public String getRecorridoMasRealizado() {

		RecorridoDeBicicleta recorridoMasRealizado = null;
		RecorridoDeBicicleta recorridoAuxiliar;
		int maximo = 0;
		int cantidadRecorridosIguales;

		for (int i = 0; i < recorridos.size(); i++) {

			recorridoAuxiliar = recorridos.get(i);
			cantidadRecorridosIguales = -1;

			for (RecorridoDeBicicleta recorrido : recorridos) {

				if (recorrido.getID_estacionOrigen() == recorridoAuxiliar
						.getID_estacionOrigen()
						&& recorrido.getID_estacionDestino() == recorridoAuxiliar
								.getID_estacionDestino()) {

					cantidadRecorridosIguales++;
				}
			}

			if (cantidadRecorridosIguales > maximo) {

				maximo = cantidadRecorridosIguales;
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

	public void generarYMLConResultado(Resultado resultado) throws IOException,
			ZipException {

		this.manejadorArchivos.escribirYML(resultado);

	}

	public void borrarRecorridosCargados() {

		this.recorridos.clear();
	}

	public void modoDaemon() throws IOException, ZipException {

		this.recorridos = new ArrayList<RecorridoDeBicicleta>();
		List<File> ZIPs = this.manejadorArchivos.getListaZIPs();

		Iterator<File> itZips = ZIPs.iterator();

		while (itZips.hasNext()) {

			File archivoZip = itZips.next();

			this.recorridos.addAll(this.manejadorArchivos
					.obtenerRecorridos(archivoZip));

			Resultado resultado = this.getResultado();

			this.generarYMLConResultado(resultado);

			this.borrarRecorridosCargados();
		}
	}

	public void modoOnDemand() throws IOException, ZipException {

		this.cargarRecorridos();

		Resultado resultado = this.getResultado();

		this.generarYMLConResultado(resultado);
	}
}
