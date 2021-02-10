package it.RistoManager.Control.Menu;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.RistoManager.Model.DAO.ProdottoDAO;
import it.RistoManager.Model.Enity.ProdottoBean;

/**
 * Servlet implementation class VisualizzaProdottiPrezzo
 */
@WebServlet("/VisualizzaProdottiPrezzo")
public class VisualizzaProdottiPrezzo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProdottoDAO pDao=new ProdottoDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaProdottiPrezzo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<ProdottoBean> pList=null;
		int min=Integer.parseInt(request.getParameter("prezzoMin"));
		int max=Integer.parseInt(request.getParameter("prezzoMax"));
		
		try {
			pList=pDao.retrieveByPrice(min, max);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("prodotti", pList);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("/prodotti.jsp");
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
