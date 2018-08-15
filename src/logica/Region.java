package logica;
import java.util.ArrayList;
public class Region implements Lugar{
	private ListaSucursales sucursales;
	private String nombre;
	
	public Region(String nombre){
		sucursales = new ListaSucursales();
		this.nombre = nombre;
	}
	
	public Region (){
		sucursales = new ListaSucursales();
	}
	
	public boolean addSucursal(Sucursal sucursalAdd){
		return sucursales.addSucursal(sucursalAdd);
	}
	public ArrayList<Sucursal> getSucursales() {
		return sucursales.getSucursales();
	}
	
	public void setSucursales(ArrayList<Sucursal> sucursales) {
		this.sucursales.setSucursales(sucursales);
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Registrado buscarRegistradoRut(String rut) {
		return sucursales.buscarRegistradoRut(rut);
		
	}

	public Funcionario buscarFuncionarioRut(String rut) {
		return sucursales.buscarFuncionarioRut(rut);
	}

	public Sucursal buscarSucursalFuncionarioRut(String rut) {
		return sucursales.buscarSucursalFuncionarioRut(rut);
	}
	
	public Sucursal buscarSucursalRegistradoRut(String rut) {
		return sucursales.buscarSucursalRegistradoRut(rut);
	}
	
	public int numeroDeSucursales(){
		return sucursales.getTamano();
	}
	
	public String[] nombreSucursales(){
		return sucursales.nombreSucursales();
	}
	
	public ArrayList<Persona> reporte(String registro, String sucursal){
		return sucursales.reporte(registro,sucursal);
	}
  
	
	/**
	 * Busca una sucursal dentro de la regi�n por su nombre
	 * @param nombre
	 * @return la Sucursal
	 * @throws LugarNoExisteException 
	 */
	public Sucursal buscarSucursal(String nombre) throws LugarNoExisteException{
		Sucursal ret = sucursales.buscarSucursal(nombre);
		if(ret!=null)
			return ret;
		else
			throw new LugarNoExisteException();
	}

	public boolean eliminarSucursal(Sucursal suc){
		return sucursales.eliminarSucursal(suc);
	}

	public boolean renombrarSucursal(Sucursal suc, String text) {
		if(sucursales.buscarSucursal(text)==null){
			suc.setNombre(text);
			return true;
		}
		return false;
	}
	
	public ArrayList<Registrado> getRegistrados(){
		ArrayList<Registrado> lista = new ArrayList<Registrado>();
		lista.addAll(sucursales.getRegistrados());
		return lista;
	}
	
	public ArrayList<Funcionario> getFuncionario(){
		ArrayList<Funcionario> lista = new ArrayList<Funcionario>();
		lista.addAll(sucursales.getFuncionarios());
		return lista;
	}

	
}
