package it.RistoManager.Control.Utente;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.RistoManager.DAO.ClienteDAO;
import it.RistoManager.Model.ClienteBean;

/**
 * Servlet implementation class RegistrazioneClient
 */
@WebServlet("/dispatch")
public class DispatchCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ClienteDAO cDao=new ClienteDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispatchCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ClienteBean cliente=null;
		
		String codiceTavolo=request.getParameter("codiceTavolo");
		
		System.out.println(codiceTavolo);
		
		request.getSession().setAttribute("codiceTavolo", codiceTavolo);
		try {
			cliente=cDao.retrieveByCodice(codiceTavolo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher=null;
		if(cliente!=null && !cliente.getNome().isEmpty() && !cliente.getEmail().isEmpty()) {
			request.getSession().setAttribute("cliente", cliente);
			dispatcher=request.getRequestDispatcher("/menu");
			dispatcher.forward(request, response);
			return;
		} else {
			if(cliente==null) {
				System.out.println("amore fa pac co cervell");
				request.setAttribute("exist", false);
				
			}else {
				request.setAttribute("exist", true);
				response.sendRedirect("./registrazione.jsp");
			}
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
