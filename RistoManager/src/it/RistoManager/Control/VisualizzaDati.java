package it.RistoManager.Gestione.Control;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import it.RistoManager.Model.ClienteBean;
import it.RistoManager.DAO.ClienteDAO;

@WebServlet("/VisualizzaDati")
public class VisualizzaDati extends HttpServlet {
	private static final long serialVersionUID = 1L;
    static ClienteDAO cmodel= new ClienteDAO();  
   
    public VisualizzaDati() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//utilizzo come nome dei parametri utilizzati 'inizio' e 'fine'
		
		LocalDate inizio = LocalDate.parse(request.getParameter("inizio"));
		
		LocalDate fine = LocalDate.parse(request.getParameter("fine"));

		
		 try {
			 Collection<?> clienti = cmodel.retrieveBetweenDates(inizio, fine); 
			 
			 //setto nella request 'listaClienti' il riultato nella query
			 
			 request.removeAttribute("listaClienti");
			 request.setAttribute("listaClienti", clienti);
 
	      }catch(SQLException | NumberFormatException e) {
				System.out.println("Error: "+ e.getMessage());
				request.setAttribute("error", e.getMessage());			
		  }
		
		/*qui poi si reindirizza alla pagina destinazione
		 * 
		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/managerArea/Destinazione.jsp");
		dispatcher.forward(request, response);
		*/
		
	}

}
