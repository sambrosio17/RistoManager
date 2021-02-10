package it.RistoManager.Model.Enity;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Rappresenta la comanda di un cliente
 *
 */
public class ComandaBean {
	
	private int id;
	private float totale;
	private List<ComandaItemBean> prodotti;
	private ClienteBean cliente;
	private boolean completata=false;
	
	/**
	 * Costruttore con parametri di default
	 */
	public ComandaBean() {
		super();
		id=-1;
		totale=-1f;
		prodotti=new ArrayList<ComandaItemBean>();
		cliente=null;
	}
	
	/**
	 * 
	 * @param totale Prezzo complessivo della comanda
	 * @param prodotti Lista dei prodotti della comanda
	 * @param cliente Cliente che effettua la comanda
	 * @param completata Stato della comanda
	 */
	public ComandaBean(float totale, List<ComandaItemBean> prodotti, ClienteBean cliente,
			boolean completata) {
		super();
		this.totale = totale;
		this.prodotti = prodotti;
		this.cliente = cliente;
		this.completata = completata;
	}

	
	/**
	 * Aggiunge un prodotto alla comanda
	 * @param p Prodotto da inserire
	 */
	public void aggiungiProdotto(ComandaItemBean p) {
		
		if(prodotti.size()==0) {
			
			System.out.println("****primo inserimento***");
			prodotti.add(p);
			return;
		}
		
		for(int i=0; i<prodotti.size(); i++)
		{
			if(prodotti.get(i).getProdotto().getId()==p.getProdotto().getId())
			{
				System.out.println("item già presente");
				prodotti.get(i).updateQuantita(p.getQuantita());
				System.out.println("incremento la quantità");
				return;
			}
		}
		System.out.println("Cart says: item non presente, aggiugo");
		prodotti.add(p);
		
	}
	
	/**
	 * Rimuove un prodotto dalla comanda
	 * @param id ID del prodotto da rimuovere
	 */
	public void rimuoviProdotto(int id) {
		
		for(int i=0; i<prodotti.size(); i++)
		{
			if(prodotti.get(i).getProdotto().getId()==id)
			{
				prodotti.remove(i);
				return;
			}
		}
		
	}
	
	/**
	 * Svuota la comanda
	 * 
	 */
	public void rimuoviTutti()
	{
		for(int i=0; i<prodotti.size(); i++)
		{
			prodotti.remove(i);
		}
	}
	
	public void setQuantita(int id, int quantita) {
		
		for(int i=0; i<prodotti.size(); i++)
		{
			if(prodotti.get(i).getProdotto().getId()==id)
			{
				prodotti.get(i).setQuantita(quantita);
			}
		}
	}
	
	/**
	 * Restituisce un prodotto dalla comanda
	 * @param id del prodotto da restituire
	 * @return Prodotto cercato
	 */
	public ComandaItemBean getComandaItem(int id) {
		
		for(int i=0; i<prodotti.size(); i++)
		{
			if(prodotti.get(i).getProdotto().getId()==id)
			{
				return prodotti.get(i);
			}
		}
		return null;
		
	}
	
	/**
	 * 
	 * @return ID della comanda
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * @param id ID della comanda
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @return Prezzo totale della comanda
	 */
	public float getTotale() {
		
		totale=0;
		for(int i=0; i<prodotti.size(); i++)
		{
			totale+=prodotti.get(i).prezzoTotale();
		}
		return totale;
	}
	
	/**
	 * 
	 * @return NUmero totale dei prodotti della comanda
	 */
	public int getNumeroTotaleProdotti() {
		
		int totale=0;
		
		for(int i=0; i<prodotti.size(); i++) {
			
			totale+=prodotti.get(i).getQuantita();
		}
		
		
		return totale;
	}

	/**
	 * 
	 * @param totale Prezzo totale della comanda
	 */
	public void setTotale(float totale) {
		this.totale = totale;
	}

	/**
	 * 
	 * @return Lista di prodotti della comanda
	 */
	public List<ComandaItemBean> getProdotti() {
		return prodotti;
	}

	/**
	 * 
	 * @param prodotti Lista di prodotti della comanda
	 */
	public void setProdotti(List<ComandaItemBean> prodotti) {
		this.prodotti = prodotti;
	}

	/**
	 * 
	 * @return Cliente a cui è associata la comanda
	 */
	public ClienteBean getCliente() {
		return cliente;
	}

	/**
	 * 
	 * @param cliente CLiente a cui è associata la comanda
	 */
	public void setCliente(ClienteBean cliente) {
		this.cliente = cliente;
	}

	/**
	 * 
	 * @return Stato della comanda
	 */
	public boolean getCompletata() {
		return completata;
	}

	/**
	 * 
	 * @param completata Stato della comanda
	 */
	public void setCompletata(boolean completata) {
		this.completata = completata;
	}

	/**
	 * Controlla se un prodotto è contenuto nella comanda
	 * @param p Prodotto da cercare
	 * @return true: se il prodotto è contenuto <br> false: se il prodotto non è contenuto
	 */
	public boolean isContenuto(ComandaItemBean p) {
		
		boolean result=false;
			
		for(ComandaItemBean item : prodotti) {
			ProdottoBean pItem=item.getProdotto();
			ProdottoBean pConfronto=p.getProdotto();
			if(pItem.getId()==pConfronto.getId())
				result=!result;
		}
		
		return result;
	}
	
	@Override
	public String toString() {
		return "****ComandaBean [id=" + id + ",\n totale=" + totale + ",\n prodotti=" + prodotti + ",\n cliente=" + cliente
				+ ",\n completata=" + completata + "]"+"****\n";
	}

}
