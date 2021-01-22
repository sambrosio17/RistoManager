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

import com.google.gson.Gson;

import it.RistoManager.DAO.ClienteDAO;
import it.RistoManager.Model.ClienteBean;
import it.RistoManager.utils.CodeGenerator;

/**
 * Servlet implementation class GeneraCodice
 */
@WebServlet("/generatore")
public class GeneraCodice extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClienteDAO cDAO=new ClienteDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GeneraCodice() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CodeGenerator codeGenerator=new CodeGenerator();
		String codiceTavolo=codeGenerator.generate();
		
		//Creo il cliente nel db solo con il campo codice settato
		ClienteBean c=new ClienteBean();
		c.setCodiceTavolo(codiceTavolo);
		c.setData(LocalDate.now());
		c.setOra(LocalTime.now());
		try {
			cDAO.create(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Gson g=new Gson();
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(g.toJson(codiceTavolo));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
