package logica;

import java.util.ArrayList;
import java.util.HashMap;

public class ListaFuncionarios {
	private ArrayList<Funcionario> listFuncionarios;
	private HashMap<String,Funcionario> mapFuncionarios;
	public ListaFuncionarios() {
		listFuncionarios = new ArrayList<Funcionario>();
		mapFuncionarios = new HashMap<String, Funcionario>();
	}
	/**
	 * Agrega un funcionario a la lista y al mapa.
	 * @param insertar
	 */
	public boolean agregarFuncionario(Funcionario insertar){
		if(mapFuncionarios.put(insertar.getRut(), insertar)==null){ //put retorna null si no habia algo con la clave
			listFuncionarios.add(insertar);
			return true;
		}
		return false;
	}

	/**
	 * Busca un funcionario en el mapa segun su rut.
	 * @param rut
	 * @return
	 */
	public Funcionario buscarFuncionarioRut(String rut) {
		return mapFuncionarios.get(rut);
	}
	
	public boolean eliminarFuncionario(String rut) {
		Funcionario func = mapFuncionarios.get(rut);
		if(mapFuncionarios.remove(rut)!=null){
			listFuncionarios.remove(func);
			return true;
		}
		return false;
	}
	
	public ArrayList<Persona> reporte(String reporte){
		ArrayList<Persona> array = new ArrayList<Persona>();
		
		for(int i=0;i<listFuncionarios.size();i++){
			Persona func = new Funcionario();
			func.setNombre(listFuncionarios.get(i).getNombre());
			func.setRut(listFuncionarios.get(i).getRut());
			func.setFechaNacimiento(listFuncionarios.get(i).getFechaNacimiento());
			func.setDiaNacimiento(listFuncionarios.get(i).getDiaNacimiento());
			func.setMesNacimiento(listFuncionarios.get(i).getMesNacimiento());
			func.setAnioNacimiento(listFuncionarios.get(i).getAnioNacimiento());
			func.setSexo(listFuncionarios.get(i).getSexo());
			func.setEstadoCivil(listFuncionarios.get(i).getEstadoCivil());
			func.setEstadoDeVida(listFuncionarios.get(i).getEstadoDeVida());
			
			array.add(func);
		}
		
		
		if(reporte.compareTo("Nacimientos")==0)
			return array;
		
		if(reporte.compareTo("Matrimonios")==0){
			ArrayList<Persona> matri = new ArrayList<Persona>();
			for(int i = 0; i<array.size(); i++){
				if(array.get(i).getEstadoCivil().compareTo("Casado")==0)
					matri.add(array.get(i));
			}
			
				return matri;
		}
		
		if(reporte.compareTo("Defunciones")==0){
			System.out.println("numeo "+array.size()+ reporte);
			ArrayList<Persona> muertos = new ArrayList<Persona>();
			for(int i = 0; i<array.size(); i++){
				if(array.get(i).getEstadoDeVida().compareTo("0")==0)
					muertos.add(array.get(i));
				
			}
			
				return muertos;
			
		}
		return array;
	}
	public ArrayList<Funcionario> getListFuncionarios() {
		return listFuncionarios;
	}
	public void cambiaRut(String antiguo, String nuevo) {
		Funcionario func = mapFuncionarios.get(antiguo);
		mapFuncionarios.remove(antiguo);
		func.setRut(nuevo);
		mapFuncionarios.put(nuevo, func);
		
	}
}
