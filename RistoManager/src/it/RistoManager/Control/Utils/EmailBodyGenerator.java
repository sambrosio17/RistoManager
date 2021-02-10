package it.RistoManager.Control.Utils;

import it.RistoManager.Model.Enity.ClienteBean;

public class EmailBodyGenerator {

	private ClienteBean c;

	/**
	 * Costruttore di default
	 * 
	 * @param c Cliente destinatario della mail
	 */
	public EmailBodyGenerator(ClienteBean c) {
		this.c = c;
	}

	/**
	 * Genera l'e-mail
	 * 
	 * @return E-mail generata
	 */
	public String generate() {
		String emailBody = "<html>";
		String title = "<title>Conferma Registrazione - RistoManager</title>";
		emailBody += title + "<body>";
		String intestatario = "<h1> Gentile " + c.getCognome() + " " + c.getNome() + ",</h1>";
		emailBody += intestatario;
		String link = "<a href='http://localhost/RistoManager/ConfermaPrenotazione?confirmId=" + c.getId() + "'>";
		String paragraph = "<p> Le confermiamo che la sua prenotazione per il giorno <b>" + c.getData().toString()
				+ "</b> ore <b>" + c.getOra() + "</b> è avvenuta con successo." + "</br> La invitiamo a cliccare "
				+ link + "qui</a> per completare la procedura di prenotazione. </br>"
				+ "Saluti, <b><i>RistoManager.</b></i>";
		emailBody += paragraph;
		emailBody += "</body></html>";

		return emailBody;
	}

}
