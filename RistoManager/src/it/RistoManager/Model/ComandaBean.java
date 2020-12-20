package it.RistoManager.Model;

import java.util.ArrayList;
import java.util.List;

public class ComandaBean {
	
	private int id;
	private float totale;
	private List<ComandaItemBean> prodotti;
	private ClienteBean cliente;
	private boolean completata=false;
	
	public ComandaBean() {
		super();
		id=-1;
		totale=-1f;
		prodotti=new ArrayList<ComandaItemBean>();
		cliente=null;
	}
	
	public ComandaBean(float totale, List<ComandaItemBean> prodotti, ClienteBean cliente,
			boolean completata) {
		super();
		this.totale = totale;
		this.prodotti = prodotti;
		this.cliente = cliente;
		this.completata = completata;
	}

	
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
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getTotale() {
		
		totale=0;
		for(int i=0; i<prodotti.size(); i++)
		{
			totale+=prodotti.get(i).prezzoTotale();
		}
		return totale;
	}
	
	
	public int getNumeroTotaleProdotti() {
		
		int totale=0;
		
		for(int i=0; i<prodotti.size(); i++) {
			
			totale+=prodotti.get(i).getQuantita();
		}
		
		
		return totale;
	}

	public void setTotale(float totale) {
		this.totale = totale;
	}

	public List<ComandaItemBean> getProdotti() {
		return prodotti;
	}

	public void setProdotti(List<ComandaItemBean> prodotti) {
		this.prodotti = prodotti;
	}

	public ClienteBean getCliente() {
		return cliente;
	}

	public void setCliente(ClienteBean cliente) {
		this.cliente = cliente;
	}


	public boolean getCompletata() {
		return completata;
	}

	public void setCompletata(boolean completata) {
		this.completata = completata;
	}

	
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
