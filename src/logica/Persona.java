package logica;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Persona {
	private String tipo;
	private String nombre;
	private String rut;
	//Fecha de nacimiento:
	private String fechaNacimiento;
	private int diaNacimiento;
	private int mesNacimiento;
	private int anioNacimiento;
	private String fechaDeInscripcion;
	
	private String sexo; //Femenino o Masculino
	private String password;
	
	private String estadoDeVida; //"1" o "0"
	private String fechaDefuncion; 
	private int diaDefuncion;
	private int mesDefuncion;
	private int anioDefuncion;
	
	private String estadoCivil;     //Casado, Soltero, Viudo, Separado
	//FECHA MATRIMONIO:
	private String fechaMatrimonio;
	private int diaMatrimonio;
	private int mesMatrimonio;
	private int anioMatrimonio;	
	
	private String rutConyuge;
	
	public Persona(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		setFechaDeInscripcion(dateFormat.format(date).toString());
	    estadoCivil = "Soltero"; //por defecto
		estadoDeVida = "1";
		setTipo("Registrado");
	}
	
	public Persona (String nombre, String rut, String password, String sexo, String fechaNacimiento){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		setFechaDeInscripcion(dateFormat.format(date).toString());
		estadoCivil = "Soltero"; //por defecto
		estadoDeVida = "1";
		setTipo("Registrado");
		setNombre(nombre);
		setRut(rut);
		setPassword(password);
		setSexo(sexo);
		setFechaNacimiento(fechaNacimiento);
	}
	

	public String getRutConyuge() {
		return rutConyuge;
	}

	public void setRutConyuge(String rutConyuge) {
		this.rutConyuge = rutConyuge;
	}


	/** 
	 * Recibe fecha en formato dd,mm,yyyy y lo asigna en las variables
	 * @param fecha
	 */
	public void setFechaNacimiento(String fecha){
		fechaNacimiento = fecha;	
		String[] fecha_sep = fecha.split(",");
		diaNacimiento = Integer.parseInt(fecha_sep[0]);
		mesNacimiento = Integer.parseInt(fecha_sep[1]);
		anioNacimiento = Integer.parseInt(fecha_sep[2]);
		fechaNacimiento = fecha;		
	}
	
	public String getFechaSlash(){
		return diaNacimiento + "/" + mesNacimiento + "/" + anioNacimiento;
	}
	
	public String getFechaNacimiento(){
		return fechaNacimiento;
	}
	// GETTERS Y SETTERS
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		rut = rut.toLowerCase(); //Estandarizar
		this.rut = rut;
	}

	public int getDiaNacimiento() {
		return diaNacimiento;
	}
	public void setDiaNacimiento(int diaNacimiento) {
		this.diaNacimiento = diaNacimiento;
	}
	public int getMesNacimiento() {
		return mesNacimiento;
	}
	public void setMesNacimiento(int mesNacimiento) {
		this.mesNacimiento = mesNacimiento;
	}
	public int getAnioNacimiento() {
		return anioNacimiento;
	}
	public void setAnioNacimiento(int anioNacimiento) {
		this.anioNacimiento = anioNacimiento;
	}

	/**
	 * 
	 * @return "Casado", "Soltero", "Viudo" o "Divorciado"
	 */
	public String getEstadoCivil() {
		return estadoCivil;
	}

	/**
	 * 
	 * @param estado "Casado", "Soltero", "Viudo" o "Divorciado"
	 */
	public void setEstadoCivil(String estado) {
		this.estadoCivil = estado;
	}

	public String getFechaDeInscripcion() {
		return fechaDeInscripcion;
	}

	public void setFechaDeInscripcion(String fechaDeInscripcion) {
		this.fechaDeInscripcion = fechaDeInscripcion;
	}

	/**
	 * 
	 * @return Masculino o Femenino
	 */
	public String getSexo() {
		return sexo;
	}

	/**
	 * Masculino o Femenino
	 * @param sexo
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	/**
	 * 
	 * @return "1" vivo "0" muerto
	 */
	public String getEstadoDeVida() {
		return estadoDeVida;
	}

	/**
	 * 
	 * @param estadoDeVida "1" vivo "0" muerto
	 */
	public void setEstadoDeVida(String estadoDeVida) {
		this.estadoDeVida = estadoDeVida;
	}

	public String getFechaDefuncion() {
		return fechaDefuncion;
	}

	public void setFechaDefuncion(String fechaDefuncion) {
		this.fechaDefuncion = fechaDefuncion;
	}

	public int getDiaDefuncion() {
		return diaDefuncion;
	}

	public void setDiaDefuncion(int diaDefuncion) {
		this.diaDefuncion = diaDefuncion;
	}

	public int getMesDefuncion() {
		return mesDefuncion;
	}

	public void setMesDefuncion(int mesDefuncion) {
		this.mesDefuncion = mesDefuncion;
	}

	public int getAnioDefuncion() {
		return anioDefuncion;
	}

	public void setAnioDefuncion(int anioDefuncion) {
		this.anioDefuncion = anioDefuncion;
	}

	public String getFechaMatrimonio() {
		return fechaMatrimonio;
	}

	public void setFechaMatrimonio(String fechaMatrimonio) {
		this.fechaMatrimonio = fechaMatrimonio;
	}

	public int getDiaMatrimonio() {
		return diaMatrimonio;
	}

	public void setDiaMatrimonio(int diaMatrimonio) {
		this.diaMatrimonio = diaMatrimonio;
	}

	public int getMesMatrimonio() {
		return mesMatrimonio;
	}

	public void setMesMatrimonio(int mesMatrimonio) {
		this.mesMatrimonio = mesMatrimonio;
	}

	public int getAnioMatrimonio() {
		return anioMatrimonio;
	}

	public void setAnioMatrimonio(int anioMatrimonio) {
		this.anioMatrimonio = anioMatrimonio;
	}


	/**
	 * Revisa si password corresponde al Registrado.
	 * @param password
	 * @return
	 */
	public boolean login(String password) {
		if(getPassword().equals(password))
			return true;
		return false;
	}	

}
