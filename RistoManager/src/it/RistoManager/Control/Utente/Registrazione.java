package it.RistoManager.Control.Utente;

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

import it.RistoManager.DAO.ClienteDAO;
import it.RistoManager.Model.ClienteBean;

/**
 * Servlet implementation class Registrazione
 */
@WebServlet("/registrazione")
public class Registrazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ClienteDAO cDao = new ClienteDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Registrazione() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ClienteBean cliente = null;

		String codiceTavolo = (String) request.getSession().getAttribute("codiceTavolo");

		try {
			cliente = cDao.retrieveByCodice(codiceTavolo);
		} catch (SQLException e) {
			// C
			e.printStackTrace();
		}

		System.out.println(cliente);
		if (cliente == null) {
			System.out.println("no codice esistente");
			return;
		}

		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String cellulare = request.getParameter("cellulare");
		String documento = request.getParameter("documento");
		String email = request.getParameter("email");
		int numPosti = -1;
		
		try {
			numPosti = Integer.parseInt(request.getParameter("numPosti"));
		} catch(NumberFormatException n) {
			request.setAttribute("errorTest",
					"L'inserimento non va a buon fine perchè il campo numero di posti non è un numero");
			response.sendRedirect("./registrazione.jsp");
			return;
		}

		if (nome.length() < 1 || nome.length() > 20) {
			request.setAttribute("errorTest",
					"L'inserimento non va a buon fine perchè il campo nome non rispetta la lunghezza");
			response.sendRedirect("./registrazione.jsp");
			return;
		} else if (!nome.matches("^[A-Z a-z]{1,20}$")) {
			request.setAttribute("errorTest",
					"L'inserimento non va a buon fine perchè il campo nome non rispetta il formato");
			response.sendRedirect("./registrazione.jsp");
			return;
		} else if (cognome.length() < 1 || cognome.length() > 20) {
			request.setAttribute("errorTest",
					"L'inserimento non va a buon fine perchè il campo cognome non rispetta la lunghezza");
			response.sendRedirect("./registrazione.jsp");
			return;
		} else if (!cognome.matches("^[A-Z a-z]{1,20}$")) {
			request.setAttribute("errorTest",
					"L'inserimento non va a buon fine perchè il campo cognome non rispetta il formato");
			response.sendRedirect("./registrazione.jsp");
			return;
		} else if (cellulare.length() != 10) {
			request.setAttribute("errorTest",
					"L'inserimento non va a buon fine perchè il campo cellulare non rispetta la lunghezza");
			response.sendRedirect("./registrazione.jsp");
			return;
		} else if (!cellulare.matches("^\\d{10}$")) {
			request.setAttribute("errorTest",
					"L'inserimento non va a buon fine perchè il campo cellulare non rispetta il formato");
			response.sendRedirect("./registrazione.jsp");
			return;
		} else if (documento.length() < 1) {
			request.setAttribute("errorTest",
					"L'inserimento non va a buon fine perchè il campo documento non rispetta la lunghezza");
			response.sendRedirect("./registrazione.jsp");
			return;
		} else if (!documento.matches("^[A-Z a-z 0-9]{1,}$")) {
			request.setAttribute("errorTest",
					"L'inserimento non va a buon fine perchè il campo documento non rispetta il formato");
			response.sendRedirect("./registrazione.jsp");
			return;
		} else if (email.length() < 5) {
			request.setAttribute("errorTest",
					"L'inserimento non va a buon fine perchè il campo email non rispetta la lunghezza");
			response.sendRedirect("./registrazione.jsp");
			return;
		} else if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
			request.setAttribute("errorTest",
					"L'inserimento non va a buon fine perchè il campo email non rispetta il formato");
			response.sendRedirect("./registrazione.jsp");
			return;
		} else if (numPosti < 1 || numPosti > 15) {
			request.setAttribute("errorTest",
					"L'inserimento non va a buon fine perchè il campo numero di posti deve essere compreso tra 1 e 15");
			response.sendRedirect("./registrazione.jsp");
			return;
		}

		cliente.setCellulare(cellulare);
		cliente.setCognome(cognome);
		cliente.setNome(nome);
		cliente.setEmail(email);
		cliente.setNumeroDocumento(documento);
		cliente.setNumeroPosti(numPosti);
		cliente.setData(LocalDate.now());
		cliente.setOra(LocalTime.now());

		try {
			cliente = cDao.update(cliente.getId(), cliente);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.getSession().setAttribute("cliente", cliente);
		request.setAttribute("errorTest", "La registrazione è andata a buon fine!");

		RequestDispatcher dispatcher = request.getRequestDispatcher("/menu");
		dispatcher.forward(request, response);
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
