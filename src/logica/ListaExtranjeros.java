package logica;

import java.util.ArrayList;
import java.util.HashMap;

public class ListaExtranjeros {
	private ArrayList<Extranjero> listExtranjeros;
	private HashMap<String,Extranjero> mapExtranjeros;
	
	public ListaExtranjeros() {
		listExtranjeros = new ArrayList<Extranjero>();
		mapExtranjeros = new HashMap<String, Extranjero>();
	}
	
	public ArrayList<Extranjero> getExtranjeros() {
		return listExtranjeros;
	}

	public void setExtranjeros(ArrayList<Extranjero> extranjeros) {
		this.listExtranjeros = extranjeros;
	}

	public Extranjero buscarExtranjeroRut(String rut) {
		
		return mapExtranjeros.get(rut);
	}

	public boolean agregarExtranjero(Extranjero extIns) {
		if(mapExtranjeros.put(extIns.getRut(), extIns)==null){
			return listExtranjeros.add(extIns);
		}
		return false;
	}

}
