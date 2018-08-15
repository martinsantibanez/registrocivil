package logica;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfWriter;

public class Administrador extends Persona {
	private String regDeTrabajo;
	private String SucDeTrabajo;
	private String cargo;
	private String antiguedad;
	private int empleadosAsociados;
	private String regionNacimiento;
	private String sucRegistro;
	
	public Administrador(){
		setTipo("Administrador");
	}
	
	public String getRegionNacimiento() {
		return regionNacimiento;
	}

	public void setRegionNacimiento(String RegionNacimiento) {
		this.regionNacimiento = RegionNacimiento;
	}

	public String getSucRegistro() {
		return sucRegistro;
	}

	public void setSucRegistro(String sucRegistro) {
		this.sucRegistro = sucRegistro;
	}
	
	public String getRegDeTrabajo() {
		return regDeTrabajo;
	}

	public void setRegDeTrabajo(String regDeTrabajo) {
		this.regDeTrabajo = regDeTrabajo;
	}

	public String getSucDeTrabajo() {
		return SucDeTrabajo;
	}

	public void setSucDeTrabajo(String sucDeTrabajo) {
		SucDeTrabajo = sucDeTrabajo;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getAntiguedad() {
		return antiguedad;
	}

	public void setAntiguedad(String antiguedad) {
		this.antiguedad = antiguedad;
	}

	public int getEmpleadosAsociados() {
		return empleadosAsociados;
	}

	public void setEmpleadosAsociados(int empleadosAsociados) {
		this.empleadosAsociados = empleadosAsociados;
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

}
