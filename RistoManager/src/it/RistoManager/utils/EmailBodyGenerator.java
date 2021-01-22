package it.RistoManager.utils;

import it.RistoManager.Model.ClienteBean;

public class EmailBodyGenerator {

	private ClienteBean c;

	public EmailBodyGenerator(ClienteBean c) {
		this.c=c;
	}


	public String generate() {
		String emailBody="<html>";
		String title="<title>Conferma Registrazione - RistoManager</title>";
		emailBody+=title+"<body>";
		String intestatario="<h1> Gentile "+c.getCognome()+" "+c.getNome()+",</h1>";
		emailBody+=intestatario;
		String link="<a href='http://localhost:8080/RistoManager/ConfermaPrenotazione?confirmId="+c.getId()+"'>";
		String paragraph="<p> Le confermiamo che la sua prenotazione per il giorno <b>"+c.getData().toString()+"</b> ore <b>"+c.getOra()+"</b> è avvenuta con successo."
				+ "</br> La invitiamo a cliccare "+ link +"qui</a> per completare la procedura di prenotazione. </br>"+
				"Saluti, <b><i>RistoManager.</b></i>";
		emailBody+=paragraph;
		emailBody+="</body></html>";	

		return emailBody;
	}

}
