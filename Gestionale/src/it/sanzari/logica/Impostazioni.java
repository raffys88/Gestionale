package it.sanzari.logica;

import java.util.ArrayList;

public class Impostazioni {
	
	private double iva;
	private ArrayList<Double> listaIva;
	private int numeroFattura;
	private String pathSalvataggio;

	public Impostazioni(double iva, int numeroFattura){
		this.iva=iva;
		this.numeroFattura=numeroFattura;
		listaIva= new ArrayList<Double>();
	}
	
	
	
	public String getPathSalvataggio() {
		return pathSalvataggio;
	}
	
	public void addIva(double iva){
		listaIva.add(iva);
	}



	public void setPathSalvataggio(String pathSalvataggio) {
		this.pathSalvataggio = pathSalvataggio;
	}



	public Impostazioni(){}

	public double getIva() {
		return iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	public int getNumeroFattura() {
		return numeroFattura;
	}

	public void setNumeroFattura(int numeroFattura) {
		this.numeroFattura = numeroFattura;
	}
	
	public void leggiImpostazioni(){
//		 XMLStreamReader xmlr = xmlif.createXMLStreamReader("", new FileInputStream(""));
	}
	
	

}
