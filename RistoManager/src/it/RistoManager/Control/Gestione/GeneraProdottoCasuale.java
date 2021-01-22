package it.RistoManager.Control.Gestione;

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

import it.RistoManager.DAO.ProdottoDAO;
import it.RistoManager.Model.ProdottoBean;

/**
 * Servlet implementation class GeneraProdottoCasuale
 */
@WebServlet("/consigliami")
public class GeneraProdottoCasuale extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProdottoDAO pDao=new ProdottoDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GeneraProdottoCasuale() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ProdottoBean p=null;
		//String category=request.getParameter("category");
		
		try {
			List<ProdottoBean> pList=pDao.retrieveAll();
			p=pList.get((int) ((Math.random()*10*pList.size())%pList.size()));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("prodotto", p);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("/prodotto.jsp");
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
