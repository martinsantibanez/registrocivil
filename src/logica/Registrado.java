package logica;

public class Registrado extends Persona{
	
	private int numTramites; //contabiliza la cantidad de tramites realizados a lo largo de la vida

	public Registrado (){ 
	     super();
	     setTipo("Registrado");
	     numTramites=1;
	}
	

	public int getNumTramites() {
		return numTramites;
	}

	public void setNumTramites(int numTramites) {
		this.numTramites = numTramites;
	}
	
	public void aumentarNumTramites(){
		numTramites++;
	}
	
	
	

	
}
