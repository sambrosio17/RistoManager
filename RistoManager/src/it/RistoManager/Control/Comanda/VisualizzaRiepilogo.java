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

import it.RistoManager.Model.DAO.ComandaDAO;
import it.RistoManager.Model.Enity.ClienteBean;
import it.RistoManager.Model.Enity.ComandaBean;
import it.RistoManager.Model.Enity.ComandaItemBean;
import it.RistoManager.Model.Enity.ProdottoBean;
import it.RistoManager.Model.Enity.RiepilogoBean;

/**
 * Servlet implementation class VisualizzaComanda
 */
@WebServlet("/riepilogo")
public class VisualizzaRiepilogo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaRiepilogo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		
		RiepilogoBean riepilogo = new RiepilogoBean();
		ClienteBean cliente;
		ComandaDAO comandaDAO = new ComandaDAO();
		
		RequestDispatcher dispatcher=null;
		synchronized (session) {
			
			cliente=(ClienteBean) session.getAttribute("cliente");
			if(cliente==null) {
				request.setAttribute("error", "Cliente non abilitato");
				dispatcher=request.getRequestDispatcher("/404.jsp");
				dispatcher.forward(request, response);
				
			}
			
			try {
				riepilogo.setComande(comandaDAO.retrieveByCodiceTavolo(cliente.getCodiceTavolo()));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			session.setAttribute("riepilogo", riepilogo);
			dispatcher=request.getRequestDispatcher("/riepilogo.jsp");
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
