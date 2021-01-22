package it.RistoManager.Control.Utente;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xml.internal.security.keys.keyresolver.implementations.RetrievalMethodResolver;

import it.RistoManager.DAO.ClienteDAO;
import it.RistoManager.Model.ClienteBean;
import it.RistoManager.utils.CodeGenerator;

/**
 * Servlet implementation class GeneraCodicePrenotato
 */
@WebServlet("/cercaCliente")
public class VisualizzaCodicePrenotato extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClienteDAO cDAO=new ClienteDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaCodicePrenotato() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String email=request.getParameter("email");
		List<ClienteBean> retrievedClients=null;
//		ClienteBean updatedClient=null;
//		CodeGenerator codeGenerator=new CodeGenerator();
//		String codiceTavolo="";
//		
		try {
			retrievedClients=cDAO.retrieveByEmail(email);
//			if(retrievedClient!=null && retrievedClient.getId()!=-1 ) {
//				codiceTavolo=codeGenerator.generate();
//				retrievedClient.setCodiceTavolo(codiceTavolo);
//				updatedClient=cDAO.update(clienteId, retrievedClient);
//			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		System.out.println(retrievedClients);
		System.out.println(LocalDate.now());
		request.setAttribute("clienti", retrievedClients);
		request.setAttribute("flag", true);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("/sala/cercaCodice.jsp");
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
