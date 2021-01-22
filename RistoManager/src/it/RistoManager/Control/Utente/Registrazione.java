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
	ClienteDAO cDao=new ClienteDAO();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Registrazione() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ClienteBean cliente=null;

		String codiceTavolo=(String) request.getSession().getAttribute("codiceTavolo");
		
		try {
			cliente=cDao.retrieveByCodice(codiceTavolo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(cliente);
		if(cliente==null) {
			System.out.println("no codice esistente");
			return;
		}
		
		String nome=request.getParameter("nome");
		String cognome=request.getParameter("cognome");
		String cellulare=request.getParameter("cellulare");
		String documento=request.getParameter("documento");
		String email=request.getParameter("email");
		int numPosti=Integer.parseInt(request.getParameter("numPosti"));
		
		cliente.setCellulare(cellulare);
		cliente.setCognome(cognome);
		cliente.setNome(nome);
		cliente.setEmail(email);
		cliente.setNumeroDocumento(documento);
		cliente.setNumeroPosti(numPosti);
		cliente.setData(LocalDate.now());
		cliente.setOra(LocalTime.now());
		
		try {
			cliente=cDao.update(cliente.getId(), cliente);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.getSession().setAttribute("cliente", cliente);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("/menu");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
