package untref.aydoo.procesador;

public class Resultado {

	private int ID_bicicletaMasUsada;
	private int ID_bicicletaMenosUsada;
	private String recorridoMasRealizado;
	private double tiempoPromedioUso;

	public Resultado(int ID_bicicletaMasUsada, int ID_bicicletaMenosUsada,
			String recorridoMasRealizada, double tiempoPromedioUso) {

		this.ID_bicicletaMasUsada = ID_bicicletaMasUsada;
		this.ID_bicicletaMenosUsada = ID_bicicletaMenosUsada;
		this.recorridoMasRealizado = recorridoMasRealizada;
		this.tiempoPromedioUso = tiempoPromedioUso;
	}

	public Resultado() {
	}

	public int getID_bicicletaMasUsada() {
		return ID_bicicletaMasUsada;
	}

	public void setID_bicicletaMasUsada(int iD_bicicletaMasUsada) {
		ID_bicicletaMasUsada = iD_bicicletaMasUsada;
	}

	public int getID_bicicletaMenosUsada() {
		return ID_bicicletaMenosUsada;
	}

	public void setID_bicicletaMenosUsada(int iD_bicicletaMenosUsada) {
		ID_bicicletaMenosUsada = iD_bicicletaMenosUsada;
	}

	public String getRecorridoMasRealizado() {
		return recorridoMasRealizado;
	}

	public void setRecorridoMasRealizado(String recorridoMasRealizado) {
		this.recorridoMasRealizado = recorridoMasRealizado;
	}

	public double getTiempoPromedioUso() {
		return tiempoPromedioUso;
	}

	public void setTiempoPromedioUso(double tiempoPromedioUso) {
		this.tiempoPromedioUso = tiempoPromedioUso;
	}
}