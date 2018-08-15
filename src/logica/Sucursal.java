package logica;
import java.util.ArrayList;

public class Sucursal implements Lugar{
	private String nombre;
	private String direccion;
	private String telefono;
	private String horario;
	private ListaFuncionarios funcionarios;
	private ListaRegistrados registrados;
	
	
	public Sucursal (){
		funcionarios= new ListaFuncionarios();
		registrados= new ListaRegistrados();
	}
	
	
	
	// GETTERS SETTERS
	
	public ArrayList<Registrado> getRegistrados() {
		return this.registrados.getListRegistrados();
	}
	
	public ArrayList<Funcionario> getFuncionarios(){
		return this.funcionarios.getListFuncionarios();
	}

	public void setRegistrados(ArrayList<Registrado> registrados) {
		this.registrados.setListRegistrados(registrados);
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
//FIN GETTERS Y SETTERS
	
	/**
	 * Registra a una persona en la sucursal
	 * @param persona
	 * @return
	 */
	public boolean registrar(Registrado persona){
		if(registrados.agregarRegistrado(persona))
			return true;
		return false;
	}
	
	/**
	 * Registra a una persona en la sucursal
	 * @param persona
	 * @return
	 */
	public boolean registrar(Funcionario persona){
		if(funcionarios.agregarFuncionario(persona))
			return true;
		return false;
	}
	

	/**
	 * Busca un registrado segun su rut
	 * @param rut
	 * @return
	 */
	public Registrado buscarRegistradoRut(String rut) {
		return registrados.buscarRegistradoRut(rut);	
	}
	
	/**
	 * Busca un funcionario segun su rut
	 * @param rut
	 * @return
	 */
	public Funcionario buscarFuncionarioRut(String rut) {
		return funcionarios.buscarFuncionarioRut(rut);
	}
	
	/**
	 * Cambia el rut de un Registrado.	
	 * @param antiguo
	 * @param nuevo
	 */
	public void cambiaRutPersona(String antiguo, String nuevo){
		Persona p = buscarPersona(antiguo);
		if(p.getTipo().compareTo("Registrado")==0)
			registrados.cambiaRut(antiguo, nuevo);
		if(p.getTipo().compareTo("Funcionario")==0)
			funcionarios.cambiaRut(antiguo, nuevo);
	}
	
	public Persona buscarPersona(String rut){
		if(buscarRegistradoRut(rut)!=null)
			return buscarRegistradoRut(rut);
		if(buscarFuncionarioRut(rut)!=null)
			return buscarFuncionarioRut(rut);
		return null;
	}
	
	/**
	 * Elimina a una persona segun su rut
	 * @param rut
	 * @return true si se logra, false si no
	 */
	public boolean eliminarPersona(String rut){
		if(registrados.eliminarRegistrado(rut))
			return true;
		if(funcionarios.eliminarFuncionario(rut))
			return true;
		return false;
	}
	
	public ArrayList<Persona> reporte(String reporte){
		ArrayList<Persona> reg, func;
		reg= registrados.reporte(reporte);
		func=funcionarios.reporte(reporte);
		
		for(int i=0; i<func.size(); i++){
			System.out.println("nombre "+ func.get(i).getNombre());
			reg.add(func.get(i));
		}
		
		return reg;
	}
	
}
	