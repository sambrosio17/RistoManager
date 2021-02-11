package it.RistoManager.Model.Enity;

import java.time.LocalDate;
import java.time.LocalTime;

import it.RistoManager.FIA.KMeansExecutor;

/**
 * 
 * Rappresenta un cliente del locale
 *
 */
public class ClienteBean {

	private int id;
	private String nome, cognome, cellulare, numeroDocumento, email, codiceTavolo;
	private LocalDate data;
	private int numeroPosti;
	private LocalTime ora;

	private int[] preferenze;

	/**
	 * Costruttore con parametri di default
	 */
	public ClienteBean() {
		id = -1;
		nome = "";
		cognome = "";
		cellulare = "";
		numeroDocumento = "";
		email = "";
		codiceTavolo = "";
		data = LocalDate.MIN;
		numeroPosti = -1;
		ora = LocalTime.MIN;
		preferenze = new int[KMeansExecutor.NUM_CLUSTERS];
		setPreferenze();
	}

	/**
	 * Costruttore con parametri
	 * 
	 * @param nome            Nome del cliente
	 * @param cognome         Cognome del cliente
	 * @param cellulare       Numero di Cellulare del cliente
	 * @param numeroDocumento Numero del Documento di identificazione del cliente
	 * @param email           Email del cliente
	 * @param codiceTavolo    Codice Tavolo del cliente
	 * @param data            Data di arrivo del cliente
	 * @param numeroPosti     Numero di posti del tavolo del cliente
	 * @param ora             Orario di arrivo del cliente
	 */
	public ClienteBean(String nome, String cognome, String cellulare, String numeroDocumento, String email,
			String codiceTavolo, LocalDate data, int numeroPosti, LocalTime ora) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.cellulare = cellulare;
		this.numeroDocumento = numeroDocumento;
		this.email = email;
		this.codiceTavolo = codiceTavolo;
		this.data = data;
		this.numeroPosti = numeroPosti;
		this.ora = ora;
		preferenze = new int[KMeansExecutor.NUM_CLUSTERS];
		setPreferenze();
	}

	/**
	 * 
	 * @return ID del cliente
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * @param id ID del cliente
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @return Nome del cliente
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * 
	 * @param nome Nome del cliente
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * 
	 * @return Cognome del Cliente
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * 
	 * @param cognome Cognome del cliente
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	/**
	 * 
	 * @return Numero di cellulare del cliente
	 */
	public String getCellulare() {
		return cellulare;
	}

	/**
	 * 
	 * @param cellulare Numero di cellulare del cliente
	 */
	public void setCellulare(String cellulare) {
		this.cellulare = cellulare;
	}

	/**
	 * 
	 * @return Numero del documento di identificazione del cliente
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	/**
	 * 
	 * @param numeroDocumento Numero del documento di identificazione del cliente
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	/**
	 * 
	 * @return Email del cliente
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 
	 * @param email Email del cliente
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 
	 * @return COdice Tavolo del cliente
	 */
	public String getCodiceTavolo() {
		return codiceTavolo;
	}

	/**
	 * 
	 * @param codiceTavolo Codice Tavolo del cliente
	 */
	public void setCodiceTavolo(String codiceTavolo) {
		this.codiceTavolo = codiceTavolo;
	}

	/**
	 * 
	 * @return Data di arrivo del cliente
	 */
	public LocalDate getData() {
		return data;
	}

	/**
	 * 
	 * @param data Data di arrivo del cliente
	 */
	public void setData(LocalDate data) {
		this.data = data;
	}

	/**
	 * 
	 * @return Numero di posti al Tavolo del cliente
	 */
	public int getNumeroPosti() {
		return numeroPosti;
	}

	/**
	 * 
	 * @param numeroPosti Numero di posti al Tavolo del cliente
	 */
	public void setNumeroPosti(int numeroPosti) {
		this.numeroPosti = numeroPosti;
	}

	/**
	 * 
	 * @return Ora di arrivo del cliente
	 */
	public LocalTime getOra() {
		return ora;
	}

	/**
	 * 
	 * @param ora Ora di arrivo del cliente
	 */
	public void setOra(LocalTime ora) {
		this.ora = ora;
	}

	public int[] getPreferenze() {
		return preferenze;
	}
	
	public void setPreferenze() {
		for (int i = 0; i < preferenze.length; i++)
			preferenze[i] = 100;
	}

	public int[] updatePreferenze(int incremento, int cluster) {
		
		preferenze[cluster] += incremento;
		
		incremento *= -1;
		incremento /= (preferenze.length - 1);
		for (int i = 0; i < preferenze.length; i++)
			if(i != cluster)
				preferenze[i] += incremento;
		
		return preferenze;
	}

	@Override
	public String toString() {
		return "ClienteBean [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", cellulare=" + cellulare
				+ ", numeroDocumento=" + numeroDocumento + ", email=" + email + ", codiceTavolo=" + codiceTavolo
				+ ", data=" + data + ", numeroPosti=" + numeroPosti + ", ora=" + ora + "]";
	}

}
