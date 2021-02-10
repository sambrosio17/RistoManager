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

import it.RistoManager.FIA.KMeansExecutor;
import it.RistoManager.Model.DAO.ProdottoDAO;
import it.RistoManager.Model.Enity.ClienteBean;
import it.RistoManager.Model.Enity.ProdottoBean;

/**
 * Servlet implementation class VisualizzaProdottiCategoria
 */
@WebServlet("/filter")
public class VisualizzaProdottiCategoria extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProdottoDAO pDao=new ProdottoDAO();
    
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaProdottiCategoria() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ClienteBean c=(ClienteBean) request.getSession().getAttribute("cliente");
		System.out.println(c);
		if(c==null) {
			System.out.println("c null");
			response.sendRedirect("./404.jsp");
		}
		
		
		List<ProdottoBean> pList=null;
		String category=request.getParameter("category");
		System.out.println(category);
		KMeansExecutor k = null;
		try {
			k = new KMeansExecutor();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		pList = k.getProdottiByCategoria(category);
		/*
		try {
			pList=pDao.retrieveByCategory(category);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		request.setAttribute("prodotti", pList);
		request.setAttribute("category", category);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("/menu.jsp");
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
