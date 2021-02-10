package it.RistoManager.Control.Utente.Cliente;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.RistoManager.Model.DAO.ClienteDAO;
import it.RistoManager.Model.Enity.ClienteBean;

/**
 * Servlet implementation class RegistrazioneClient
 */
@WebServlet("/dispatch")
public class DispatchCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ClienteDAO cDao = new ClienteDAO();

	public static final String LUNGHEZZA_ERR = "L'inserimento non va a buon fine poiché il campo codice non rispetta la lunghezza richiesta";
	public static final String FORMATO_ERR = "L'inserimento non va a buon fine poiché il campo codice non rispetta il formato";
	public static final String PRENOTATO = "L'inserimento va a buon fine e l'utente viene rediretto al menu";
	public static final String DA_REGISTRARE = "L'inserimento va a buon fine e l'utente può registrarsi";
	public static final String CORRISPONDENZA_ERR = "L'inserimento non va a buon fine poichè non c'è corrispondenza con il codice del tavolo";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DispatchCliente() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ClienteBean cliente = null;
		HttpSession session = request.getSession();

		String codiceTavolo = request.getParameter("codiceTavolo");

		System.out.println(codiceTavolo);

		if (codiceTavolo.length() != 5) {
			request.setAttribute("errorTest", LUNGHEZZA_ERR);
			session.setAttribute("errorType", "codice");
			session.setAttribute("error", "Il campo non rispetta la lunghezza");
			response.sendRedirect("./accedi.jsp");
			return;
		} else if (!codiceTavolo.matches("^[a-z 0-9]{5}$")) {
			request.setAttribute("errorTest", FORMATO_ERR);
			session.setAttribute("errorType", "codice");
			session.setAttribute("error", "Il campo non rispetta il formato");
			response.sendRedirect("./accedi.jsp");
			return;
		}
		request.getSession().setAttribute("codiceTavolo", codiceTavolo);
		try {
			cliente = cDao.retrieveByCodice(codiceTavolo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		RequestDispatcher dispatcher = null;
		if (cliente != null && !cliente.getNome().isEmpty() && !cliente.getEmail().isEmpty()) {
			// Caso prenotato
			request.getSession().setAttribute("cliente", cliente);
			request.setAttribute("errorTest", PRENOTATO);
			dispatcher = request.getRequestDispatcher("/menu");
			dispatcher.forward(request, response);
			return;
		} else {
			if (cliente == null) {
				// Caso codice sbagliato
				request.setAttribute("exist", false);
				request.setAttribute("errorTest", CORRISPONDENZA_ERR);
				session.setAttribute("errorType", "codice");
				session.setAttribute("error", "Non è stata trovata una corrispondenza");
				response.sendRedirect("./accedi.jsp");
			} else {
				// Caso utente che deve registrarsi
				request.setAttribute("exist", true);
				request.setAttribute("errorTest", DA_REGISTRARE);
				response.sendRedirect("./registrazione.jsp");
			}
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
