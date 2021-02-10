package it.RistoManager.Model.Enity;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Rappresenta un prodotto del menu
 *
 */
public class ProdottoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String nomeprodotto;
	private float prezzo;
	private String descrizione, immagine, categoria;
	private List<String> ingredienti;

	//
	private int line, calorie, calorieDaiGrassi, grassi, grassiSaturati, acidiGrassiTrans, colesterolo, sodio,
			carboidrati, fibre, zuccheri, proteine, vitaminaA, vitaminaC, calcio, ferro;
	private double size;

	/**
	 * Costruttore con parametri di default
	 */
	public ProdottoBean() {
		id = -1;
		nomeprodotto = "";
		prezzo = -1f;
		descrizione = "";
		immagine = "";
		categoria = "";
		ingredienti = new ArrayList<String>();
		immagineDefault();
	}

	/**
	 * Costruttore con parametri
	 * 
	 * @param nomeprodotto Nome del prodotto
	 * @param prezzo       Prezo del prodotto
	 * @param descrizione  Descrizione del prodotto
	 * @param immagine     Immagine del prodotto
	 * @param categoria    Categoria del prodotto
	 * @param ingredienti  Ingredienti del prodotto
	 */
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

	/**
	 * 
	 * @return ID del prodotto
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * @param id ID del prodotto
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @return Prezo del prodotto
	 */
	public float getPrezzo() {
		return prezzo;
	}

	/**
	 * 
	 * @param prezzo Prezzo del prodotto
	 */
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	/**
	 * 
	 * @return Descrizione del prodotto
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * 
	 * @param descrizione Descrizione del prodotto
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	/**
	 * 
	 * @return Immagine del prodotto
	 */
	public String getImmagine() {
		immagineDefault();
		return immagine;
	}

	/**
	 * 
	 * @param immagine Immagine del prodotto
	 */
	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}

	public void immagineDefault() {
		this.immagine = "https://static.cookist.it/wp-content/uploads/sites/21/2019/09/colazione-sana-5-idee.jpg";
	}
	/**
	 * 
	 * @return Categoria del prodotto
	 */
	public String getCategoria() {
		return categoria;
	}

	/**
	 * 
	 * @param categoria Categoria del prodotto
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	/**
	 * 
	 * @return Lista degli ingredienti del prodotto
	 */
	public List<String> getIngredienti() {
		return ingredienti;
	}

	/**
	 * 
	 * @param ingredienti Lista degli ingredienti del prodotto
	 */
	public void setIngredienti(List<String> ingredienti) {
		this.ingredienti = ingredienti;
	}

	/**
	 * 
	 * @return Nome del prodotto
	 */
	public String getNomeprodotto() {
		return nomeprodotto;
	}

	/**
	 * 
	 * @param nomeprodotto Nome del prodotto
	 */
	public void setNomeprodotto(String nomeprodotto) {
		this.nomeprodotto = nomeprodotto;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public int getCalorie() {
		return calorie;
	}

	public void setCalorie(int calorie) {
		this.calorie = calorie;
	}

	public int getCalorieDaiGrassi() {
		return calorieDaiGrassi;
	}

	public void setCalorieDaiGrassi(int calorieDaiGrassi) {
		this.calorieDaiGrassi = calorieDaiGrassi;
	}

	public int getGrassi() {
		return grassi;
	}

	public void setGrassi(int grassi) {
		this.grassi = grassi;
	}

	public int getGrassiSaturati() {
		return grassiSaturati;
	}

	public void setGrassiSaturati(int grassiSaturati) {
		this.grassiSaturati = grassiSaturati;
	}

	public int getAcidiGrassiTrans() {
		return acidiGrassiTrans;
	}

	public void setAcidiGrassiTrans(int acidiGrassiTrans) {
		this.acidiGrassiTrans = acidiGrassiTrans;
	}

	public int getColesterolo() {
		return colesterolo;
	}

	public void setColesterolo(int colesterolo) {
		this.colesterolo = colesterolo;
	}

	public int getSodio() {
		return sodio;
	}

	public void setSodio(int sodio) {
		this.sodio = sodio;
	}

	public int getCarboidrati() {
		return carboidrati;
	}

	public void setCarboidrati(int carboidrati) {
		this.carboidrati = carboidrati;
	}

	public int getFibre() {
		return fibre;
	}

	public void setFibre(int fibre) {
		this.fibre = fibre;
	}

	public int getZuccheri() {
		return zuccheri;
	}

	public void setZuccheri(int zuccheri) {
		this.zuccheri = zuccheri;
	}

	public int getProteine() {
		return proteine;
	}

	public void setProteine(int proteine) {
		this.proteine = proteine;
	}

	public int getVitaminaA() {
		return vitaminaA;
	}

	public void setVitaminaA(int vitaminaA) {
		this.vitaminaA = vitaminaA;
	}

	public int getVitaminaC() {
		return vitaminaC;
	}

	public void setVitaminaC(int vitaminaC) {
		this.vitaminaC = vitaminaC;
	}

	public int getCalcio() {
		return calcio;
	}

	public void setCalcio(int calcio) {
		this.calcio = calcio;
	}

	public int getFerro() {
		return ferro;
	}

	public void setFerro(int ferro) {
		this.ferro = ferro;
	}

	public void setValori(int[] valori) {
		int i = 0;
		calorie = valori[i++];
		calorieDaiGrassi = valori[i++];
		grassi = valori[i++];
		grassiSaturati = valori[i++];
		acidiGrassiTrans = valori[i++];
		colesterolo = valori[i++];
		sodio = valori[i++];
		carboidrati = valori[i++];
		fibre = valori[i++];
		zuccheri = valori[i++];
		proteine = valori[i++];
		vitaminaA = valori[i++];
		vitaminaC = valori[i++];
		calcio = valori[i++];
		ferro = valori[i++];
	}

	@Override
	public String toString() {
		return "ProdottoBean [id=" + id + ", nomeprodotto=" + nomeprodotto + ", prezzo=" + prezzo + ", descrizione="
				+ descrizione + ", immagine=" + immagine + ", categoria=" + categoria + ", ingredienti=" + ingredienti
				+ ", line=" + line + ", calorie=" + calorie + ", calorieDaiGrassi=" + calorieDaiGrassi + ", grassi="
				+ grassi + ", grassiSaturati=" + grassiSaturati + ", acidiGrassiTrans=" + acidiGrassiTrans
				+ ", colesterolo=" + colesterolo + ", sodio=" + sodio + ", carboidrati=" + carboidrati + ", fibre="
				+ fibre + ", zuccheri=" + zuccheri + ", proteine=" + proteine + ", vitaminaA=" + vitaminaA
				+ ", vitaminaC=" + vitaminaC + ", calcio=" + calcio + ", ferro=" + ferro + ", size=" + size + "]";
	}
	
	
}
