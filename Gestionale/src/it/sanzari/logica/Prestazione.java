package it.sanzari.logica;

public class Prestazione {
	
	private int codice;
	private String descrizione;
	private double iva;
	private double prezzo;
	private double quantita;
	private double importo;
	
	
	public Prestazione(){}
	
	public Prestazione(int codice, String descrizione,double quantita,double iva,double prezzo,double importo){
		this.codice=codice;
		this.descrizione=descrizione;
		this.quantita=quantita;
		this.iva=iva;
		this.prezzo=prezzo;
		this.importo=importo;
	}


	public int getCodice() {
		return codice;
	}


	public void setCodice(int codice) {
		this.codice = codice;
	}


	public String getDescrizione() {
		return descrizione;
	}


	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}


	public double getIva() {
		return iva;
	}


	public void setIva(double iva) {
		this.iva = iva;
	}


	public double getPrezzo() {
		return prezzo;
	}


	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}


	public double getQuantita() {
		return quantita;
	}


	public void setQuantita(double quantita) {
		this.quantita = quantita;
	}


	public double getImporto() {
		return importo;
	}


	public void setImporto(double importo) {
		this.importo = importo;
	}
	
	

}
