package logica;

import java.io.File;
import java.util.ArrayList;

import jxl.*;

public class Lectura {
	private ArrayList<Region> regiones;
	private ArrayList<Administrador> administradores;
	
	public Lectura(){
		regiones = new ArrayList<Region>();
		administradores = new ArrayList<Administrador>();
	}
	
	public void LeerDatos(RegistroCivil registro){
		try{
			Workbook libro = Workbook.getWorkbook(new File("proyecto.xls"));//Pasamos el excel que vamos a leer
			Sheet hojaPersonas = libro.getSheet(0); 
			Sheet hojaSucursales= libro.getSheet(1);//creamos las hojas en que vamos a leer
			llenarRegiones(); //Cargar regiones (pre escritas)
			//Para cada region se buscan las sucursales y personas correspondientes
			for(Region regionActual : regiones){
				//En las sucursales:
				for(int fila=0; fila < hojaSucursales.getRows(); fila++){
					String regionSuc = hojaSucursales.getCell(0, fila).getContents();
					if(regionSuc.equals(regionActual.getNombre())){ //Si la sucursal corresponde a la region
						Sucursal sucIns = new Sucursal();
						sucIns.setNombre(hojaSucursales.getCell(1, fila).getContents());
						sucIns.setDireccion(hojaSucursales.getCell(2, fila).getContents());
						sucIns.setTelefono(hojaSucursales.getCell(3, fila).getContents());
						sucIns.setHorario(hojaSucursales.getCell(4, fila).getContents());		
						regionActual.addSucursal(sucIns);
					}
				}
				registro.agregarRegion(regionActual);
			}
			agregarPersonas(hojaPersonas, registro);
			
		}catch(Exception ioe){
				ioe.printStackTrace();
		}
	}
	
	
	/**
	 * Busca en la hojaPersonas las personas de la region 'region' y de la sucursal 'sucursal'
	 * @param region
	 * @param sucursal
	 * @param hojaPersonas
	 * @throws LugarNoExisteException 
	 */
	private void agregarPersonas(Sheet hojaPersonas, RegistroCivil registro) throws LugarNoExisteException{
		for (int fila=1; fila < hojaPersonas.getRows(); fila++){ //Recorre la hojaPersonas
			String tipo = hojaPersonas.getCell(0,fila).getContents();
			if(tipo.compareTo("Registrado")==0 || tipo.compareTo("Funcionario")==0){
				String regPers = hojaPersonas.getCell(4, fila).getContents();
				String sucPers = hojaPersonas.getCell(5, fila).getContents();
				Region region = registro.buscarRegion(regPers);
				Sucursal sucursal = region.buscarSucursal(sucPers);
				if(tipo.compareTo("Registrado")==0)
					lecturaRegistrado(fila, hojaPersonas, sucursal);
				if(tipo.compareTo("Funcionario")==0)
					lecturaFuncionario(fila, hojaPersonas, sucursal);
				
			}
			if(tipo.compareTo("Administrador")==0)
				lecturaAdministrador(fila, hojaPersonas, registro);
			if(tipo.compareTo("Extranjero")==0)
				lecturaExtranjero(fila, hojaPersonas, registro);
			if(tipo.compareTo("Empresa")==0)
				lecturaEmpresa(fila, hojaPersonas, registro);
		}
	}
		
	private void lecturaRegistrado(int fila, Sheet hojaPersonas, Sucursal sucursal){
		Registrado regIns = new Registrado();
		regIns.setTipo(hojaPersonas.getCell(0, fila).getContents());
		regIns.setNombre(hojaPersonas.getCell(1, fila).getContents());
		regIns.setRut(hojaPersonas.getCell(2, fila).getContents());
		regIns.setFechaNacimiento(hojaPersonas.getCell(3, fila).getContents());
		regIns.setEstadoCivil(hojaPersonas.getCell(6, fila).getContents());
		if(hojaPersonas.getCell(6, fila).getContents().equals("Casado")==true){//verificamos que el ingresado est� casado (posea conyuge)
			regIns.setFechaMatrimonio(hojaPersonas.getCell(7, fila).getContents());
			regIns.setRutConyuge(hojaPersonas.getCell(8, fila).getContents());
		} else { 
			regIns.setFechaMatrimonio(null);
			regIns.setRutConyuge(null);
		}
		regIns.setFechaDeInscripcion(hojaPersonas.getCell(9, fila).getContents());
		regIns.setSexo(hojaPersonas.getCell(10, fila).getContents());
		regIns.setPassword(hojaPersonas.getCell(11, fila).getContents());
		regIns.setEstadoDeVida(hojaPersonas.getCell(12, fila).getContents());
		regIns.setFechaDefuncion(null);
		regIns.setNumTramites (Integer.parseInt(hojaPersonas.getCell(14, fila).getContents()));
		sucursal.registrar(regIns);	
	}
	
	private void lecturaFuncionario(int fila, Sheet hojaPersonas, Sucursal sucursal){
		Funcionario funcIns = new Funcionario();
		funcIns.setTipo(hojaPersonas.getCell(0, fila).getContents());
		funcIns.setNombre(hojaPersonas.getCell(1, fila).getContents());
		funcIns.setRut(hojaPersonas.getCell(2, fila).getContents());
		funcIns.setFechaNacimiento(hojaPersonas.getCell(3, fila).getContents());
		funcIns.setEstadoCivil(hojaPersonas.getCell(6, fila).getContents());
		if(hojaPersonas.getCell(6, fila).getContents().equals("Casado")==true){//verificamos que el ingresado est� casado (posea conyuge)
			funcIns.setFechaMatrimonio(hojaPersonas.getCell(7, fila).getContents());
			funcIns.setRutConyuge(hojaPersonas.getCell(8, fila).getContents());
		} else { 
			funcIns.setFechaMatrimonio(null);
			funcIns.setRutConyuge(null);
		}
		funcIns.setFechaDeInscripcion(hojaPersonas.getCell(9, fila).getContents());
		funcIns.setSexo(hojaPersonas.getCell(10, fila).getContents());
		funcIns.setPassword(hojaPersonas.getCell(11, fila).getContents());
		funcIns.setEstadoDeVida(hojaPersonas.getCell(12, fila).getContents());
		funcIns.setFechaDefuncion(hojaPersonas.getCell(13, fila).getContents());
		funcIns.setAntiguedad(Integer.parseInt(hojaPersonas.getCell(14, fila).getContents()));
		funcIns.setSueldo(Integer.parseInt(hojaPersonas.getCell(15, fila).getContents()));
		funcIns.setRegionNacimiento(hojaPersonas.getCell(17, fila).getContents());
		funcIns.setSucursalRegistro(hojaPersonas.getCell(18, fila).getContents());
		sucursal.registrar(funcIns);
	}

	private void lecturaAdministrador(int fila, Sheet hojaPersonas, RegistroCivil registro){
		Administrador adminIns = new Administrador();
		
		adminIns.setTipo(hojaPersonas.getCell(0, fila).getContents());
		adminIns.setNombre(hojaPersonas.getCell(1, fila).getContents());
		adminIns.setRut(hojaPersonas.getCell(2, fila).getContents());
		adminIns.setFechaNacimiento(hojaPersonas.getCell(3, fila).getContents());
		adminIns.setRegionNacimiento(hojaPersonas.getCell(4, fila).getContents());
		adminIns.setSucRegistro(hojaPersonas.getCell(5 , fila).getContents());
		adminIns.setEstadoCivil(hojaPersonas.getCell(6, fila).getContents());
		if(hojaPersonas.getCell(6, fila).getContents().equals("Casado")==true){//verificamos que el ingresado est� casado (posea conyuge)
			adminIns.setFechaMatrimonio(hojaPersonas.getCell(7, fila).getContents());
			adminIns.setRutConyuge(hojaPersonas.getCell(8, fila).getContents());
		}
		else{ 
			adminIns.setFechaMatrimonio(null);
			adminIns.setRutConyuge(null);
		}
		adminIns.setFechaDeInscripcion(hojaPersonas.getCell(9, fila).getContents());
		adminIns.setSexo(hojaPersonas.getCell(10, fila).getContents());
		adminIns.setPassword(hojaPersonas.getCell(11, fila).getContents());
		adminIns.setEstadoDeVida(hojaPersonas.getCell(12, fila).getContents());
		adminIns.setFechaDefuncion(null);
		adminIns.setCargo(hojaPersonas.getCell(14, fila).getContents());
		adminIns.setAntiguedad(hojaPersonas.getCell(15, fila).getContents());
		adminIns.setEmpleadosAsociados(Integer.parseInt(hojaPersonas.getCell(16, fila).getContents()));
		adminIns.setRegDeTrabajo(hojaPersonas.getCell(17, fila).getContents());
		adminIns.setSucDeTrabajo(hojaPersonas.getCell(18, fila).getContents());
		registro.agregarAdministrador(adminIns);
	}
	
	private void lecturaExtranjero(int fila, Sheet hojaPersonas, RegistroCivil registro){
		Extranjero extIns = new Extranjero();
		extIns.setTipo(hojaPersonas.getCell(0, fila).getContents());
		extIns.setNombre(hojaPersonas.getCell(1, fila).getContents());
		extIns.setRut(hojaPersonas.getCell(2, fila).getContents());
		extIns.setFechaNacimiento(hojaPersonas.getCell(3, fila).getContents());
		//extIns.setSucRegistro(hojaPersonas.getCell(5, fila).getContents());
		extIns.setEstadoCivil(hojaPersonas.getCell(6, fila).getContents());
		if(hojaPersonas.getCell(6, fila).getContents().equals("Casado")==true){//verificamos que el ingresado est� casado (posea conyuge)
			extIns.setFechaMatrimonio(hojaPersonas.getCell(7, fila).getContents());
			extIns.setRutConyuge(hojaPersonas.getCell(8, fila).getContents());
		}
		else{ 
			extIns.setFechaMatrimonio(null);
			extIns.setRutConyuge(null);
		}
		extIns.setFechaDeInscripcion(hojaPersonas.getCell(9, fila).getContents());
		extIns.setSexo(hojaPersonas.getCell(10, fila).getContents());
		extIns.setPassword(hojaPersonas.getCell(11, fila).getContents());
		extIns.setEstadoDeVida(hojaPersonas.getCell(12, fila).getContents());
		extIns.setFechaDefuncion(null);
		extIns.setNumTramites(Integer.parseInt(hojaPersonas.getCell(14, fila).getContents()));
		extIns.setPaisOrigen(hojaPersonas.getCell(15,  fila).getContents());
		extIns.setTipoVisa(hojaPersonas.getCell(16,  fila).getContents());
		registro.agregarExtranjero(extIns);
	}
	
	private void lecturaEmpresa (int fila, Sheet hojaPersonas, RegistroCivil registro){
		Empresa empIns = new Empresa();
		
		empIns.setTipo(hojaPersonas.getCell(0, fila).getContents());
		empIns.setNombre(hojaPersonas.getCell(1, fila).getContents());
		empIns.setRut(hojaPersonas.getCell(2, fila).getContents());
		empIns.setFechaNacimiento(hojaPersonas.getCell(3, fila).getContents());
		empIns.setEstadoCivil(null);
		empIns.setFechaMatrimonio(null);
		empIns.setRutConyuge(null);
		empIns.setFechaDeInscripcion(null);
		empIns.setSexo(null);
		empIns.setPassword(hojaPersonas.getCell(11, fila).getContents());
		empIns.setEstadoDeVida(hojaPersonas.getCell(12, fila).getContents());
		empIns.setFechaDefuncion(null);
		empIns.setNumTramites(Integer.parseInt(hojaPersonas.getCell(14, fila).getContents()));
		empIns.setNomRepresentante(hojaPersonas.getCell(15,  fila).getContents());
		empIns.setCapital(Integer.parseInt(hojaPersonas.getCell(16,  fila).getContents()));
		empIns.setRubro(hojaPersonas.getCell(17, fila).getContents());
		empIns.setDireccion(hojaPersonas.getCell(18, fila).getContents());
		registro.agregarEmpresa(empIns);
	}
	
	private void llenarRegiones (){
		Region region1 = new Region("Arica y Parinacota");
		regiones.add(region1);
		Region region2 = new Region("Tarapaca");
		regiones.add(region2);
		Region region3 = new Region("Antofagasta");
		regiones.add(region3);
		Region region4 = new Region("Atacama");
		regiones.add(region4);
		Region region5 = new Region("Coquimbo");
		regiones.add(region5);
		Region region6 = new Region("Valparaiso");
		regiones.add(region6);
		Region region7 = new Region("Metropolitana");
		regiones.add(region7);
		Region region8 = new Region("Lib. Gral. Bernardo o'Higgins");
		regiones.add(region8);
		Region region9 = new Region("Maule");
		regiones.add(region9);
		Region region10 = new Region("Biobio");
		regiones.add(region10);
		Region region11 = new Region("Araucania");
		regiones.add(region11);
		Region region12 = new Region("Los Rios");
		regiones.add(region12);
		Region region13 = new Region("Los lagos");
		regiones.add(region13);
		Region region14 = new Region("Aysen del Gral. Carlos Ibanez del Campo");
		regiones.add(region14);
		Region region15 = new Region("Magallanes y la Antartica Chilena");
		regiones.add(region15);
	}

	public ArrayList<Region> getRegiones() {
		return regiones;
	}

	public ArrayList<Administrador> getAdministradores() {
		return administradores;
	}
}
