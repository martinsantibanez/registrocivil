package logica;

import java.util.ArrayList;

public class ListaSucursales {

	private ArrayList<Sucursal> sucursales;


	public ListaSucursales(){
		sucursales = new ArrayList<Sucursal>();
	}
	public int getTamano() {
		return sucursales.size();
	}
	public boolean addSucursal(Sucursal sucursalAdd) {
		String nombreIns = sucursalAdd.getNombre();
		for(Sucursal sucursalActual : sucursales){
			if(sucursalActual.getNombre().equals(nombreIns))
				return false;
		}
		sucursales.add(sucursalAdd);
		return true;
	}

	public ArrayList<Sucursal> getSucursales() {
		return sucursales;
	}

	public void setSucursales(ArrayList<Sucursal> sucursales) {
		this.sucursales = sucursales;
	}

	public Registrado buscarRegistradoRut(String rut) {
		for(Sucursal sucursalActual : sucursales){
			Registrado ret = sucursalActual.buscarRegistradoRut(rut);
			if(ret!=null)
				return ret;
		}
		return null;
		
	}

	public Funcionario buscarFuncionarioRut(String rut) {
		for(Sucursal sucursalActual : sucursales){
			Funcionario ret = sucursalActual.buscarFuncionarioRut(rut);
			if(ret!=null)
				return ret;
		}
		return null;
	}

	public Sucursal buscarSucursalFuncionarioRut(String rut) {
		for(Sucursal sucursalActual: sucursales){
			Funcionario busc = sucursalActual.buscarFuncionarioRut(rut);
			if(busc!=null)
				return sucursalActual;
		}
		return null;
	}
	
	public Sucursal buscarSucursalRegistradoRut(String rut) {
		for(Sucursal sucursalActual: sucursales){
			Registrado busc = sucursalActual.buscarRegistradoRut(rut);
			if(busc!=null)
				return sucursalActual;
		}
		return null;
	}
	
	public String[] nombreSucursales(){
		String[] vector = new String[sucursales.size()];
		for(int i=0;i<sucursales.size();i++){
			vector[i] = sucursales.get(i).getNombre();
		}
		return vector;
	}
	
	public ArrayList<Persona> reporte(String reporte, String sucursal){
		for(int i=0;i<sucursales.size();i++){
			if(sucursales.get(i).getNombre().compareTo(sucursal)==0){
				return sucursales.get(i).reporte(reporte);
			}
		}
		return null;
	}

	public Sucursal buscarSucursal(String nombre){
		for ( Sucursal sucursalActual : sucursales){
			if (sucursalActual.getNombre().equals(nombre))
				return sucursalActual;
		}
		return null;
	}

	public boolean eliminarSucursal(Sucursal suc){
		return sucursales.remove(suc);
	}
	
	public ArrayList<Registrado> getRegistrados(){
		ArrayList<Registrado> lista = new ArrayList<Registrado>();
		for ( Sucursal sucursalActual : sucursales){
			lista.addAll(sucursalActual.getRegistrados());
		}
		return lista;
	}
	public ArrayList<Funcionario> getFuncionarios(){
		ArrayList<Funcionario> lista = new ArrayList<Funcionario>();
		for ( Sucursal sucursalActual : sucursales){
			lista.addAll(sucursalActual.getFuncionarios());
		}
		return lista;
	}
}
