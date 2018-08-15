package logica;

import java.util.ArrayList;

public class ListaRegiones {
	private ArrayList<Region> regiones;

	public ListaRegiones(){
		regiones = new ArrayList<Region>();
	}
	
	/*public ListaRegiones (ArrayList<Region> regiones){
		setRegiones(regiones);
	}*/	

	/**
	 * Busca en cada region de la lista el rut indicado.
	 * @param rut
	 * @return
	 */
	public Registrado buscarRegistradoRut(String rut) {
		for(Region regionActual : regiones){
			Registrado ret = regionActual.buscarRegistradoRut(rut);
			if(ret!=null)
				return ret;
			
		}
		return null;
		
	}
	
	public ArrayList<Registrado> getRegistrados(){
		ArrayList<Registrado> lista = new ArrayList<Registrado>();
		for(Region regionActual : regiones){
			lista.addAll(regionActual.getRegistrados());
		}
		
		return lista;
	}
	public ArrayList<Funcionario> getFuncionarios(){
		ArrayList<Funcionario> lista = new ArrayList<Funcionario>();
		for(Region regionActual : regiones){
			lista.addAll(regionActual.getFuncionario());
		}
		return lista;
	}
	public ArrayList<Sucursal> getSucursales(){
		ArrayList<Sucursal> lista = new ArrayList<Sucursal>();
		for (Region regionActual : regiones){
			lista.addAll(regionActual.getSucursales());
		}
		return lista;
	}

	public Funcionario buscarFuncionarioRut(String rut) {
		for(Region regionActual : regiones){
			Funcionario ret = regionActual.buscarFuncionarioRut(rut);
			if(ret!=null)
				return ret;
		}
		return null;
	}
	
	public boolean agregarRegion(Region region) {
		return regiones.add(region);
	}

	/**
	 * Busca la sucursal del funcionario con ese RUT
	 * @param rut
	 * @return la Sucursal o null.
	 */
	public Sucursal buscarSucursalFuncionarioRut(String rut) {
		for(Region regionActual : regiones){
			Sucursal ret = regionActual.buscarSucursalFuncionarioRut(rut);
			if(ret!=null)
				return ret;
		}
		return null;
	}
	
	public Sucursal buscarSucursalRegistradoRut(String rut) {
		for(Region regionActual: regiones){
			Sucursal ret = regionActual.buscarSucursalRegistradoRut(rut);
			if(ret!=null)
				return ret;
		}
		return null;
	}	
	
	public String nombreRegionIndice(int i){
		return regiones.get(i).getNombre();
	}
	
	public int numeroDeSucursales (String region){
		for(int i=0;i<regiones.size();i++){
			if(regiones.get(i).getNombre().compareTo(region)==0){
				return regiones.get(i).numeroDeSucursales();
			}
		}
		return 0; 
	}
	
	public String[] nombreSucursales (String region){
		for(int i=0;i<regiones.size();i++){
			if(regiones.get(i).getNombre().compareTo(region)==0){
				return regiones.get(i).nombreSucursales();
			}
		}
		return null;
		
	}
	
	public ArrayList<Persona> reporte(String registro, String region, String sucursal){
		for(int i=0;i<regiones.size();i++){
			if(regiones.get(i).getNombre().compareTo(region)==0){
				return regiones.get(i).reporte(registro,sucursal);
			}
		}
		return null;
	}

	public int numeroRegiones() {
		return regiones.size();
	}
   
	public boolean eliminarSucursal(Sucursal suc){
		for(Region regAct : regiones){
			if(regAct.eliminarSucursal(suc))
				return true;
		}
		return false;
	}
	

	public void setRegiones(ArrayList<Region> regiones) {
		this.regiones = regiones;
	}
	/**
	 * 
	 * @param nombre
	 * @return la Region o null
	 */
	public Region buscarRegion(String nombre){
		for(Region regionActual : regiones){
			if(regionActual.getNombre().compareTo(nombre)==0)
				return regionActual;
		}
		return null;
	}
	
	public ArrayList<Region> getRegiones(){
		return regiones;
	}

	public Sucursal buscarSucursalPersonaRut(String rut) {
		if(buscarSucursalFuncionarioRut(rut)!=null)
			return buscarSucursalFuncionarioRut(rut);
		if(buscarSucursalRegistradoRut(rut)!=null)
			return buscarSucursalRegistradoRut(rut);
		return null;
	}
}

	 

