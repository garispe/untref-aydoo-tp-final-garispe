package untref.aydoo.procesador;

import java.util.List;

public class ProcesadorEstadistico {

	private ManejadorArchivos manejadorArchivos;

	public ProcesadorEstadistico() {

		this.manejadorArchivos = new ManejadorArchivos();
	}

	public ManejadorArchivos getManejadorArchivos() {

		return this.manejadorArchivos;
	}

	public int getBicicletaMasUsada(List<RecorridoPorBicicleta> recorridos) {

		int id_bicicletaMasUsada = 0;
		int maximo = recorridos.get(0).getTiempoUso();

		for (int i = 0; i < recorridos.size(); i++) {

			if (recorridos.get(i).getTiempoUso() >= maximo) {

				maximo = recorridos.get(i).getTiempoUso();
				id_bicicletaMasUsada = recorridos.get(i).getID_bicicleta();
			}
		}

		return id_bicicletaMasUsada;
	}

	public int getBicicletaMenosUsada(List<RecorridoPorBicicleta> recorridos) {

		int id_bicicletaMenosUsada = 0;
		int minimo = recorridos.get(0).getTiempoUso();

		for (int i = 0; i < recorridos.size(); i++) {

			if (recorridos.get(i).getTiempoUso() <= minimo) {

				minimo = recorridos.get(i).getTiempoUso();
				id_bicicletaMenosUsada = recorridos.get(i).getID_bicicleta();
			}
		}

		return id_bicicletaMenosUsada;
	}

	public double getTiempoPromedioUso(List<RecorridoPorBicicleta> recorridos) {

		double tiempoPromedio = 0.0;
		int totalRecorridos = recorridos.size();

		for (int i = 0; i < recorridos.size(); i++) {

			tiempoPromedio = tiempoPromedio + recorridos.get(i).getTiempoUso();
		}

		return tiempoPromedio / totalRecorridos;
	}
}
