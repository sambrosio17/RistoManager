package it.RistoManager.Model;

public class AccountStaffBean {
	
	private int id;
	private String email, nome, cognome, password;
	public enum Ruolo {
		sala,
		cucina,
		gestione
	};
	private Ruolo ruolo;
	
	public AccountStaffBean() {
		id=-1;
		email="";
		nome="";
		cognome="";
		password="";
	}
	
	public AccountStaffBean(String email, String nome, String cognome, String password, Ruolo ruolo) {
		super();
		this.email = email;
		this.nome = nome;
		this.cognome = cognome;
		this.password = password;
		this.ruolo = ruolo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Ruolo getRuolo() {
		return ruolo;
	}

	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
		
		
	}

	@Override
	public String toString() {
		return "AccountStaffBean [id=" + id + ", email=" + email + ", nome=" + nome + ", cognome=" + cognome
				+ ", password=" + password + ", ruolo=" + ruolo + "]";
	}

	
	
	
	 
	
	
	
	

}
