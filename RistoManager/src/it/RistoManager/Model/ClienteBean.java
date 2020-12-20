package it.RistoManager.Model;

import java.time.LocalDate;
import java.time.LocalTime;

public class ClienteBean {
	
	
	private int id;
	private String nome, cognome, cellulare, numeroDocumento, email, codiceTavolo;
	private LocalDate data;
	private int numeroPosti;
	private LocalTime ora;
	
	public ClienteBean() {
		id=-1;
		nome="";
		cognome="";
		cellulare="";
		numeroDocumento="";
		email="";
		codiceTavolo="";
		data=LocalDate.MIN;
		numeroPosti=-1;
		ora=LocalTime.MIN;
		
	}
	
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
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getCellulare() {
		return cellulare;
	}
	public void setCellulare(String cellulare) {
		this.cellulare = cellulare;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCodiceTavolo() {
		return codiceTavolo;
	}
	public void setCodiceTavolo(String codiceTavolo) {
		this.codiceTavolo = codiceTavolo;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public int getNumeroPosti() {
		return numeroPosti;
	}
	public void setNumeroPosti(int numeroPosti) {
		this.numeroPosti = numeroPosti;
	}
	public LocalTime getOra() {
		return ora;
	}
	public void setOra(LocalTime ora) {
		this.ora = ora;
	}

	@Override
	public String toString() {
		return "ClienteBean [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", cellulare=" + cellulare
				+ ", numeroDocumento=" + numeroDocumento + ", email=" + email + ", codiceTavolo=" + codiceTavolo
				+ ", data=" + data + ", numeroPosti=" + numeroPosti + ", ora=" + ora + "]";
	}
	
	

}
