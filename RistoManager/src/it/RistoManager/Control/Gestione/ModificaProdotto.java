package it.RistoManager.Control.Gestione;

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


@WebServlet("/modificaProdotto")
public class ModificaProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;
    static ProdottoDAO pmodel= new ProdottoDAO();
    
    public ModificaProdotto() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id=Integer.parseInt(request.getParameter("id"));
		String nomeProdotto= request.getParameter("nome");
		String prezzo= request.getParameter("prezzo");
		String descrizione= request.getParameter("descrizione");
		String categoria= request.getParameter("categoria");
		String ingredienti= request.getParameter("ingredienti");
		String foto=request.getParameter("foto");
		
		
		ProdottoBean prodotto = null;
		
		try {
			prodotto=pmodel.retrieveById(id);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(nomeProdotto!=null && !nomeProdotto.isEmpty())
			prodotto.setNomeprodotto(nomeProdotto);
		if(categoria!=null && !categoria.isEmpty())
			prodotto.setCategoria(categoria);
		if(descrizione!=null && !descrizione.isEmpty())
			prodotto.setDescrizione(descrizione);
		if(prezzo!=null && !prezzo.isEmpty()) {
			if(prezzo.contains(",")) {
				String[] cifre=prezzo.split(",");
				prezzo=cifre[0]+"."+cifre[1];
			}
			
			prodotto.setPrezzo(Float.parseFloat(prezzo));
		}
		prodotto.setImmagine(foto);
		
		if(ingredienti!=null && !ingredienti.isEmpty()) {
		List<String> lista = new ArrayList<String>(Arrays.asList(ingredienti.split(",")));
		
		prodotto.setIngredienti(lista);
		}
		
		try {
			pmodel.update(id, prodotto);
		}catch(SQLException | NumberFormatException e) {
			System.out.println("Error: "+ e.getMessage());
			request.setAttribute("error", e.getMessage());			
	  }
		
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("/gestione/prodotti.jsp");
		dispatcher.forward(request, response);
	
		
		
		
		
	}

}