package logica;

@SuppressWarnings("serial")
public class LugarNoExisteException extends Exception {

	public LugarNoExisteException() {
		super("El lugar no existe.");
	}

}
