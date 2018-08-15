package logica;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.itextpdf.text.*;
import com.itextpdf.text.Font.*;
import com.itextpdf.text.pdf.PdfWriter;

public class Funcionario extends Persona {
	private String regionNacimiento;
	private  String sucursalRegistro;
	private int antiguedad;
	private int sueldo;
	
	public Funcionario(){
		super();
		setTipo("Funcionario");
	}
	
	
	public void escribirReporte(ArrayList<Persona> reporte, Persona usuario) throws Exception{
		if (reporte==null){
			return;
		}
		else{
			Document document = new Document();     
			Font fuente= new Font(FontFamily.TIMES_ROMAN);
			Calendar cal = Calendar.getInstance();
			Date fecha = new Date( cal.getTimeInMillis() );
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			fuente.setColor(BaseColor.BLACK);
			PdfWriter.getInstance(document, new FileOutputStream("Reporte_"+usuario.getNombre()+".pdf"));
			document.open();
			String parrafo =  "SERVICIO DE REGISTRO                                                                  FECHA:"+formato.format(fecha)+" \n" 
							+ "CIVIL E IDENTIFICACION\n"
							+ "--------------------------------------------------------------------------------------------------------------------- \n \n ";
			String parrafo2 = "											REPORTE  \n \n                        ";
			document.add(new Paragraph(parrafo,fuente));
			document.add(new Paragraph(parrafo2));	         
			for (int i = 0; i<reporte.size();i++){
				String parrafo3 = "--------------------------------"
								+"\nNombre: "+reporte.get(i).getNombre()
								+"\nFecha Nacimiento: "+reporte.get(i).getFechaSlash()
								+"\nRut: "+reporte.get(i).getRut()
								+"\nEstado Civil: "+reporte.get(i).getEstadoCivil()
								+"\nSexo: "+reporte.get(i).getSexo()
								+"\n";
			document.add(new Paragraph(parrafo3));
			}
			document.close();
		}
	}


	public String getRegionNacimiento() {
		return regionNacimiento;
	}


	public void setRegionNacimiento(String regionNacimiento) {
		this.regionNacimiento = regionNacimiento;
	}


	public String getSucursalRegistro() {
		return sucursalRegistro;
	}


	public void setSucursalRegistro(String sucursalRegistro) {
		this.sucursalRegistro = sucursalRegistro;
	}


	public int getAntiguedad() {
		return antiguedad;
	}


	public void setAntiguedad(int antiguedad) {
		this.antiguedad = antiguedad;
	}


	public int getSueldo() {
		return sueldo;
	}


	public void setSueldo(int sueldo) {
		this.sueldo = sueldo;
	}

	
}
