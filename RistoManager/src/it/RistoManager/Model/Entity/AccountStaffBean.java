package it.RistoManager.Model.Enity;

/**
 * 
 * Rappresenta l'account di un membro dello staff
 *
 */
public class AccountStaffBean {
	
	private int id;
	private String email, nome, cognome, password;
	public enum Ruolo {
		sala,
		cucina,
		gestione
	};
	private Ruolo ruolo;
	
	/**
	 * Costruttore con parametri di default
	 */
	public AccountStaffBean() {
		id=-1;
		email="";
		nome="";
		cognome="";
		password="";
	}
	
	/**
	 * Costruttore con parametri
	 * @param email Email dell'account
	 * @param nome Nome del membro dello staff
	 * @param cognome Cognome del membro dello staff
	 * @param password Password dell'account
	 * @param ruolo Ruolo del membro dello staff
	 */
	public AccountStaffBean(String email, String nome, String cognome, String password, Ruolo ruolo) {
		super();
		this.email = email;
		this.nome = nome;
		this.cognome = cognome;
		this.password = password;
		this.ruolo = ruolo;
	}

	/**
	 * 
	 * @return id dell'account
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * @param id L'id dell'account 
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @return email dell'account
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 
	 * @param email L'email dell'account
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 
	 * @return nome del membro dello staff
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * 
	 * @param nome Nome del membro dello staff
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * 
	 * @return Congnome del membro dello staff
	 */
	
	public String getCognome() {
		return cognome;
	}

	
	/**
	 * 
	 * @param cognome Cognome del membro dello staff
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	/**
	 * 
	 * @return Password dell'account
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 
	 * @param password Password dell'account
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 
	 * @return Ruolo del membro dello staff
	 */
	public Ruolo getRuolo() {
		return ruolo;
	}

	/**
	 * 
	 * @param ruolo Ruolo del mebro dello staff
	 */
	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}

	@Override
	public String toString() {
		return "AccountStaffBean [id=" + id + ", email=" + email + ", nome=" + nome + ", cognome=" + cognome
				+ ", password=" + password + ", ruolo=" + ruolo + "]";
	}

}
