package it.RistoManager.Control.Utente.Sala;

import java.io.IOException
;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.RistoManager.Control.Utils.CodeGenerator;
import it.RistoManager.Model.DAO.ClienteDAO;
import it.RistoManager.Model.Enity.ClienteBean;

/**
 * Servlet implementation class GeneraCodicePrenotato
 */
@WebServlet("/cercaCliente")
public class VisualizzaCodicePrenotato extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClienteDAO cDAO=new ClienteDAO();
	
	public static final String LUNGHEZZA_ERR = "L'inserimento non va a buon fine poiché il campo email non rispetta la lunghezza richiesta";
	public static final String FORMATO_ERR = "L'inserimento non va a buon fine poiché il campo email non rispetta il formato";
	public static final String CORRISPONDENZA_ERR = "L'inserimento non va a buon fine poichè non c'è corrispondenza con l'email inserita";
	public static final String OK = "L'inserimento è andato a buon fine e vengono mostrati i risultati";   
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
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		
		String email=request.getParameter("email");
		if (email.length() < 5) {
			System.out.println(LUNGHEZZA_ERR);
			request.setAttribute("errorTest", LUNGHEZZA_ERR);
			session.setAttribute("errorType", "email");
			session.setAttribute("error", "Il campo non rispetta la lunghezza");
			request.setAttribute("flag", true);
			RequestDispatcher dispatcher=request.getRequestDispatcher("/sala/cercaCodice.jsp");
			dispatcher.forward(request, response);
			return;
		} else if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
			System.out.println(FORMATO_ERR);
			request.setAttribute("errorTest", FORMATO_ERR);
			session.setAttribute("errorType", "email");
			session.setAttribute("error", "Il campo non rispetta il formato");
			request.setAttribute("flag", true);
			RequestDispatcher dispatcher=request.getRequestDispatcher("/sala/cercaCodice.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
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
	
//		System.out.println(retrievedClients);
//		System.out.println(LocalDate.now());
		request.setAttribute("clienti", retrievedClients);
		request.setAttribute("flag", true);
		
		if(retrievedClients.isEmpty()) {
			request.setAttribute("errorTest", CORRISPONDENZA_ERR);
			session.setAttribute("errorType", "email");
			session.setAttribute("error", "Non è stata trovata una corrispondenza");
		} else {
			request.setAttribute("errorTest", OK);
		}
		
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
