package logica;

public class Extranjero extends Registrado {
	/**
	 * Un extranjero no puede ser Administrador ni Funcionario es por eso que extiende de Registrado
	 * De los atributos heredados de Registrado:
	 * tipo-> seguir� siento registrado
	 * regionNacimiento-> se considera null, pues la lista de extranjero pertenecer� a RegistroCivil, no a una region como en el caso de Registrado y Funcionario
	 * todos los dem�s atributos se mantienen
	 */
	private String paisOrigen;
	private String tipoVisa; //Estudios; Trabajo; Negocios, Otros;
	private String sucRegistro;
	
	public Extranjero(){
		super();
		setTipo("Extranjero");
		
	}
	public String getPaisOrigen() {
		return paisOrigen;
	}

	public void setPaisOrigen(String paisOrigen) {
		this.paisOrigen = paisOrigen;
	}

	public String getTipoVisa() {
		return tipoVisa;
	}

	public void setTipoVisa(String tipoVisa) {
		this.tipoVisa = tipoVisa;
	}
	public String getSucRegistro() {
		return sucRegistro;
	}
	public void setSucRegistro(String sucRegistro) {
		this.sucRegistro = sucRegistro;
	}

}
