package logica;

import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfWriter;

public class RegistroCivil {
	private ListaAdministradores administradores;
	private ListaRegiones regiones;
	private ListaExtranjeros extranjeros;
	private ListaEmpresas empresas;

	public RegistroCivil() {
		regiones = new ListaRegiones();
		administradores = new ListaAdministradores();
		extranjeros = new ListaExtranjeros();
		empresas = new ListaEmpresas();
	}

	
	/**
	 * Revisa si el rut coincide con la clave.
	 * @param rut Persona
	 * @param clave Persona
	 * @return Persona si lo encuentra, de lo contrario retorna null
	 */
	public Persona login(String rut, String clave) {
		
		Persona persona = (Persona)buscarRegistrado(rut);
		if(persona!=null && persona.login(clave)){
			return persona;
		}
		persona= (Persona)buscarFuncionario(rut);
		if(persona!=null && persona.login(clave)){
			return persona;
		}
		persona = (Persona)buscarAdministrador(rut);
		if(persona!=null && persona.login(clave)){
			return persona;
		}
		
		return null;
	}
	
	/**
	 * Buscar a una persona dentro del sistema, segun sea su tipo por el rut
	 * @param rut Persona
	 * @return Persona en caso de encontrarlo, de lo contrario retorna null
	 */
	public Persona buscarPersona(String rut){
		if(buscarRegistrado(rut)!= null)
			return buscarRegistrado(rut);
		if(buscarFuncionario(rut)!= null)
			return buscarFuncionario(rut);
		if(buscarAdministrador(rut)!= null)
			return buscarAdministrador(rut);
		if(buscarEmpresa(rut)!=null)
			return buscarEmpresa(rut);
		if(buscarExtranjero(rut)!=null)
			return buscarExtranjero(rut);
		return null;
	}

	private Persona buscarExtranjero(String rut) {
		return extranjeros.buscarExtranjeroRut(rut);
	}


	private Persona buscarEmpresa(String rut) {
		return empresas.buscarEmpresaRut(rut);
	}


	public String nombrePersonaRut(String rut){
		Persona pers = buscarPersona(rut);
		if(pers!=null)
			return pers.getNombre();
		return null;
	}
	/**
	 * Busca un registrado en el sistema completo seg�n su rut
	 * @param rut Registrado
	 * @return Registrado con el rut o null si no existe. @
	 */
	public Persona buscarRegistrado(String rut) {
		Registrado busc = regiones.buscarRegistradoRut(rut);
		return (Persona)busc;
	}

	/**
	 * Busca un funcionario en el sistema completo seg�n su rut
	 * @param rut
	 * @return Funcionario con el rut o null si no existe
	 */
	public Funcionario buscarFuncionario(String rut) {
		Funcionario busc = regiones.buscarFuncionarioRut(rut);
		return busc;
	}

	/**
	 * Busca un administrador segun rut en el sistema completo 
	 * @param rut
	 * @return
	 */
	public Administrador buscarAdministrador(String rut) {
		Administrador busc = administradores.buscarAdministradorRut(rut);
		return busc;
	}
	
	/**
	 * Busca la sucursal asociada al funcionario con el rut dado.
	 * @param rut
	 * @return
	 * @throws LugarNoExisteException
	 */
	public Sucursal buscarSucursalFuncionarioRut(String rut) throws LugarNoExisteException {
		Sucursal busc = regiones.buscarSucursalFuncionarioRut(rut);
		if(busc!=null)
			return busc;
		else
			throw new LugarNoExisteException();
	}
	
	/**
	 * Busca la sucursal asociada al registrado con el rut dado.
	 * @param rut
	 * @return
	 */
	public Sucursal buscarSucursalRegistradoRut(String rut) {
		Sucursal busc = regiones.buscarSucursalRegistradoRut(rut);
		return busc;
	}
	
	public Sucursal buscarSucursalPersonaRut(String rut) {
		return regiones.buscarSucursalPersonaRut(rut);
	}
	
	/**
	 * Busca el nombre de la sucursal asociada al Administrador con el rut dado.
	 * @param rut
	 * @return
	 */
	public String nombreSucursalAdministradorRut(String rut) {
		return administradores.buscarSucursalAdministradorRut(rut);
	}
	
	/**
	 * Busca el nombre de la sucursal asociada al registrado con el rut dado.
	 * @param rut
	 * @return
	 */
	public String nombreSucursalRegistradoRut(String rut){
		return buscarSucursalRegistradoRut(rut).getNombre();
	}
	
	/**
	 * Busca el nombre de la sucursal asociada al Funcionario con el rut dado.
	 * @param rut
	 * @return
	 * @throws LugarNoExisteException 
	 */
	public String nombreSucursalFuncionarioRut(String rut) throws LugarNoExisteException{
		return buscarSucursalFuncionarioRut(rut).getNombre();
	}
	

	/**
	 * Busca el nombre de la sucursal asociada a la persona con el rut dado.
	 * @param rut
	 * @return
	 * @throws LugarNoExisteException 
	 */
	public String nombreSucursalPersonaRut(String rut) throws LugarNoExisteException{
		Persona pers = buscarPersona(rut);
		if(pers.getTipo().compareTo("Registrado")==0) return nombreSucursalRegistradoRut(rut);
		if(pers.getTipo().compareTo("Funcionario")==0) return nombreSucursalFuncionarioRut(rut);  
		if(pers.getTipo().compareTo("Administrador")==0) return nombreSucursalAdministradorRut(rut);
		return null;
	}
	
	
	/**
	 * Valida el formato del string de fecha
	 * @param fecha
	 * @return
	 */
	public void validarFecha(String fecha) throws FormatoInvalidoException{
		try {
			DateFormat formatter = new SimpleDateFormat("dd,MM,yyyy");
		    formatter.setLenient(false);
		    @SuppressWarnings("unused")
			Date date = formatter.parse(fecha);
			return;
		} catch (ParseException ex) {
			throw new FormatoInvalidoException();
		}
	}
	
	/**
	 * Valida que el formato de un rut sea correcto.
	 * @param rut
	 * @return true si es valido, false si no.
	 */
	@SuppressWarnings("unused")
	public void validarRut(String rut) throws FormatoInvalidoException{
		if ( rut.length() > 0  ) {
			// Creamos un arreglo con el rut y el digito verificador
			String[] rut_dv = rut.split("-");
			// Las partes del rut (numero y dv) deben tener una longitud positiva
			if ( rut_dv.length == 2  ) {
			// Capturamos error (al convertir un string a entero)
				try {
					int rut_int = Integer.parseInt( rut_dv[0] );
					char dv = rut_dv[1].charAt(0);
					return;
				} catch( Exception ex ) {
					throw new FormatoInvalidoException();
				}
			}
		}
		throw new FormatoInvalidoException();
	}
	/**
	 * Muestra un mensaje de error.
	 * @param error
	 */
	public void error(String error) {
		JOptionPane.showMessageDialog(null, error,"ERROR", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Muestra un mensaje de alerta.
	 * @param alerta
	 */
	public void alerta(String alerta) {
		JOptionPane.showMessageDialog(null, alerta);
	}
	/**
	 * Nombre de la regi�n seg�n su indice
	 * @param i
	 * @return
	 */
	public String nombreRegion (int i){
		return regiones.nombreRegionIndice(i);
	}
	
	public int numeroDeSucursales (String region){
		return regiones.numeroDeSucursales(region);
	}
	
	public String[] nombreSucursales (String region){
		return regiones.nombreSucursales(region);
		
	}
	
	public ArrayList<Persona> reporte(String registro, String region, String sucursal){
		ArrayList<Persona> adm,rep;
		rep= regiones.reporte(registro,region,sucursal);
		adm= administradores.reporte(registro, region, sucursal);
		
		for(int i=0; i<adm.size();i++)
			rep.add(adm.get(i));
		
		return  rep;
	}
	

	public ArrayList<Persona> reporte(ArrayList<Persona> reporte, int anio,String registro){
		ArrayList<Persona> filtrados = new ArrayList<Persona>();
		//validar a�o de filtro
		//rellenar A�oDefuncion y A�oMatrimonio
		
		if(registro.compareTo("Nacimientos")==0){
			for(int i=0;i<reporte.size();i++){
				System.out.println(reporte.get(i).getNombre()+"a�ooo  "+reporte.get(i).getAnioNacimiento());
				if(reporte.get(i).getAnioNacimiento()==anio){
					filtrados.add(reporte.get(i));
				}
			}
			
			return filtrados;
		}
		
		if(registro.compareTo("Matrimonios")==0){
			for(int i=0;i<reporte.size();i++){
				if(reporte.get(i).getAnioMatrimonio()==anio){
					filtrados.add(reporte.get(i));
				}
			}
			return filtrados;
			}
		
		if(registro.compareTo("Defunciones")==0){
			for(int i=0;i<reporte.size();i++){
				if(reporte.get(i).getAnioDefuncion()==anio){
					filtrados.add(reporte.get(i));
				}
			}
			return filtrados;
			}
		
		return null;
	}
	
	
	public int numeroRegiones(){
		return regiones.numeroRegiones();
	}
	
	/**
	 *  Busca una region por su nombre.
	 * @param nombre
	 * @return
	 * @throws LugarNoExisteException
	 */
	public Region buscarRegion(String nombre) throws LugarNoExisteException{
		Region ret = regiones.buscarRegion(nombre);
		if(ret!=null)
			return ret;
		else throw new LugarNoExisteException();
	}

	public boolean eliminarSucursal(Sucursal suc){
		return regiones.eliminarSucursal(suc);
	}
	
	/**
	 * Divorcia a rut1 de rut2.
	 * @param rut1
	 * @param rut2
	 * @return
	 */
	public boolean divorciar(String rut1, String rut2){
		Persona p1 = buscarPersona(rut1);
		Persona p2 = buscarPersona(rut2);
		if(confirmar("CONFIRMAR DIVORCIO de:\n" + p1.getNombre() + "\nY:\n"+p2.getNombre())){
			p1.setRutConyuge("");
			p2.setRutConyuge("");
			p1.setEstadoCivil("Divorciado");
			p2.setEstadoCivil("Divorciado");
			return true;
		}
		return false;
		
	}
	
	/**
	 * Muestra un dialogo de confirmaci�n.
	 * @param texto
	 * @return true si Acepta, false si Cancela.
	 */
	public boolean confirmar(String texto){
		int dialogResult = JOptionPane.showConfirmDialog (null, texto ,"Atenci�n", JOptionPane.YES_NO_OPTION);
		if(dialogResult == JOptionPane.YES_OPTION) return true;
		else return false;
	}
	
	/**
	 * genera un matrimonio entre dos personas verificando que el tramite se pueda realizar 
	 * @param rut1 conyuge 1 
	 * @param rut2 conyuge 2
	 * @param encargado realizando el matrimonio
	 * @param fecha en que se genera el matrimonio
	 * @return true si se realiza el matrimonio
	 */
	public boolean generarMatrimonio(String rut1, String rut2, Persona encargado, String fecha){
		
		Persona p1 = buscarPersona(rut1);
		Persona p2 = buscarPersona(rut2);
		
		if(p1!=null && p2!= null && p1.getEstadoDeVida().equals("1") && p2.getEstadoDeVida().equals("1")){
			if(!encargado.getRut().equals(rut1) && !encargado.getRut().equals(rut2) && !(rut1.equals(rut2))){
				if(p1.getEstadoCivil().equals("Casado") || p2.getEstadoCivil().equals("Casado"))
					error("Uno de los dos conyuges ya se encuentra casado, no es posible realizar esta operaci�n");
				else{
					if(confirmar("CONFIRMAR MATRIMONIO entre:\n" + p1.getNombre() + "\nY:\n"+p2.getNombre())){
						p1.setEstadoCivil("Casado");
						p2.setEstadoCivil("Casado");
						p1.setFechaMatrimonio(fecha);
						p2.setFechaMatrimonio(fecha);
						p1.setRutConyuge(buscarPersona(rut2).getRut());
						p2.setRutConyuge(buscarPersona(rut1).getRut());
						alerta("Matrimonio realizado con �xito.");
						return true;
					}
				}
			}
			else{
				error("No es posible realizar esta operaci�n");
			}
		}
		else{
			error("No se encuentran registros asociados a los rut ingresados");
		}		
		return false;
	}
	/**
	 * Escribe certificado de nacimiento de una persona. Estaba en Persona
	 * @throws Exception
	 */
	public void EscrituraPersona (Persona persona) throws Exception{
	       Document document = new Document();     
	       Font fuente= new Font(FontFamily.TIMES_ROMAN);
	       Calendar cal = Calendar.getInstance();
	       Date fecha = new Date( cal.getTimeInMillis() );
	       SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	       fuente.setColor(BaseColor.BLACK);
	       String parrafo =  "SERVICIO DE REGISTRO                                                                  FECHA:"+formato.format(fecha)+" \n" 
	       				   + "CIVIL E IDENTIFICACION\n"
	       		           + "--------------------------------------------------------------------------------------------------------------------- \n \n ";
	       String parrafo2 = "											CERTIFICADO DE NACIMIENTO \n \n                        ";	       
	       String parrafo3 = "CIRCUNSCRIPCION: " + nombreSucursalRegistradoRut(persona.getRut()) + " \n"
	    		   +"FECHA DE REGISTRO: " + persona.getFechaDeInscripcion() +"\n"
                +"NOMBRE: " + persona.getNombre() + "\n"
                +"RUT: "+ persona.getRut() + "\n"
                +"FECHA DE NACIMIENTO: "+ persona.getDiaNacimiento()+"."+ persona.getMesNacimiento() +"."+ persona.getAnioNacimiento()+"\n";	         
	       PdfWriter.getInstance(document, new FileOutputStream("nacimiento"+persona.getRut()+".pdf"));
	       document.open();
	       document.add(new Paragraph(parrafo,fuente));
	       document.add(new Paragraph(parrafo2));
	       document.add(new Paragraph(parrafo3));
	       document.close();	             
	  }
	
	public boolean agregarRegion(Region region){
		return regiones.agregarRegion(region);
	}
	
	public boolean agregarAdministrador(Administrador admin){
		return administradores.agregarAdministrador(admin);
	}
	
	public boolean agregarExtranjero(Extranjero extIns) {
		return extranjeros.agregarExtranjero(extIns);
	}
	public boolean agregarEmpresa(Empresa empIns) {
		return empresas.agregarEmpresa(empIns);
	}
	
	// GETTERS AND SETTERS

	public void setAdministradores(ListaAdministradores administradores) {
		this.administradores = administradores;
	}

	public void setRegiones(ListaRegiones regiones) {
		this.regiones = regiones;
	}
	
	/**
	 * calcula el precio con el descuento aplicado para un registrado, este descuento varia segun a cantidad de tramites que haya realizado y la edad que tenga 
	 * si el ntramites<=edad, no se aplica descuento. Se mantiene el recio original de $2500
	 * si el ntramites>=2*edad el precio sera la mitad del original (el descuento solo llega a este tope) $1250
	 * si el ntramites es mayor a la edad y menos que el doble de esta, se realiza una division (edad/ntramites)*precioOriginal. De esta manera se obtiene el precio con descuento
	 * @param persona (a la que se le calculara el descuento)
	 * @return el precio del tramite con el descuento aplicado
	 */

	public int calcularDescuento(Registrado persona){
		float precio=2500;
		System.out.println("edad: "+calcularEdad(persona)+"\nnumero de tramites"+persona.getNumTramites());
		
		if(persona.getNumTramites()<=calcularEdad(persona))
			return (int)precio;
		if(persona.getNumTramites()>=(2*calcularEdad(persona)))
			return (int)(precio/2);	
		else{
			precio= ((float)calcularEdad(persona)/(float)persona.getNumTramites())*precio;
			return (int)precio;
		}
	}
	
	public int calcularEdad(Persona persona){
		int edad;
		
		Calendar fecha = new GregorianCalendar();
		int anio = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
      
        edad= (anio-persona.getAnioNacimiento());
     
        if(mes<persona.getMesNacimiento())
        	return edad-1;
        if(mes==persona.getMesNacimiento()){
        	if(dia<persona.getDiaNacimiento())
        		return edad-1;
        }
		return edad;
		
	}
	
	public ArrayList<Region> getRegiones(){
		return regiones.getRegiones();
	}
	
	public ArrayList<Administrador> getAdministradores(){
		return administradores.getAdministradores();
	}

	public ArrayList<Extranjero> getExtranjeros(){
		return extranjeros.getExtranjeros();
	}
	
	public ArrayList<Empresa> getEmpresas(){
		return empresas.getEmpresas();
	}
	
	public ListaAdministradores getListaAdministradores(){
		return administradores;
	}
}
