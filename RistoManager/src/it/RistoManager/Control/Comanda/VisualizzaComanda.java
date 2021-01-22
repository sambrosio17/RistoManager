package it.RistoManager.Control.Comanda;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.RistoManager.Model.ClienteBean;
import it.RistoManager.Model.ComandaBean;
import it.RistoManager.Model.ComandaItemBean;
import it.RistoManager.Model.ProdottoBean;

/**
 * Servlet implementation class VisualizzaComanda
 */
@WebServlet("/comanda")
public class VisualizzaComanda extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaComanda() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		ComandaBean comanda;
		ClienteBean cliente;
		RequestDispatcher dispatcher=null;
		synchronized (session) {
			
			comanda=(ComandaBean) session.getAttribute("comanda");
			cliente=(ClienteBean) session.getAttribute("cliente");
			if(cliente==null) {
				request.setAttribute("error", "Cliente non abilitato");
				dispatcher=request.getRequestDispatcher("/404.jsp");
				dispatcher.forward(request, response);
				
			}
			if(comanda==null) {
				comanda=new ComandaBean();
				session.setAttribute("comanda", comanda);
			}
			session.setAttribute("comanda", comanda);
			dispatcher=request.getRequestDispatcher("/comanda.jsp");
			dispatcher.forward(request, response);
			
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
