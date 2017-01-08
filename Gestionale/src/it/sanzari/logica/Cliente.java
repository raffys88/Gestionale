package it.sanzari.logica;

public class Cliente {
	
	private String nome;
	private String cognome;
	private String ragioneSociale;
	private String via;
	private String civico;
	private String citta;
	private String provincia;
	private String telefono;
	private String pIva;
	private String cap;
	
	public Cliente(){}
	
	public Cliente(String nome,String cognome,String ragioneSociale,String via,String civico,String citta,String provincia,String telefono, String pIva,String cap){
		this.nome=nome;
		this.cognome=cognome;
		this.ragioneSociale=ragioneSociale;
		this.via=via;
		this.civico=civico;
		this.citta=citta;
		this.provincia=provincia;
		this.telefono=telefono;
		this.pIva=pIva;
		this.cap=cap;
	}
	

	public String getpIva() {
		return pIva;
	}

	public void setpIva(String pIva) {
		this.pIva = pIva;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getRagioneSociale() {
		return ragioneSociale;
	}

	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public String getCivico() {
		return civico;
	}

	public void setCivico(String civico) {
		this.civico = civico;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public String getIntestazione(){
		if(!ragioneSociale.equalsIgnoreCase(""))
			return cognome+" "+nome+" - "+ragioneSociale;
		else
			return cognome+" "+nome;
	}
	
	public String getIndirizzo(){
		return via+" "+civico;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}
	
	
	
	

}
