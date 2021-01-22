package it.RistoManager.Control.Utente;

import java.io.IOException;
import java.sql.SQLException;
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

import it.RistoManager.DAO.ClienteDAO;
import it.RistoManager.Model.ClienteBean;
import it.RistoManager.utils.EmailBodyGenerator;

/**
 * Servlet implementation class PrenotazioneTavolo
 */
@WebServlet("/prenotazione")
public class PrenotazioneTavolo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ClienteDAO cDao= new ClienteDAO();
	String fromEmail = "ambrosio.s@outlook.it";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PrenotazioneTavolo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ClienteBean cliente= new ClienteBean();
		String nome=request.getParameter("nome");
		String cognome=request.getParameter("cognome");
		String cellulare=request.getParameter("cellulare");
		String documento=request.getParameter("documento");
		String email=request.getParameter("email");
		LocalDate data=LocalDate.parse(request.getParameter("data"));
		LocalTime ora=LocalTime.parse(request.getParameter("ora"));
		int numPosti=Integer.parseInt(request.getParameter("numPosti"));

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
			cliente=cDao.create(cliente);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "805");
		
		

		Session session = Session.getDefaultInstance(props,new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("risto.manager.food@gmail.com","ristomanager");
			}
		});

		try {

			SMTPMessage message = new SMTPMessage(session);
			message.setFrom(new InternetAddress("no-replay@ristomanager.it"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse( email));
			message.setSubject("Conferma Prenotazione - RistoManager");
			message.setContent(new EmailBodyGenerator(cliente).generate(), "text/html");
			message.setNotifyOptions(SMTPMessage.NOTIFY_SUCCESS);
			int returnOption = message.getReturnOption();
			System.out.println(returnOption);        
			Transport.send(message);
			System.out.println("sent");
			
			request.setAttribute("flag", true);
			
			RequestDispatcher rd=request.getRequestDispatcher("/thankYou.jsp");
			rd.forward(request, response);

		}
		catch (MessagingException e) {
			throw new RuntimeException(e);         
		}



	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
