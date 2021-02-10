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

import it.RistoManager.Model.DAO.ProdottoDAO;
import it.RistoManager.Model.Enity.ClienteBean;
import it.RistoManager.Model.Enity.ComandaBean;
import it.RistoManager.Model.Enity.ComandaItemBean;
import it.RistoManager.Model.Enity.ProdottoBean;

/**
 * Servlet implementation class AggiornaQuantitaComanda
 */
@WebServlet("/updateComanda")
public class AggiornaQuantitaComanda extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProdottoDAO pDao=new ProdottoDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiornaQuantitaComanda() {
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
				dispatcher=request.getRequestDispatcher("/error.jsp");
				dispatcher.forward(request, response);
				
			}
			if(comanda==null) {
				comanda=new ComandaBean();
				session.setAttribute("comanda", comanda);
			}
			int productId=Integer.parseInt(request.getParameter("productId"));
			ProdottoBean prodotto=null;
			try {
				prodotto=pDao.retrieveById(productId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			comanda.rimuoviProdotto(productId);
			ComandaItemBean prodottoItem=new ComandaItemBean();
			int quantity=Integer.parseInt(request.getParameter("quantita"));
			prodottoItem.setProdotto(prodotto);
			prodottoItem.setQuantita(quantity);
			comanda.aggiungiProdotto(prodottoItem);
			comanda.setTotale(comanda.getTotale());
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
