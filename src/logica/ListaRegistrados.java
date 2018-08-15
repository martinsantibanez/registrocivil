package logica;

import java.util.ArrayList;
import java.util.HashMap;

public class ListaRegistrados {
	private ArrayList<Registrado> listRegistrados;
	private HashMap<String, Registrado> mapRegistrados;


	public ListaRegistrados() {
		listRegistrados = new ArrayList<Registrado>();
		mapRegistrados = new HashMap<String, Registrado>();
	}
	
	/**
	 * Agrega a un registrado a las estructuras.
	 * @param insertar
	 * @return
	 */
	public boolean agregarRegistrado(Registrado insertar) {
		if(mapRegistrados.put(insertar.getRut(), insertar)==null){ //put retorna null si no existia la clave
			listRegistrados.add(insertar);
			return true;
		}
		return false;
		
	}

	/**
	 * Cambia el rut de un registrado de la sucursal.
	 * @param antiguo
	 * @param nuevo
	 * @return
	 */
	public void cambiaRut(String antiguo, String nuevo) {
		Registrado reg = mapRegistrados.get(antiguo);
		mapRegistrados.remove(antiguo);
		reg.setRut(nuevo);
		mapRegistrados.put(nuevo, reg);
	}

	public boolean eliminarRegistrado(String rut){
		Registrado reg = mapRegistrados.get(rut);
		if(mapRegistrados.remove(rut)!=null){
			listRegistrados.remove(reg);
			return true;
		}
		return false;
	}
	
	public ArrayList<Registrado> getListRegistrados() {
		return listRegistrados;
	}

	public void setListRegistrados(ArrayList<Registrado> listRegistrados) {
		this.listRegistrados = listRegistrados;
	}

	public HashMap<String, Registrado> getMapRegistrados() {
		return mapRegistrados;
	}

	public void setMapRegistrados(HashMap<String, Registrado> mapRegistrados) {
		this.mapRegistrados = mapRegistrados;
	}

	public Registrado buscarRegistradoRut(String rut) {
		return mapRegistrados.get(rut);
	}
	
	public ArrayList<Persona> reporte(String registro){
		ArrayList<Persona> array = new ArrayList<Persona>();
		
		for(int i=0;i<listRegistrados.size();i++){
			Persona reg = new Registrado();
			reg.setNombre(listRegistrados.get(i).getNombre());
			reg.setRut(listRegistrados.get(i).getRut());
			reg.setFechaNacimiento(listRegistrados.get(i).getFechaNacimiento());
			reg.setSexo(listRegistrados.get(i).getSexo());
			reg.setEstadoCivil(listRegistrados.get(i).getEstadoCivil());
			reg.setEstadoDeVida(listRegistrados.get(i).getEstadoDeVida());
			
			array.add(reg);
		}
		
		if(registro.compareTo("Nacimientos")==0)
			return array;
		
		if(registro.compareTo("Matrimonios")==0){
			ArrayList<Persona> matri = new ArrayList<Persona>();
			for(int i = 0; i<array.size(); i++){
				if(array.get(i).getEstadoCivil().compareTo("Casado")==0)
					matri.add(array.get(i));
			}
			
				return matri;
		}
		
		if(registro.compareTo("Defunciones")==0){
			ArrayList<Persona> muertos = new ArrayList<Persona>();
			for(int i = 0; i<array.size(); i++){
				if(array.get(i).getEstadoDeVida().compareTo("0")==0)
					muertos.add(array.get(i));
			}
		
				return muertos;			
		}
		
		
		return array;
	}

}
