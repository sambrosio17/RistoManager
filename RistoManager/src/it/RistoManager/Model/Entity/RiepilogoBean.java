package it.RistoManager.Model.Enity;

import java.util.ArrayList;
import java.util.List;

/**
 * Rappresenta il riepilogo di un cliente
 *
 */
public class RiepilogoBean {

	private float totale;
	private int numeroTotaleProdotti;
	private List<ComandaBean> comande;

	/**
	 * Costruttore con parametri di default
	 */
	public RiepilogoBean() {
		super();
		totale = 0;
		numeroTotaleProdotti = 0;
		comande = new ArrayList<ComandaBean>();
	}

	/**
	 * COstruttore con parametri
	 * 
	 * @param totale               Prezzo totale
	 * @param numeroTotaleProdotti Numero totale dei prodotti
	 * @param comande              Lista delle comande
	 */
	public RiepilogoBean(float totale, int numeroTotaleProdotti, List<ComandaBean> comande) {
		super();
		this.totale = totale;
		this.numeroTotaleProdotti = numeroTotaleProdotti;
		this.comande = comande;
	}

	/**
	 * 
	 * @return Prezzo totale
	 */
	public float getTotale() {
		return totale;
	}

	/**
	 * 
	 * @param totale Prezzo totale
	 */
	public void setTotale(float totale) {
		this.totale = totale;
	}

	/**
	 * 
	 * @return Numero totale dei prodotti
	 */
	public int getNumeroTotaleProdotti() {

		return numeroTotaleProdotti;

	}

	/**
	 * 
	 * @param numeroTotaleProdotti Numero totale dei 
	 */
	public void setNumeroTotaleProdotti(int numeroTotaleProdotti) {
		this.numeroTotaleProdotti = numeroTotaleProdotti;
	}

	/**
	 * Aggiunge una comanda alla lista
	 * @param c COmanda da aggiungere
	 */
	public void addComanda(ComandaBean c) {
		comande.add(c);
	}

	/**
	 * 
	 * @return Lista delle comande
	 */
	public List<ComandaBean> getComande() {
		return comande;
	}

	/**
	 * 
	 * @param comande Lista delle comande
	 */
	public void setComande(List<ComandaBean> comande) {
		this.comande = comande;
	}

	@Override
	public String toString() {
		return "RiepilogoBean [totale=" + totale + ", numeroTotaleProdotti=" + numeroTotaleProdotti + ", comande="
				+ comande + "]";
	}

}
