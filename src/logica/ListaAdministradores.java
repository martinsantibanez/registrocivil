package logica;

import java.util.ArrayList;
import java.util.HashMap;

public class ListaAdministradores {
	private ArrayList<Administrador> listAdministradores;
	private HashMap<String,Administrador> mapAdministradores;
	
	public ListaAdministradores(){
		listAdministradores = new ArrayList<Administrador>();
		mapAdministradores = new HashMap<String,Administrador>();
	}
	
	public ArrayList<Administrador> getAdministradores() {
		return listAdministradores;
	}

	public void setAdministradores(ArrayList<Administrador> administradores) {
		this.listAdministradores = administradores;
	}

	public Administrador buscarAdministradorRut(String rut) {
		
		return mapAdministradores.get(rut);
	}

	public boolean agregarAdministrador(Administrador admin) {
		if(mapAdministradores.put(admin.getRut(), admin)==null){
			return listAdministradores.add(admin);
		}
		return false;
	}
	
	public ArrayList<Persona> reporte (String registro, String region, String sucursal){
		ArrayList<Persona> array = new ArrayList<Persona>();
		
		for(int i=0; i<listAdministradores.size();i++){
			Persona admi = new Administrador();
			admi.setNombre(listAdministradores.get(i).getNombre());
			admi.setRut(listAdministradores.get(i).getRut());
			admi.setFechaNacimiento(listAdministradores.get(i).getFechaNacimiento());
			admi.setSexo(listAdministradores.get(i).getSexo());
			admi.setEstadoCivil(listAdministradores.get(i).getEstadoCivil());
			admi.setEstadoDeVida(listAdministradores.get(i).getEstadoDeVida());
			array.add(admi);
		}
		
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

	public String buscarSucursalAdministradorRut(String rut) {
		Administrador admRet = buscarAdministradorRut(rut);
		if(admRet!=null)
			return admRet.getSucRegistro();
		return null;
	}
	
	public IteratorList iterador(){
		return new IteratorList(this);
	}
}
