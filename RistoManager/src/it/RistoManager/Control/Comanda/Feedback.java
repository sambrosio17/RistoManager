package it.RistoManager.Control.Comanda;

import java.io.IOException;
import java.security.KeyManagementException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.RistoManager.FIA.KMeansExecutor;
import it.RistoManager.Model.Enity.ClienteBean;
import it.RistoManager.Model.Enity.ProdottoBean;

/**
 * Servlet implementation class Feedback
 */
@WebServlet("/feedback")
public class Feedback extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ClienteBean c = (ClienteBean)request.getSession().getAttribute("cliente");
		int id = Integer.parseInt(request.getParameter("id"));
		String action = request.getParameter("action");
		
		
		if (id < 0 || action == null) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/riepilogo.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		//A partire dall'id, recupera l'indice del prodotto nel dataset
		int line = KMeansExecutor.getLineById(id);
		
		//Recupera il cluster associato al prodotto
		int cluster = KMeansExecutor.getAssignments()[line];
		
		switch(action) {
		case "up":
			c.updatePreferenze(KMeansExecutor.increment, cluster);
			break;
		case "down":
			c.updatePreferenze(KMeansExecutor.increment * -2, cluster);
			break;
		}
		for(int i=0; i< c.getPreferenze().length; i++)
			System.out.print(c.getPreferenze()[i] + " ");
		System.out.println();
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/riepilogo.jsp");
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
