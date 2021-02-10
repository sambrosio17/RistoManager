package it.RistoManager.Control.Utente.Cliente;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.RistoManager.Control.Utils.CodeGenerator;
import it.RistoManager.Model.DAO.ClienteDAO;
import it.RistoManager.Model.Enity.ClienteBean;

/**
 * Servlet implementation class ConfermaPrenotazione
 */
@WebServlet("/ConfermaPrenotazione")
public class ConfermaPrenotazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ClienteDAO cDao=new ClienteDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfermaPrenotazione() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int confirmId=Integer.parseInt(request.getParameter("confirmId"));
		ClienteBean c=null;
		
		try {
			c=cDao.retrieveById(confirmId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		CodeGenerator generator=new CodeGenerator();
		
		String code=generator.generate();
		
		c.setCodiceTavolo(code);
		
		try {
			c=cDao.update(confirmId, c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("cliente", c);
		
		RequestDispatcher rd=request.getRequestDispatcher("/confermato.jsp");
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
