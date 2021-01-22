package it.RistoManager.Control.Utente;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.RistoManager.DAO.AccountStaffDAO;
import it.RistoManager.Model.AccountStaffBean;;


@WebServlet("/rimuovi")
public class EliminaUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
    static AccountStaffDAO staffmodel= new AccountStaffDAO();
    
    public EliminaUtente() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		  //nell momento in cui si seleziona la rimozione del prodotto si setta nell url
		   //un parametro id che conterra l'id del prodotto in questione
		   int id= Integer.parseInt(request.getParameter("id"));
		   
		   
		   if(id<0) {
			   RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/gestione/index.jsp");
			   dispatcher.forward(request, response);
		   }
		   
		   try {
			   Collection<?> staff = staffmodel.retrieveAll();
			   if(staff != null && staff.size() > 0) {
					Iterator<?> it  = staff.iterator();
					while(it.hasNext()) {
						AccountStaffBean bean = (AccountStaffBean)it.next();	
						if(bean.getId() == id) {
							 staffmodel.delete(id);
						}
					}
				}		
	      }catch(SQLException | NumberFormatException e) {
				System.out.println("Error: "+ e.getMessage());
				request.setAttribute("error", e.getMessage());			
		  }
		   
	
	RequestDispatcher dispatcher=request.getRequestDispatcher("/gestione/personale.jsp");
	dispatcher.forward(request, response);
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}