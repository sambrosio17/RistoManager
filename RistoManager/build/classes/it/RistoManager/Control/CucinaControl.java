package it.RistoManager.Control;

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

import it.RistoManager.DAO.ComandaDAO;
import it.RistoManager.Model.ComandaBean;

/**
 * Servlet implementation class GestioneCucina
 */
@WebServlet("/GestioneCucina")
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
		
		try {
			if(action != null)
				switch(action) {
				case "visualizza" : 
					List<ComandaBean> comande = comandaDAO.retrieveNonCompletate();
					request.setAttribute("comande", comande);
					break;
				case "accetta" :
					idComanda = request.getParameter("idComanda");
					comanda = comandaDAO.retrieveById(Integer.parseInt(idComanda));
					request.setAttribute("comanda", comanda);
					break;
				case "conferma" :
					idComanda = request.getParameter("idComanda");
					comanda = comandaDAO.retrieveById(Integer.parseInt(idComanda));
					comanda.setCompletata(true);
					break;
				}
					
		} catch (SQLException | NumberFormatException e) {
			System.out.println("Error: " + e.getMessage());
			request.setAttribute("error", e.getMessage());
		}
		
		RequestDispatcher dispatcher = this.getServletContext()
				.getRequestDispatcher("/cucina/cucina.jsp"); //Da cambiare
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
