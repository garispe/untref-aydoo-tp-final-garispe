package untref.aydoo.procesador;

public class Resultado {

	private int ID_bicicletaMasUsada;
	private int ID_bicicletaMenosUsada;
	private String recorridoMasRealizado;
	private float tiempoPromedioUso;

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

	public float getTiempoPromedioUso() {
		return tiempoPromedioUso;
	}

	public void setTiempoPromedioUso(float tiempoPromedioUso) {
		this.tiempoPromedioUso = tiempoPromedioUso;
	}

}
