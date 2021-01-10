package it.RistoManager.Gestione.Control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.RistoManager.DAO.ProdottoDAO;
import it.RistoManager.Model.ProdottoBean;


@WebServlet("/AggiungiProdotto")
public class AggiungiProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;
    static ProdottoDAO pmodel= new ProdottoDAO();
   
    public AggiungiProdotto() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomeProdotto= request.getParameter("nome");
		String prezzo= request.getParameter("prezzo");
		String descrizione= request.getParameter("descrizione");
		String categoria= request.getParameter("categoria");
		String ingredienti= request.getParameter("ingredienti");
		String foto=request.getParameter("foto");
		
		
		ProdottoBean prodotto = new ProdottoBean();
		prodotto.setNomeprodotto(nomeProdotto);
		prodotto.setCategoria(categoria);
		prodotto.setDescrizione(descrizione);
		prodotto.setPrezzo(Float.parseFloat(prezzo));
		prodotto.setImmagine(foto);
		
		List<String> lista = new ArrayList<String>(Arrays.asList(ingredienti.split(",")));
		
		prodotto.setIngredienti(lista);
		
		try {
			pmodel.create(prodotto);
		}catch(SQLException | NumberFormatException e) {
			System.out.println("Error: "+ e.getMessage());
			request.setAttribute("error", e.getMessage());			
	  }
		/* qui poi si reindirizza alla pagina destinazione
		 *
		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/managerArea/Destinazione.jsp");
		dispatcher.forward(request, response);
	*/
	}

}
