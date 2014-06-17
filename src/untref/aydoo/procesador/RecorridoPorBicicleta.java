package untref.aydoo.procesador;


public class RecorridoPorBicicleta {

	private int ID_usuario;
	private int ID_bicicleta;
	private String fechaOrigen;
	private Integer ID_estacionOrigen;
	private String nombreOrigen;
	private String fechaDestino;
	private Integer ID_estacionDestino;
	private String nombreDestino;
	private int tiempoUso;
	
	public String getParOrigenDestino(){
		
		
		return ID_estacionOrigen.toString().concat("-" + ID_estacionDestino.toString());
	}
	
	public int getID_usuario() {
		return ID_usuario;
	}

	public void setID_usuario(int iD_usuario) {
		ID_usuario = iD_usuario;
	}

	public int getID_bicicleta() {
		return ID_bicicleta;
	}

	public void setID_bicicleta(int iD_bicicleta) {
		ID_bicicleta = iD_bicicleta;
	}

	public String getFechaOrigen() {
		return fechaOrigen;
	}

	public void setFechaOrigen(String fechaOrigen) {
		this.fechaOrigen = fechaOrigen;
	}

	public int getID_estacionOrigen() {
		return ID_estacionOrigen;
	}

	public void setID_estacionOrigen(int iD_estacionOrigen) {
		ID_estacionOrigen = iD_estacionOrigen;
	}

	public String getNombreOrgien() {
		return nombreOrigen;
	}

	public void setNombreOrigen(String nombreOrigen) {
		this.nombreOrigen = nombreOrigen;
	}

	public String getFechaDestino() {
		return fechaDestino;
	}

	public void setFechaDestino(String fechaDestino) {
		this.fechaDestino = fechaDestino;
	}

	public int getID_estacionDestino() {
		return ID_estacionDestino;
	}

	public void setID_estacionDestino(int iD_estacionDestino) {
		ID_estacionDestino = iD_estacionDestino;
	}

	public String getNombreDestino() {
		return nombreDestino;
	}

	public void setNombreDestino(String nombreDestino) {
		this.nombreDestino = nombreDestino;
	}

	public int getTiempoUso() {
		return tiempoUso;
	}

	public void setTiempoUso(int tiempoUso) {
		this.tiempoUso = tiempoUso;
	}

}
