package it.RistoManager.Model;

public class ComandaItemBean {

	private ProdottoBean prodotto;
	private int quantita;

	public ComandaItemBean() {
		quantita=0;
	}

	public ComandaItemBean(ProdottoBean prodotto, int quantita) {
		super();
		this.prodotto = prodotto;
		this.quantita = quantita;
	}

	public ProdottoBean getProdotto() {
		return prodotto;
	}

	public void setProdotto(ProdottoBean prodotto) {
		this.prodotto = prodotto;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public void updateQuantita(int qnt)
	{
		quantita+=qnt;
	}

	public void increaseQuantita()
	{
		quantita++;
	}

	public void decreaseQuantita()
	{
		quantita--;
	}

	public float prezzoTotale()
	{
		return quantita*prodotto.getPrezzo();
	}

	@Override
	public String toString() {
		return "ComandaItemBean [prodotto=" + prodotto + ", quantita=" + quantita + "]";
	}






}
