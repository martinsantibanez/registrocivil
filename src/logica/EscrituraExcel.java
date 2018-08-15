package logica;
import java.io.*;
import java.util.ArrayList;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class EscrituraExcel {

	private HSSFWorkbook workbook;

	public EscrituraExcel(){
	}

	public boolean EscribirDatos(RegistroCivil registro) throws IOException {
		FileInputStream input_document = new FileInputStream(new File("proyecto.xls"));
		workbook = new HSSFWorkbook(input_document);
		workbook.removeSheetAt(0);
		workbook.removeSheetAt(0);
		HSSFSheet hojaPersonas = workbook.createSheet("personas");
		HSSFSheet hojaSucursales = workbook.createSheet("sucursales");
		int numSuc = 0;
		int numPers = 1;
		ArrayList <Region> regiones = registro.getRegiones();
		for (Region region : regiones){
			ArrayList<Sucursal> sucursales = region.getSucursales();
			String nombReg = region.getNombre();
			for (Sucursal sucursal : sucursales){
				String nombSuc = sucursal.getNombre();
				hojaSucursales.createRow(numSuc);
				escribirSucursal(sucursal, hojaSucursales, numSuc, nombReg);
				numSuc++;
				ArrayList <Registrado> registrados = sucursal.getRegistrados();
				for(Registrado registrado : registrados){
					hojaPersonas.createRow(numPers);
					escribirRegistrado(registrado, hojaPersonas, numPers, nombReg, nombSuc);
					numPers++;
				}
				ArrayList <Funcionario> funcionarios = sucursal.getFuncionarios();
				for(Funcionario funcionario : funcionarios){
					hojaPersonas.createRow(numPers);
					escribirFuncionario(funcionario, hojaPersonas, numPers, nombReg, nombSuc);
					numPers++;
				}
			}
		}
		ListaAdministradores admin = registro.getListaAdministradores(); //Recorrido con iterator
		IteratorList iterador = admin.iterador();
		while (iterador.hasNext()){
			Administrador administrador = (Administrador) iterador.next();
			hojaPersonas.createRow(numPers);
			escribirAdministrador(administrador, hojaPersonas, numPers);
			numPers++;
		}

		ArrayList <Extranjero> extranjeros = registro.getExtranjeros();
		for(Extranjero extranjero : extranjeros){
			hojaPersonas.createRow(numPers);
			escribirExtranjero(extranjero, hojaPersonas, numPers);
			numPers++;
		}
				
		ArrayList <Empresa> empresas = registro.getEmpresas();
		for(Empresa empresa : empresas){
			hojaPersonas.createRow(numPers);
			escribirEmpresa(empresa, hojaPersonas, numPers);
			numPers++;
		}
		
        input_document.close();
        FileOutputStream output_file = new FileOutputStream(new File("proyecto.xls"));
        workbook.write(output_file);
        output_file.close();
		return true;
	}

    private void escribirEmpresa(Empresa empresa, HSSFSheet hojaPersonas, int numPers){
        HSSFCell cellTipo = (HSSFCell) hojaPersonas.getRow(numPers).createCell(0);
        HSSFCell cellNombre = (HSSFCell) hojaPersonas.getRow(numPers).createCell(1);
        HSSFCell cellRut = (HSSFCell) hojaPersonas.getRow(numPers).createCell(2);
        HSSFCell cellFechaNacimiento = (HSSFCell) hojaPersonas.getRow(numPers).createCell(3);
        HSSFCell cellPassword = (HSSFCell) hojaPersonas.getRow(numPers).createCell(11);
        HSSFCell cellEstadoDeVida = (HSSFCell) hojaPersonas.getRow(numPers).createCell(12);
        HSSFCell cellNumTramites = (HSSFCell) hojaPersonas.getRow(numPers).createCell(14);
        HSSFCell cellNomRepresentante = (HSSFCell) hojaPersonas.getRow(numPers).createCell(15);
        HSSFCell cellCapital = (HSSFCell) hojaPersonas.getRow(numPers).createCell(16);
        HSSFCell cellRubro = (HSSFCell) hojaPersonas.getRow(numPers).createCell(17);
        HSSFCell cellDireccion = (HSSFCell) hojaPersonas.getRow(numPers).createCell(18);
        
        ((HSSFCell) cellTipo).setCellValue(empresa.getTipo());
        ((HSSFCell) cellNombre).setCellValue(empresa.getNombre());
        ((HSSFCell) cellRut).setCellValue(empresa.getRut());
        ((HSSFCell) cellFechaNacimiento).setCellValue(empresa.getFechaNacimiento());
        ((HSSFCell) cellPassword).setCellValue(empresa.getPassword());
        ((HSSFCell) cellEstadoDeVida).setCellValue(empresa.getEstadoDeVida());
        ((HSSFCell) cellNumTramites).setCellValue(empresa.getNumTramites());
        ((HSSFCell) cellNomRepresentante).setCellValue(empresa.getNomRepresentante());
        ((HSSFCell) cellCapital).setCellValue(empresa.getCapital());
        ((HSSFCell) cellRubro).setCellValue(empresa.getRubro());
        ((HSSFCell) cellDireccion).setCellValue(empresa.getDireccion());
    }

    private void escribirExtranjero(Extranjero extranjero, HSSFSheet hojaPersonas, int numPers){
        HSSFCell cellTipo = (HSSFCell) hojaPersonas.getRow(numPers).createCell(0);
        HSSFCell cellNombre = (HSSFCell) hojaPersonas.getRow(numPers).createCell(1);
        HSSFCell cellRut = (HSSFCell) hojaPersonas.getRow(numPers).createCell(2);
        HSSFCell cellFechaN = (HSSFCell) hojaPersonas.getRow(numPers).createCell(3);
        HSSFCell cellSucursalR = (HSSFCell) hojaPersonas.getRow(numPers).createCell(5);
        HSSFCell cellEstadoC = (HSSFCell) hojaPersonas.getRow(numPers).createCell(6);
        HSSFCell cellFechaM = (HSSFCell) hojaPersonas.getRow(numPers).createCell(7);
        HSSFCell cellRutC= (HSSFCell) hojaPersonas.getRow(numPers).createCell(8);
        HSSFCell cellFechaIns = (HSSFCell) hojaPersonas.getRow(numPers).createCell(9);
        HSSFCell cellSexo = (HSSFCell) hojaPersonas.getRow(numPers).createCell(10);
        HSSFCell cellPassword = (HSSFCell) hojaPersonas.getRow(numPers).createCell(11);
        HSSFCell cellEstadoVida = (HSSFCell) hojaPersonas.getRow(numPers).createCell(12);
        HSSFCell cellFechaD = (HSSFCell) hojaPersonas.getRow(numPers).createCell(13);
        HSSFCell cellNumTramites = (HSSFCell) hojaPersonas.getRow(numPers).createCell(14);
        HSSFCell cellPaisOrigen = (HSSFCell) hojaPersonas.getRow(numPers).createCell(15);
        HSSFCell cellTipoVisa = (HSSFCell) hojaPersonas.getRow(numPers).createCell(16);

        ((HSSFCell) cellTipo).setCellValue(extranjero.getTipo());
        ((HSSFCell) cellNombre).setCellValue(extranjero.getNombre());
        ((HSSFCell) cellRut).setCellValue(extranjero.getRut());
        ((HSSFCell) cellFechaN).setCellValue(extranjero.getFechaNacimiento());
        ((HSSFCell) cellSucursalR).setCellValue(extranjero.getSucRegistro());
        ((HSSFCell) cellEstadoC).setCellValue(extranjero.getEstadoCivil());
        if(extranjero.getEstadoCivil().compareTo("Casado")==0){
			((HSSFCell) cellFechaM).setCellValue(extranjero.getFechaMatrimonio());		
			((HSSFCell) cellRutC).setCellValue(extranjero.getRutConyuge());
		}
        ((HSSFCell) cellFechaIns).setCellValue(extranjero.getFechaDeInscripcion());
        ((HSSFCell) cellSexo).setCellValue(extranjero.getSexo());
        ((HSSFCell) cellPassword).setCellValue(extranjero.getPassword());
		((HSSFCell) cellEstadoVida).setCellValue(extranjero.getEstadoDeVida());
		if(extranjero.getEstadoDeVida().compareTo("0")==0){
        	((HSSFCell) cellEstadoVida).setCellValue("0");
        	((HSSFCell) cellFechaD).setCellValue(extranjero.getFechaDefuncion());
        }
        ((HSSFCell) cellNumTramites).setCellValue(extranjero.getNumTramites());
        ((HSSFCell) cellPaisOrigen).setCellValue(extranjero.getPaisOrigen());
        ((HSSFCell) cellTipoVisa).setCellValue(extranjero.getTipoVisa());
    }

    private void escribirAdministrador(Administrador administrador, HSSFSheet hojaPersonas, int numPers){                   
        HSSFCell cellTipo = (HSSFCell) hojaPersonas.getRow(numPers).createCell(0);
        HSSFCell cellNombre = (HSSFCell) hojaPersonas.getRow(numPers).createCell(1);
        HSSFCell cellRut = (HSSFCell) hojaPersonas.getRow(numPers).createCell(2);
        HSSFCell cellFechaN = (HSSFCell) hojaPersonas.getRow(numPers).createCell(3);
        HSSFCell cellRegionN= (HSSFCell) hojaPersonas.getRow(numPers).createCell(4);
        HSSFCell cellSucursalR = (HSSFCell) hojaPersonas.getRow(numPers).createCell(5);
        HSSFCell cellEstadoC = (HSSFCell) hojaPersonas.getRow(numPers).createCell(6);
        HSSFCell cellFechaM = (HSSFCell) hojaPersonas.getRow(numPers).createCell(7);
        HSSFCell cellRutC= (HSSFCell) hojaPersonas.getRow(numPers).createCell(8);
        HSSFCell cellFechaIns = (HSSFCell) hojaPersonas.getRow(numPers).createCell(9);
        HSSFCell cellSexo = (HSSFCell) hojaPersonas.getRow(numPers).createCell(10);
        HSSFCell cellPassword = (HSSFCell) hojaPersonas.getRow(numPers).createCell(11);
        HSSFCell cellEstadoVida = (HSSFCell) hojaPersonas.getRow(numPers).createCell(12);
        HSSFCell cellFechaD = (HSSFCell) hojaPersonas.getRow(numPers).createCell(13);
        HSSFCell cellCargo = (HSSFCell) hojaPersonas.getRow(numPers).createCell(14);
        HSSFCell cellAntiguedad = (HSSFCell) hojaPersonas.getRow(numPers).createCell(15);
        HSSFCell cellEmpleadosA = (HSSFCell) hojaPersonas.getRow(numPers).createCell(16);
        HSSFCell cellRegionT = (HSSFCell) hojaPersonas.getRow(numPers).createCell(17);
        HSSFCell cellSucTrabajo = (HSSFCell) hojaPersonas.getRow(numPers).createCell(18);
        System.out.println(administrador.getNombre());
        System.out.println(administrador.getTipo());
        ((HSSFCell) cellTipo).setCellValue(administrador.getTipo());
        ((HSSFCell) cellNombre).setCellValue(administrador.getNombre());
        ((HSSFCell) cellRut).setCellValue(administrador.getRut());
        ((HSSFCell) cellFechaN).setCellValue(administrador.getFechaNacimiento());
        ((HSSFCell) cellRegionN).setCellValue(administrador.getRegionNacimiento());
        ((HSSFCell) cellSucursalR).setCellValue(administrador.getSucRegistro());
        ((HSSFCell) cellEstadoC).setCellValue(administrador.getEstadoCivil());
    	if(administrador.getEstadoCivil().compareTo("Casado")==0){
			((HSSFCell) cellFechaM).setCellValue(administrador.getFechaMatrimonio());		
			((HSSFCell) cellRutC).setCellValue(administrador.getRutConyuge());
		}       
        ((HSSFCell) cellFechaIns).setCellValue(administrador.getFechaDeInscripcion());
        ((HSSFCell) cellSexo).setCellValue(administrador.getSexo());
        ((HSSFCell) cellPassword).setCellValue(administrador.getPassword());
		((HSSFCell) cellEstadoVida).setCellValue(administrador.getEstadoDeVida());
		if(administrador.getEstadoDeVida().compareTo("0")==0){
        	((HSSFCell) cellEstadoVida).setCellValue("0");
        	((HSSFCell) cellFechaD).setCellValue(administrador.getFechaDefuncion());
        }
        ((HSSFCell) cellCargo).setCellValue(administrador.getCargo());
        ((HSSFCell) cellAntiguedad).setCellValue(administrador.getAntiguedad());
        ((HSSFCell) cellEmpleadosA).setCellValue(administrador.getEmpleadosAsociados());
        ((HSSFCell) cellRegionT).setCellValue(administrador.getRegDeTrabajo());
        ((HSSFCell) cellSucTrabajo).setCellValue(administrador.getSucDeTrabajo());

    }
    

    private void escribirFuncionario(Funcionario funcionario, HSSFSheet hojaPersonas, int numPers, String nombReg, String nombSuc){
        HSSFCell cellTipo = (HSSFCell) hojaPersonas.getRow(numPers).createCell(0);
        HSSFCell cellNombre = (HSSFCell) hojaPersonas.getRow(numPers).createCell(1);
        HSSFCell cellRut = (HSSFCell) hojaPersonas.getRow(numPers).createCell(2);
        HSSFCell cellFechaN = (HSSFCell) hojaPersonas.getRow(numPers).createCell(3);
        HSSFCell cellRegionTrabajo = (HSSFCell) hojaPersonas.getRow(numPers).createCell(4);
        HSSFCell cellSucTrabajo = (HSSFCell) hojaPersonas.getRow(numPers).createCell(5);
        HSSFCell cellEstadoC = (HSSFCell) hojaPersonas.getRow(numPers).createCell(6);
        HSSFCell cellFechaM = (HSSFCell) hojaPersonas.getRow(numPers).createCell(7);
        HSSFCell cellRutC= (HSSFCell) hojaPersonas.getRow(numPers).createCell(8);
        HSSFCell cellFechaIns = (HSSFCell) hojaPersonas.getRow(numPers).createCell(9);
        HSSFCell cellSexo = (HSSFCell) hojaPersonas.getRow(numPers).createCell(10);
        HSSFCell cellPassword = (HSSFCell) hojaPersonas.getRow(numPers).createCell(11);
        HSSFCell cellEstadoVida = (HSSFCell) hojaPersonas.getRow(numPers).createCell(12);
        HSSFCell cellFechaD = (HSSFCell) hojaPersonas.getRow(numPers).createCell(13);
        HSSFCell cellAntiguedad = (HSSFCell) hojaPersonas.getRow(numPers).createCell(14);
        HSSFCell cellSueldo = (HSSFCell) hojaPersonas.getRow(numPers).createCell(15);
        HSSFCell cellRegionN= (HSSFCell) hojaPersonas.getRow(numPers).createCell(17);
        HSSFCell cellSucursalR = (HSSFCell) hojaPersonas.getRow(numPers).createCell(18);
        ((HSSFCell) cellTipo).setCellValue(funcionario.getTipo());
        ((HSSFCell) cellNombre).setCellValue(funcionario.getNombre());
        ((HSSFCell) cellRut).setCellValue(funcionario.getRut());
        ((HSSFCell) cellFechaN).setCellValue(funcionario.getFechaNacimiento());
        ((HSSFCell) cellRegionTrabajo).setCellValue(nombReg);
        ((HSSFCell) cellSucTrabajo).setCellValue(nombSuc);
        ((HSSFCell) cellEstadoC).setCellValue(funcionario.getEstadoCivil());
		if(funcionario.getEstadoCivil().compareTo("Casado")==0){
			((HSSFCell) cellFechaM).setCellValue(funcionario.getFechaMatrimonio());		
			((HSSFCell) cellRutC).setCellValue(funcionario.getRutConyuge());
		}
        ((HSSFCell) cellFechaIns).setCellValue(funcionario.getFechaDeInscripcion());
        ((HSSFCell) cellSexo).setCellValue(funcionario.getSexo());
        ((HSSFCell) cellPassword).setCellValue(funcionario.getPassword());
		((HSSFCell) cellEstadoVida).setCellValue(funcionario.getEstadoDeVida());
		if(funcionario.getEstadoDeVida().compareTo("0")==0){
        	((HSSFCell) cellEstadoVida).setCellValue("0");
        	((HSSFCell) cellFechaD).setCellValue(funcionario.getFechaDefuncion());
        }
        ((HSSFCell) cellAntiguedad).setCellValue(funcionario.getAntiguedad());
        System.out.println(funcionario.getNombre());
        ((HSSFCell) cellSueldo).setCellValue(funcionario.getSueldo());
        ((HSSFCell) cellRegionN).setCellValue(funcionario.getRegionNacimiento());
        ((HSSFCell) cellSucursalR).setCellValue(funcionario.getSucursalRegistro());
    }
    
	private void escribirRegistrado(Registrado registrado, HSSFSheet hojaPersonas, int numPers, String nombReg, String nombSuc){
		HSSFCell cellTipo = (HSSFCell) hojaPersonas.getRow(numPers).createCell(0);
		HSSFCell cellNombre = (HSSFCell) hojaPersonas.getRow(numPers).createCell(1);
		HSSFCell cellRut = (HSSFCell) hojaPersonas.getRow(numPers).createCell(2);
		HSSFCell cellFechaN = (HSSFCell) hojaPersonas.getRow(numPers).createCell(3);
        HSSFCell cellRegionN= (HSSFCell) hojaPersonas.getRow(numPers).createCell(4);
        HSSFCell cellSucursalR = (HSSFCell) hojaPersonas.getRow(numPers).createCell(5);
        HSSFCell cellEstadoC = (HSSFCell) hojaPersonas.getRow(numPers).createCell(6);
        HSSFCell cellFechaM = (HSSFCell) hojaPersonas.getRow(numPers).createCell(7);
        HSSFCell cellRutC= (HSSFCell) hojaPersonas.getRow(numPers).createCell(8);
        HSSFCell cellFechaIns = (HSSFCell) hojaPersonas.getRow(numPers).createCell(9);
        HSSFCell cellSexo = (HSSFCell) hojaPersonas.getRow(numPers).createCell(10);
        HSSFCell cellPassword = (HSSFCell) hojaPersonas.getRow(numPers).createCell(11);
        HSSFCell cellEstadoVida = (HSSFCell) hojaPersonas.getRow(numPers).createCell(12);
        HSSFCell cellFechaD = (HSSFCell) hojaPersonas.getRow(numPers).createCell(13);
        HSSFCell cellNumTramites = (HSSFCell) hojaPersonas.getRow(numPers).createCell(14);
        ((HSSFCell) cellTipo).setCellValue(registrado.getTipo());
        ((HSSFCell) cellNombre).setCellValue(registrado.getNombre());
        ((HSSFCell) cellRut).setCellValue(registrado.getRut());
		((HSSFCell) cellFechaN).setCellValue(registrado.getFechaNacimiento());
		((HSSFCell) cellRegionN).setCellValue(nombReg);
		((HSSFCell) cellSucursalR).setCellValue(nombSuc);
		((HSSFCell) cellEstadoC).setCellValue(registrado.getEstadoCivil());
		if(registrado.getEstadoCivil().compareTo("Casado")==0){
			((HSSFCell) cellFechaM).setCellValue(registrado.getFechaMatrimonio());		
			((HSSFCell) cellRutC).setCellValue(registrado.getRutConyuge());
		}
		((HSSFCell) cellFechaIns).setCellValue(registrado.getFechaDeInscripcion());
		((HSSFCell) cellSexo).setCellValue(registrado.getSexo());					
		((HSSFCell) cellPassword).setCellValue(registrado.getPassword());
		((HSSFCell) cellEstadoVida).setCellValue(registrado.getEstadoDeVida());
		if(registrado.getEstadoDeVida().compareTo("0")==0){
        	((HSSFCell) cellEstadoVida).setCellValue("0");
        	((HSSFCell) cellFechaD).setCellValue(registrado.getFechaDefuncion());
        }
		((HSSFCell) cellNumTramites).setCellValue(registrado.getNumTramites());
	}
	
	private void escribirSucursal(Sucursal suc, HSSFSheet hojasucursal, int numSuc, String nombRegion){
			HSSFCell cellRegion = (HSSFCell) hojasucursal.getRow(numSuc).createCell(0);
			HSSFCell cellNombre = (HSSFCell) hojasucursal.getRow(numSuc).createCell(1);
			HSSFCell cellDireccion = (HSSFCell) hojasucursal.getRow(numSuc).createCell(2);
			HSSFCell cellTelefono = (HSSFCell) hojasucursal.getRow(numSuc).createCell(3);
			HSSFCell cellHorario = (HSSFCell) hojasucursal.getRow(numSuc).createCell(4);
			((HSSFCell) cellRegion).setCellValue(nombRegion);
			((HSSFCell) cellNombre).setCellValue(suc.getNombre());
			((HSSFCell) cellDireccion).setCellValue(suc.getDireccion());
			((HSSFCell) cellTelefono).setCellValue(suc.getTelefono());
			((HSSFCell) cellHorario).setCellValue(suc.getHorario());
	}
	
	

	


}
