package it.RistoManager.Model;

import java.util.ArrayList;
import java.util.List;

public class RiepilogoBean {
	
	private float totale;
	private int numeroTotaleProdotti;
	private List<ComandaBean> comande;
	
	public RiepilogoBean() {
		super();
		totale=0;
		numeroTotaleProdotti=0;
		comande=new ArrayList<ComandaBean>();
	}
	
	
	public RiepilogoBean(float totale, int numeroTotaleProdotti, List<ComandaBean> comande) {
		super();
		this.totale = totale;
		this.numeroTotaleProdotti = numeroTotaleProdotti;
		this.comande = comande;
	}
	

	public float getTotale() {
		return totale;
	}


	public void setTotale(float totale) {
		this.totale = totale;
	}


	public int getNumeroTotaleProdotti() {
		
		return numeroTotaleProdotti;
		
	}


	public void setNumeroTotaleProdotti(int numeroTotaleProdotti) {
		this.numeroTotaleProdotti = numeroTotaleProdotti;
	}

	
	public void addComanda(ComandaBean c) {
		comande.add(c);
	}

	public List<ComandaBean> getComande() {
		return comande;
	}


	public void setComande(List<ComandaBean> comande) {
		this.comande = comande;
	}


	@Override
	public String toString() {
		return "RiepilogoBean [totale=" + totale + ", numeroTotaleProdotti=" + numeroTotaleProdotti + ", comande="
				+ comande + "]";
	}


	
	
	

}
