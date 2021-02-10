package it.RistoManager.Control.Cucina;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.RistoManager.Model.DAO.ComandaDAO;
import it.RistoManager.Model.Enity.ComandaBean;

/**
 * Servlet implementation class GestioneCucina
 */
@WebServlet("/cucina")
public class CucinaControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public CucinaControl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ComandaDAO comandaDAO = new ComandaDAO();
		ComandaBean comanda;
		String idComanda;
		
		String action = request.getParameter("action");
		RequestDispatcher dispatcher=null;
		
		if(action==null) {
			dispatcher = this.getServletContext()
					.getRequestDispatcher("/cucina?action=visualizza"); //Da cambiare
			dispatcher.forward(request, response);
		}
		
		try {
			if(action != null)
				switch(action) {
				case "visualizza" : 
					List<ComandaBean> comande = comandaDAO.retrieveNonCompletate();
					request.setAttribute("comande", comande);
					dispatcher = this.getServletContext()
							.getRequestDispatcher("/cucina/index.jsp"); //Da cambiare
					dispatcher.forward(request, response);
					break;
				case "accetta" :
					idComanda = request.getParameter("idComanda");
					System.out.println(idComanda);
					comanda = comandaDAO.retrieveById(Integer.parseInt(idComanda));
					request.setAttribute("comanda", comanda);
					dispatcher = this.getServletContext()
							.getRequestDispatcher("/cucina/dettaglio.jsp"); //Da cambiare
					dispatcher.forward(request, response);
					break;
				case "conferma" :
					idComanda = request.getParameter("idComanda");
					comanda = comandaDAO.retrieveById(Integer.parseInt(idComanda));
					comanda.setCompletata(true);
					comanda=comandaDAO.update(Integer.parseInt(idComanda), comanda);
					dispatcher = this.getServletContext()
							.getRequestDispatcher("/cucina?action=visualizza"); //Da cambiare
					dispatcher.forward(request, response);
					break;
				}
					
		} catch (SQLException | NumberFormatException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
