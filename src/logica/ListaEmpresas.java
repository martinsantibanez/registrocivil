package logica;

import java.util.ArrayList;
import java.util.HashMap;

public class ListaEmpresas {
	private ArrayList<Empresa> listEmpresas;
	private HashMap<String,Empresa> mapEmpresas;
	
	public ListaEmpresas() {
		listEmpresas = new ArrayList<Empresa>();
		mapEmpresas = new HashMap<String, Empresa>();
	}

	public ArrayList<Empresa> getEmpresas() {
		return listEmpresas;
	}

	public void setEmpresa(ArrayList<Empresa> empresas) {
		this.listEmpresas = empresas;
	}

	public Empresa buscarEmpresaRut(String rut) {
		
		return mapEmpresas.get(rut);
	}

	public boolean agregarEmpresa(Empresa empIns) {
		if(mapEmpresas.put(empIns.getRut(), empIns)==null){
			return listEmpresas.add(empIns);
		}
		return false;
	}

}


