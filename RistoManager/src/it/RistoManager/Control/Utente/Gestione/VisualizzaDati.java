package it.RistoManager.Control.Utente.Gestione;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.RistoManager.Model.DAO.ClienteDAO;
import it.RistoManager.Model.Enity.ClienteBean;

@WebServlet("/showClienti")
public class VisualizzaDati extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static ClienteDAO cmodel = new ClienteDAO();

	public VisualizzaDati() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// utilizzo come nome dei parametri utilizzati 'inizio' e 'fine'
		LocalDate inizio = LocalDate.of(2017, 1, 1);
		LocalDate fine = LocalDate.now();
		String inizioStr = request.getParameter("inizio");
		String fineStr = request.getParameter("fine");

		if (!inizioStr.isEmpty()) {
			try {
				inizio = LocalDate.parse(inizioStr);
			} catch (DateTimeParseException e) {
				System.out.println(e);
			}
		}
		if (!fineStr.isEmpty()) {
			try {
				fine = LocalDate.parse(fineStr);
			} catch (DateTimeParseException e) {
				System.out.println(e);
			}
		}
		
		try {
			Collection<?> clienti = cmodel.retrieveBetweenDates(inizio, fine);

			// setto nella request 'listaClienti' il riultato nella query

			System.out.println("visualizza dati:" + clienti);

			request.removeAttribute("listaClienti");
			request.setAttribute("listaClienti", clienti);

		} catch (SQLException | NumberFormatException e) {
			System.out.println("Error: " + e.getMessage());
			request.setAttribute("error", e.getMessage());
		}

		request.setAttribute("flag", true);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/gestione/clienti.jsp");
		dispatcher.forward(request, response);

	}

}