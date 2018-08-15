package logica;

import java.io.IOException;

import ventanas.VentanaLogin;

public class Principal {
	private static VentanaLogin princ=null;
	
	public static void main(String[] args) throws IOException {
		
		Lectura lec = new Lectura();
		RegistroCivil registro = new RegistroCivil();
		lec.LeerDatos(registro);
		
		princ = VentanaLogin.getInstance(registro);
		princ.mostrar();
	}

}
