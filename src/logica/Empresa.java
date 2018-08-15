package logica;

public class Empresa extends Registrado {
	
   /**
    * fechaNacimiento->fecha en cual se cre� la empresa
    * fechaInscripcion->fecha en que se registro la empresa
    * sexo;estadoCivil;fechaMatrimonio;sucursalMatrimonio;rutConyuge;conyuge---> TODAS NULL
    * todos los demas atributos se utilizan sin modificacion
    */
	private String nomRepresentante;
	private int capital;
	private String rubro;
	private String direccion;
	private String region;
	
	public Empresa(){
		super();
		setTipo("Empresa");
		setSexo(null);
		setEstadoCivil(null);
		setFechaMatrimonio(null);
		setRutConyuge("0");
	}
	

	public String getNomRepresentante() {
		return nomRepresentante;
	}

	public void setNomRepresentante(String nomRepresentante) {
		this.nomRepresentante = nomRepresentante;
	}

	public int getCapital() {
		return capital;
	}

	public void setCapital(int capital) {
		this.capital = capital;
	}

	public String getRubro() {
		return rubro;
	}

	public void setRubro(String rubro) {
		this.rubro = rubro;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
	
	
}
