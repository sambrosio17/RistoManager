package it.RistoManager.Model;

import java.util.ArrayList;
import java.util.List;

public class ProdottoBean {
	
	private int id;
	private String nomeprodotto;
	private float prezzo;
	private String descrizione, immagine, categoria;
	private List<String> ingredienti;
	
	
	
	public ProdottoBean() {
		id=-1;
		nomeprodotto="";
		prezzo=-1f;
		descrizione="";
		immagine="";
		categoria="";
		ingredienti=new ArrayList<String>();
	}



	public ProdottoBean(String nomeprodotto, float prezzo, String descrizione, String immagine, String categoria,
			List<String> ingredienti) {
		super();
		this.nomeprodotto = nomeprodotto;
		this.prezzo = prezzo;
		this.descrizione = descrizione;
		this.immagine = immagine;
		this.categoria = categoria;
		this.ingredienti = ingredienti;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public float getPrezzo() {
		return prezzo;
	}



	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}



	public String getDescrizione() {
		return descrizione;
	}



	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}



	public String getImmagine() {
		return immagine;
	}



	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}



	public String getCategoria() {
		return categoria;
	}



	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}



	public List<String> getIngredienti() {
		return ingredienti;
	}



	public void setIngredienti(List<String> ingredienti) {
		this.ingredienti = ingredienti;
	}







	public String getNomeprodotto() {
		return nomeprodotto;
	}







	public void setNomeprodotto(String nomeprodotto) {
		this.nomeprodotto = nomeprodotto;
	}



	@Override
	public String toString() {
		return "ProdottoBean [id=" + id + ", nomeprodotto=" + nomeprodotto + ", prezzo=" + prezzo + ", descrizione="
				+ descrizione + ", immagine=" + immagine + ", categoria=" + categoria + ", ingredienti=" + ingredienti
				+ "]";
	}
	
	
	
	
	
	

}
