package it.sanzari.logica;

import java.util.ArrayList;
import java.util.HashMap;

public class Fattura {
	
	private Cliente cliente;
	private ArrayList<Prestazione> prestazioni;
	private int numero;
	private String data;
	
	private double totaleIva;
	private double totaleFattura;

	public Fattura(Cliente c, ArrayList<Prestazione>p, int numero, String data){
		this.cliente=c;
		this.prestazioni=p;
		this.numero=numero;
		this.data=data;
	}

	public double getTotaleIva() {
		return totaleIva;
	}

	public void setTotaleIva(double totaleIva) {
		this.totaleIva = totaleIva;
	}

	public double getTotaleFattura() {
		return totaleFattura;
	}

	public void setTotaleFattura(double totaleFattura) {
		this.totaleFattura = totaleFattura;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ArrayList<Prestazione> getPrestazioni() {
		return prestazioni;
	}

	public void setPrestazioni(ArrayList<Prestazione> prestazioni) {
		this.prestazioni = prestazioni;
	}
	
	public void calcolaTotali(){
		HashMap<String, Double> listaIvaTmp = new HashMap();
		ArrayList<Double> listaIva= new ArrayList<Double>();
		for(Prestazione p: prestazioni){
			if(!listaIvaTmp.containsKey(p.getIva()+"")){
				listaIvaTmp.put(p.getIva()+"", p.getIva());
				listaIva.add(p.getIva());
				}
		}
		
		for(Double iva:listaIva){		//calcolo dell IVA
			double totaleParzialeIva=0;
			for(Prestazione p: prestazioni){
				if(p.getIva()==iva)
				totaleParzialeIva=totaleParzialeIva+((p.getImporto()*p.getIva())/100);
			}
			totaleIva=totaleIva+totaleParzialeIva;
		}
		
		for(Prestazione p:prestazioni){
			totaleFattura=totaleFattura+(p.getImporto());
		}
		
	}
	
	

}
