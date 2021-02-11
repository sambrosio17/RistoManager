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
		if (immagine == null || immagine.isEmpty())
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
		String immagine = null;
		switch (categoria.toLowerCase()) {
		case "breakfast":
			immagine = "https://static.cookist.it/wp-content/uploads/sites/21/2019/09/colazione-sana-5-idee.jpg";
			break;
		case "beef & pork":
			immagine = "https://img.texasmonthly.com/2018/02/Pork-Steak-3.jpg?auto=compress&crop=faces&fit=crop&fm=jpg&h=900&ixlib=php-1.2.1&q=45&w=1600";
			break;
		case "chicken & fish":
			immagine = "https://images.squarespace-cdn.com/content/v1/5be6dd00f79392e09d2d579d/1592221451816-S0IDQ66T0KSK8CUJ1NQU/ke17ZwdGBToddI8pDm48kCdHzySs-VlZ_pV2dafNvhkUqsxRUqqbr1mOJYKfIPR7LoDQ9mXPOjoJoqy81S2I8N_N4V1vUb5AoIIIbLZhVYy7Mythp_T-mtop-vrsUOmeInPi9iDjx9w8K4ZfjXt2dvWsIrrPb2nU6I8KjvvFgbkvb-V9U3sDlNvbstcOulBn3WUfc_ZsVm9Mi1E6FasEnQ/McDonalds+Thai+Green+Curry+Burger";
			break;
		case "salads":
			immagine = "https://www.vegolosi.it/wp-content/uploads/2018/06/insalata-dressing-burro-arachidi_1490_650.jpg";
			break;
		case "snacks & sides":
			immagine = "https://storcpdkenticomedia.blob.core.windows.net/media/recipemanagementsystem/media/recipe-media-files/recipes/retail/x17/2018_sweet-sallty-snack-mix_5817_600x600.jpg?ext=.jpg";
			break;
		case "desserts":
			immagine = "https://www.jocooks.com/wp-content/uploads/2012/03/sex-in-a-pan-1-4-1-500x500.jpg";
			break;
		case "beverages":
			immagine = "https://www.gaiatrade.it/image/cache/catalog/products/beverage/Gaia-Trade-Products-Beverages-01-1920x1280.jpg";
			break;
		case "coffee & tea":
			immagine = "https://lh3.googleusercontent.com/proxy/FHfgD0XnJmDp9c5t5Db9menIJ5VF8akT24Gg7EzhujwuBmUOH7POJp_WwIu-qiaKUR2BEbUkY880wa8PlYlIJm5K-UkGW6FTwzAp1ZaHvxbTFG6Ci1JJaQ_53DB6-nxPGjuLgJgM5IvOhjipqb6-HVZe5WhItU0qynZ8UaYAOE6-2qXQZlaSZedCNSQ391A6o8LKZ0wiT_pT4am_FEBSMGHoZt8HQsk";
			break;
		case "smoothies & shakes":
			immagine = "data:image/jpeg;base64,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";
			break;
		default:
			immagine = "https://cdn.vega-direct.com/images/600x600/2c90800c/2c90800c4614d8c7/2c90800c4614d8c701462286/2c90800c4614d8c7014622869d417407/VE3547FS001EMG_01_265866_2019-01-14_14-36-01.JPG";
			break;
		}
		this.immagine = immagine;
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
