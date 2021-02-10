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
import it.RistoManager.Model.*;
import it.RistoManager.Model.DAO.ProdottoDAO;
import it.RistoManager.Model.Enity.ClienteBean;
import it.RistoManager.Model.Enity.ProdottoBean;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.ArrayList;
import it.RistoManager.Model.DAO.ProdottoDAO;
import it.RistoManager.Model.Enity.ProdottoBean;
import weka.clusterers.SimpleKMeans;
import weka.core.Instance;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;
/**
 * Servlet implementation class VisualizzaMenu
 */
@WebServlet("/menu")
public class VisualizzaMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProdottoDAO pDao=new ProdottoDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaMenu() {
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
			RequestDispatcher rd=request.getRequestDispatcher("/accedi.jsp");
			rd.forward(request, response);
		}
		
		List<ProdottoBean> prodotti=null;
		KMeansExecutor kmeans = null;
		try {
			kmeans = new KMeansExecutor();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			kmeans.createClusters();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
/*		
		try {
			prodotti=pDao.retrieveAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/
		prodotti = kmeans.getAllProdotti();
		request.setAttribute("prodotti",prodotti);
		RequestDispatcher rd=request.getRequestDispatcher("/menu.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
