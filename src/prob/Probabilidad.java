package prob;

public class Probabilidad {
	
	private String simbolo;
	private double probabilidad;
	
	
	

	public Probabilidad(String simbolo, double probabilidad) {
		super();
		this.simbolo = simbolo;
		this.probabilidad = probabilidad;
	}


	public double getProbabilidad() {
		return probabilidad;
	}


	public void setProbabilidad(double probabilidad) {
		this.probabilidad = probabilidad;
	}


	public String getSimbolo() {
		return simbolo;
	}
	
	
	
	

}
