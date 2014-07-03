package untref.aydoo.procesador;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.lingala.zip4j.exception.ZipException;

public class ProcesadorEstadistico {

	private ManejadorArchivos manejadorArchivos;
	private ManejadorYML manejadorYML;
	private List<RecorridoDeBicicleta> recorridos;
	private boolean daemon;

	public ProcesadorEstadistico(String rutaDirectorio) {

		this.manejadorArchivos = new ManejadorArchivos(rutaDirectorio);
		this.manejadorYML = new ManejadorYML(rutaDirectorio);
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

		Map<Integer, Integer> cantidadVecesUsada = new HashMap<Integer, Integer>();
		Integer ID_bicicletaMasUsada = 0;
		Integer maximo = 0;
		Integer ID_auxiliar;
		Integer cantidadIDIguales;

		for (int i = 0; i < recorridos.size(); i++) {

			ID_auxiliar = recorridos.get(i).getID_bicicleta();
			cantidadIDIguales = cantidadVecesUsada.get(ID_auxiliar);

			if (cantidadIDIguales != null) {

				cantidadIDIguales++;

			} else {

				cantidadIDIguales = 1;
			}

			cantidadVecesUsada.put(ID_auxiliar, cantidadIDIguales);

			if (cantidadIDIguales > maximo) {

				maximo = cantidadIDIguales;
				ID_bicicletaMasUsada = ID_auxiliar;
			}
		}

		return ID_bicicletaMasUsada;
	}

	public int getBicicletaMenosUsada() {

		Map<Integer, Integer> cantidadVecesUsada = new HashMap<Integer, Integer>();
		Integer ID_bicicletaMenosUsada = 0;
		Integer minimo = recorridos.size();
		Integer ID_auxiliar = 0;
		Integer cantidadIDIguales;

		for (int i = 0; i < recorridos.size(); i++) {

			ID_auxiliar = recorridos.get(i).getID_bicicleta();
			cantidadIDIguales = cantidadVecesUsada.get(ID_auxiliar);

			if (cantidadIDIguales != null) {

				cantidadIDIguales++;

			} else {

				cantidadIDIguales = 1;
			}

			cantidadVecesUsada.put(ID_auxiliar, cantidadIDIguales);

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

		Map<String, Integer> cantidadVecesRealizado = new HashMap<String, Integer>();
		String recorridoMasRealizado = null;
		String recorridoAuxiliar;
		Integer maximo = 0;
		Integer cantidadRecorridosIguales;

		for (int i = 0; i < recorridos.size(); i++) {

			recorridoAuxiliar = recorridos.get(i).getParOrigenDestino();
			cantidadRecorridosIguales = cantidadVecesRealizado
					.get(recorridoAuxiliar);

			if (cantidadRecorridosIguales != null) {

				cantidadRecorridosIguales++;

			} else {

				cantidadRecorridosIguales = 1;
			}

			cantidadVecesRealizado.put(recorridoAuxiliar,
					cantidadRecorridosIguales);

			if (cantidadRecorridosIguales > maximo) {

				maximo = cantidadRecorridosIguales;
				recorridoMasRealizado = recorridoAuxiliar;
			}
		}

		return recorridoMasRealizado;
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

		this.manejadorYML.escribirYML(resultado);

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
