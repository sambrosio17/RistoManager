package it.RistoManager.Gestione.Control;

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

import it.RistoManager.Model.ProdottoBean;
import it.RistoManager.DAO.ProdottoDAO;


@WebServlet("/RimuoviProdotto")
public class RimuoviProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static ProdottoDAO pmodel= new ProdottoDAO();
    public RimuoviProdotto() {
        super();
       
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			   //nell momento in cui si seleziona la rimozione del prodotto si setta nell url
			   //un parametro id che conterra l'id del prodotto in questione
			   int id= Integer.parseInt(request.getParameter("id"));
			   
			   
			   if(id<0) {
				   RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/managerArea/AdminHome.jsp");
				   dispatcher.forward(request, response);
			   }
			   
			   try {
				   Collection<?> prodotti = pmodel.retrieveAll();
				   if(prodotti != null && prodotti.size() > 0) {
						Iterator<?> it  = prodotti.iterator();
						while(it.hasNext()) {
							ProdottoBean bean = (ProdottoBean)it.next();	
							if(bean.getId() == id) {
								 pmodel.delete(id);
							}
						}
					}		
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

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
