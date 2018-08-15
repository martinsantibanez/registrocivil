package logica;

import java.util.ArrayList;

public class IteratorList {
	private ArrayList<Administrador> admin;
	private int posicion;
	
	public IteratorList(ListaAdministradores listaAdmin){
		admin= listaAdmin.getAdministradores();
		posicion=0;
	}
	
	public boolean hasNext(){
		if (posicion < admin.size())
			return true;
		else 
			return false;
	}
	
	public Object next(){
		Administrador admi = admin.get(posicion);
		posicion++;
		return (Object) admi;
	}
}
