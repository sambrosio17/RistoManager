package it.RistoManager.Control.Utente.Cliente;

import java.io.IOException;

import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.mail.smtp.SMTPMessage;

import it.RistoManager.Control.Utils.EmailBodyGenerator;
import it.RistoManager.Model.DAO.ClienteDAO;
import it.RistoManager.Model.Enity.ClienteBean;

/**
 * Servlet implementation class PrenotazioneTavolo
 */
@WebServlet("/prenotazione")
public class PrenotazioneTavolo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ClienteDAO cDao = new ClienteDAO();
	String fromEmail = "ambrosio.s@outlook.it";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PrenotazioneTavolo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		ClienteBean cliente = new ClienteBean();
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String cellulare = request.getParameter("cellulare");
		String documento = request.getParameter("documento");
		String email = request.getParameter("email");
		LocalDate data;
		LocalTime ora;
		int numPosti = -1;

		try {
			numPosti = Integer.parseInt(request.getParameter("numPosti"));
		} catch (NumberFormatException n) {
			request.setAttribute("errorTest",
					"L'inserimento non va a buon fine perchè il campo numero di posti non è un numero");
			session.setAttribute("errorType", "numeroPosti");
			session.setAttribute("error", "Il campo non rispetta il formato");
			response.sendRedirect("./prenotazione.jsp");
			return;
		}

		// Controllo su data e tempo

		if (nome.length() < 1 || nome.length() > 20) {
			request.setAttribute("errorTest",
					"L'inserimento non va a buon fine perchè il campo nome non rispetta la lunghezza");
			session.setAttribute("errorType", "nome");
			session.setAttribute("error", "Il campo non rispetta la lunghezza");
			response.sendRedirect("./prenotazione.jsp");
			return;
		} else if (!nome.matches("^[A-Z a-z]{1,20}$")) {
			request.setAttribute("errorTest",
					"L'inserimento non va a buon fine perchè il campo nome non rispetta il formato");
			session.setAttribute("errorType", "nome");
			session.setAttribute("error", "Il campo non rispetta il formato");
			response.sendRedirect("./prenotazione.jsp");
			return;
		} else if (cognome.length() < 1 || cognome.length() > 20) {
			request.setAttribute("errorTest",
					"L'inserimento non va a buon fine perchè il campo cognome non rispetta la lunghezza");
			session.setAttribute("errorType", "cognome");
			session.setAttribute("error", "Il campo non rispetta la lunghezza");
			response.sendRedirect("./prenotazione.jsp");
			return;
		} else if (!cognome.matches("^[A-Z a-z]{1,20}$")) {
			request.setAttribute("errorTest",
					"L'inserimento non va a buon fine perchè il campo cognome non rispetta il formato");
			session.setAttribute("errorType", "cognome");
			session.setAttribute("error", "Il campo non rispetta il formato");
			response.sendRedirect("./prenotazione.jsp");
			return;
		} else if (cellulare.length() != 10) {
			request.setAttribute("errorTest",
					"L'inserimento non va a buon fine perchè il campo cellulare non rispetta la lunghezza");
			session.setAttribute("errorType", "cellulare");
			session.setAttribute("error", "Il campo non rispetta la lunghezza");
			response.sendRedirect("./prenotazione.jsp");
			return;
		} else if (!cellulare.matches("^\\d{10}$")) {
			request.setAttribute("errorTest",
					"L'inserimento non va a buon fine perchè il campo cellulare non rispetta il formato");
			session.setAttribute("errorType", "cellulare");
			session.setAttribute("error", "Il campo non rispetta il formato");
			response.sendRedirect("./prenotazione.jsp");
			return;
		} else if (documento.length() < 1) {
			request.setAttribute("errorTest",
					"L'inserimento non va a buon fine perchè il campo documento non rispetta la lunghezza");
			session.setAttribute("errorType", "documento");
			session.setAttribute("error", "Il campo non rispetta la lunghezza minima");
			response.sendRedirect("./prenotazione.jsp");
			return;
		} else if (!documento.matches("^[A-Z a-z 0-9]{1,}$")) {
			request.setAttribute("errorTest",
					"L'inserimento non va a buon fine perchè il campo documento non rispetta il formato");
			session.setAttribute("errorType", "documento");
			session.setAttribute("error", "Il campo non rispetta il formato");
			response.sendRedirect("./prenotazione.jsp");
			return;
		} else if (email.length() < 5) {
			request.setAttribute("errorTest",
					"L'inserimento non va a buon fine perchè il campo email non rispetta la lunghezza");
			session.setAttribute("errorType", "email");
			session.setAttribute("error", "Il campo non rispetta la lunghezza minima");
			response.sendRedirect("./prenotazione.jsp");
			return;
		} else if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
			request.setAttribute("errorTest",
					"L'inserimento non va a buon fine perchè il campo email non rispetta il formato");
			session.setAttribute("errorType", "email");
			session.setAttribute("error", "Il campo non rispetta il formato");
			response.sendRedirect("./prenotazione.jsp");
			return;
		} else if (numPosti < 1 || numPosti > 15) {
			request.setAttribute("errorTest",
					"L'inserimento non va a buon fine perchè il numero di posti deve essere compreso tra 1 e 15");
			session.setAttribute("errorType", "numeroPosti");
			session.setAttribute("error", "Il campo non rispetta il formato");
			response.sendRedirect("./prenotazione.jsp");
			return;
		}

		try {
			data = LocalDate.parse(request.getParameter("data"));
		} catch (DateTimeException d) {
			request.setAttribute("errorTest",
					"L'inserimento non va a buon fine perchè il campo data non rispetta il formato");
			session.setAttribute("errorType", "data");
			session.setAttribute("error", "Il campo non rispetta il formato");
			response.sendRedirect("./prenotazione.jsp");
			return;
		}
		if (data.isBefore(LocalDate.now())) {
			request.setAttribute("errorTest",
					"L'inserimento non va a buon fine perchè la data non può essere precedente a quella odierna");
			session.setAttribute("errorType", "data");
			session.setAttribute("error", "Il campo non rispetta il formato");
			response.sendRedirect("./prenotazione.jsp");
			return;
		}
		try {
			ora = LocalTime.parse(request.getParameter("ora"));
		} catch (DateTimeException d) {
			request.setAttribute("errorTest",
					"L'inserimento non va a buon fine perchè il campo ora non rispetta il formato");
			session.setAttribute("errorType", "ora");
			session.setAttribute("error", "Il campo non rispetta il formato");
			response.sendRedirect("./prenotazione.jsp");
			return;
		}
		if (ora.isBefore(LocalTime.of(12, 0)) || ora.isAfter(LocalTime.of(23, 0))) {
			request.setAttribute("errorTest",
					"L'inserimento non va a buon fine perchè l'orario deve essere compreso tra le 12:00 e le 23:00");
			session.setAttribute("errorType", "ora");
			session.setAttribute("error", "Il campo non rispetta il formato");
			response.sendRedirect("./prenotazione.jsp");
			return;
		}

		cliente.setCellulare(cellulare);
		cliente.setCodiceTavolo(null);
		cliente.setCognome(cognome);
		cliente.setNome(nome);
		cliente.setEmail(email);
		cliente.setNumeroDocumento(documento);
		cliente.setNumeroPosti(numPosti);
		cliente.setData(data);
		cliente.setOra(ora);

		try {
			cliente = cDao.create(cliente);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "805");

		Session sessionMail = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("risto.manager.food@gmail.com", "ristomanager");
			}
		});

		try {

			SMTPMessage message = new SMTPMessage(sessionMail);
			message.setFrom(new InternetAddress("no-replay@ristomanager.it"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			message.setSubject("Conferma Prenotazione - RistoManager");
			message.setContent(new EmailBodyGenerator(cliente).generate(), "text/html");
			message.setNotifyOptions(SMTPMessage.NOTIFY_SUCCESS);
			int returnOption = message.getReturnOption();
			System.out.println(returnOption);
			Transport.send(message);
			System.out.println("sent");

			request.setAttribute("flag", true);
			request.setAttribute("errorTest", "La prenotazione è andata a buon fine!");

			RequestDispatcher rd = request.getRequestDispatcher("/thankYou.jsp");
			rd.forward(request, response);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
