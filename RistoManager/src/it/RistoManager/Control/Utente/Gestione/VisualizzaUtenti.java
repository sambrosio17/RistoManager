package it.RistoManager.Control.Utente.Gestione;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.RistoManager.Model.DAO.AccountStaffDAO;
import it.RistoManager.Model.Enity.AccountStaffBean;

/**
 * Servlet implementation class VisualizzaUtenti
 */
@WebServlet("/showPersonale")
public class VisualizzaUtenti extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AccountStaffDAO aDao=new AccountStaffDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaUtenti() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<AccountStaffBean> list= null;
		
		try {
			list=aDao.retrieveAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(list);
		request.setAttribute("utenti", list);
		
		request.getRequestDispatcher("/gestione/personale.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
