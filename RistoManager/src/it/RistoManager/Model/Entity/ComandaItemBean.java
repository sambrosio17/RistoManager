package it.RistoManager.Model.Enity;

/**
 * 
 * Rappresenta un prodotto della comanda
 *
 */
public class ComandaItemBean {

	private ProdottoBean prodotto;
	private int quantita;

	/**
	 * Costruttore con parametri di default
	 */
	public ComandaItemBean() {
		quantita = 0;
	}

	/**
	 * Costruttore con parametri
	 * 
	 * @param prodotto Prodotto
	 * @param quantita Quantit� del prodotto
	 */
	public ComandaItemBean(ProdottoBean prodotto, int quantita) {
		super();
		this.prodotto = prodotto;
		this.quantita = quantita;
	}

	/**
	 * 
	 * @return Prodotto
	 */
	public ProdottoBean getProdotto() {
		return prodotto;
	}

	/**
	 * 
	 * @param prodotto Prodotto
	 */
	public void setProdotto(ProdottoBean prodotto) {
		this.prodotto = prodotto;
	}

	/**
	 * 
	 * @return Quantit� del prodotto
	 */
	public int getQuantita() {
		return quantita;
	}

	/**
	 * 
	 * @param quantita Quantit� del prodotto
	 */
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	/**
	 * 
	 * @param qnt Quantit� del prodotto da incrementare
	 */
	public void updateQuantita(int qnt) {
		quantita += qnt;
	}

	/**
	 * Incrementa la quantit� del prodotto
	 */
	public void increaseQuantita() {
		quantita++;
	}

	/**
	 * Decrementa la quantit� del prodotto
	 */
	public void decreaseQuantita() {
		quantita--;
	}

	/**
	 * 
	 * @return Prezzo totale dell' item
	 */
	public float prezzoTotale() {
		return quantita * prodotto.getPrezzo();
	}

	@Override
	public String toString() {
		return "ComandaItemBean [prodotto=" + prodotto + ", quantita=" + quantita + "]";
	}

}
