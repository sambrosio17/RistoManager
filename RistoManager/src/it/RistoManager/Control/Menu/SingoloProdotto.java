package it.RistoManager.Control.Menu;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.RistoManager.Model.DAO.ProdottoDAO;
import it.RistoManager.Model.Enity.ProdottoBean;

/**
 * Servlet implementation class SingoloProdotto
 */
@WebServlet("/prodotto")
public class SingoloProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProdottoDAO pDao=new ProdottoDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SingoloProdotto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id=Integer.parseInt(request.getParameter("id"));
		String action=request.getParameter("action");
		ProdottoBean prodotto=null;

		try {
			prodotto=pDao.retrieveById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(prodotto);
		
		if(action!=null && action.equalsIgnoreCase("modifica")) {
			request.setAttribute("prodotto", prodotto);
			System.out.println("PRODOTTO servlet singolo = " + prodotto);
//			response.sendRedirect("./gestione/modifica.jsp");
			request.getRequestDispatcher("/gestione/modifica.jsp").forward(request, response);
			return;
		}
		Gson g=new Gson();

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(g.toJson(prodotto));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
