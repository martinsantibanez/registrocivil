package logica;

import ventanas.VentanaCertificadoMatrimonio;
import ventanas.VentanaCertificadoNacimiento;

public class GeneradorCertificados {

	/**
	 * Genera un certificado de nacimiento
	 * @param persona
	 */
	public GeneradorCertificados(RegistroCivil registro, Persona persona) {
		VentanaCertificadoNacimiento ventana = VentanaCertificadoNacimiento.getInstance(registro, persona);
		ventana.mostrar();
	}
	
	/**
	 * Genera un certificado de matrimonio.
	 * @param persona
	 * @param Conyuge
	 */
	public GeneradorCertificados(Persona persona, RegistroCivil registro) {
		
		Persona conyuge = registro.buscarPersona(persona.getRutConyuge());
		VentanaCertificadoMatrimonio ventana = VentanaCertificadoMatrimonio.getInstance(registro, persona, conyuge);
		ventana.mostrar();
	}

}
