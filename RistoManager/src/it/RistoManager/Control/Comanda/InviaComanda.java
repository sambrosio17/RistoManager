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

import it.RistoManager.DAO.ComandaDAO;
import it.RistoManager.DAO.ProdottoDAO;
import it.RistoManager.Model.ClienteBean;
import it.RistoManager.Model.ComandaBean;
import it.RistoManager.Model.ComandaItemBean;
import it.RistoManager.Model.ProdottoBean;

/**
 * Servlet implementation class InviaComanda
 */
@WebServlet("/sendComanda")
public class InviaComanda extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ComandaDAO cDao=new ComandaDAO();
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InviaComanda() {
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
			System.out.println(comanda);
			cliente=(ClienteBean) session.getAttribute("cliente");
			if(cliente==null) {
				request.setAttribute("error", "Cliente non abilitato");
				dispatcher=request.getRequestDispatcher("/error.jsp");
				dispatcher.forward(request, response);
				
			}
			if(comanda==null) {
				comanda=new ComandaBean();
				session.setAttribute("comanda", comanda);
				request.setAttribute("error", "Comanda Vuota");
				dispatcher=request.getRequestDispatcher("/error.jsp");
				dispatcher.forward(request, response);
			}
			ComandaBean savedComanda=null;
			try {
				savedComanda=cDao.create(comanda);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("saved comanda "+savedComanda);
			
			session.setAttribute("comanda", null);
			request.setAttribute("riepilogo", savedComanda);
			request.setAttribute("flag", true);
			dispatcher=request.getRequestDispatcher("/riepilogoComanda.jsp");
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
