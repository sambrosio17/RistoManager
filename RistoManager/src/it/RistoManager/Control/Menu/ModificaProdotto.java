package it.RistoManager.Control.Menu;

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
import javax.servlet.http.HttpSession;

import it.RistoManager.Model.DAO.ProdottoDAO;
import it.RistoManager.Model.Enity.ProdottoBean;

/**
 * 
 * Permette di modificare un prodotto del menu
 *
 */

@WebServlet("/modificaProdotto")
public class ModificaProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static ProdottoDAO pmodel = new ProdottoDAO();

	public ModificaProdotto() {
		super();

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute("errorType", null);
		session.setAttribute("error", null);

		int id = Integer.parseInt(request.getParameter("id"));
		String nomeProdotto = request.getParameter("nome");
		String prezzo = request.getParameter("prezzo");
		String descrizione = request.getParameter("descrizione");
		String categoria = request.getParameter("categoria");
		String ingredienti = request.getParameter("ingredienti");
		String foto = request.getParameter("immagine");

		System.out.println("id" + id + "nome" + nomeProdotto + " prezzo" + prezzo + " desc" + descrizione + " cat"
				+ categoria + " ing" + ingredienti + " foto" + foto);

		ProdottoBean prodotto = null;

		try {
			prodotto = pmodel.retrieveById(id);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		request.setAttribute("prodotto", prodotto);

		try {
			Float f = Float.parseFloat(prezzo);
		} catch (NumberFormatException n) {
			request.setAttribute("errorTest", "L’inserimento fallisce perché il campo ‘prezzo’ non rispetta il formato");

			session.setAttribute("errorType", "prezzo");
			session.setAttribute("error", "il campo non rispetta il formato");

			request.getRequestDispatcher("/gestione/modifica.jsp").forward(request, response);
//			response.sendRedirect("./gestione/modifica.jsp");
			return;
		}

		if ((nomeProdotto.length()) == 0) {
			request.setAttribute("errorTest", "L’inserimento fallisce perché il campo ‘nome prodotto’ è vuoto");

			session.setAttribute("errorType", "nome");
			session.setAttribute("error", "il campo non rispetta la lunghezza minima");
			request.getRequestDispatcher("/gestione/modifica.jsp").forward(request, response);
//			response.sendRedirect("./gestione/modifica.jsp");
			return;
		} else if (prezzo.isEmpty()) {
			request.setAttribute("errorTest", "L’inserimento fallisce perché il campo ‘prezzo’ è vuoto");

			session.setAttribute("errorType", "prezzo");
			session.setAttribute("error", "il campo non rispetta la lunghezza minima");

			request.getRequestDispatcher("/gestione/modifica.jsp").forward(request, response);
//			response.sendRedirect("./gestione/modifica.jsp");
			return;
		} else if ((Float.parseFloat(prezzo)) < 0.01) {

			request.setAttribute("errorTest",
					"L’inserimento fallisce perché la dimensione del campo ‘prezzo’ non è adatta");

			session.setAttribute("errorType", "prezzo");
			session.setAttribute("error", "il campo non rispetta la lunghezza minima");

			request.getRequestDispatcher("/gestione/modifica.jsp").forward(request, response);
//			response.sendRedirect("./gestione/modifica.jsp");
			return;
		} else if (descrizione.length() > 250) {

			request.setAttribute("errorTest",
					"L’inserimento fallisce perché la lunghezza del campo ‘descrizione’ è eccessiva");

			session.setAttribute("errorType", "descrizione");
			session.setAttribute("error", "lunghezza eccessiva");
			request.getRequestDispatcher("/gestione/modifica.jsp").forward(request, response);
//			response.sendRedirect("./gestione/modifica.jsp");
			return;
		} else if (ingredienti.isEmpty()) {

			request.setAttribute("errorTest", "L’inserimento fallisce perché il campo ‘ingredienti’ è vuoto");

			session.setAttribute("errorType", "ingredienti");
			session.setAttribute("error", "il campo non rispetta la lunghezza minima");
			request.getRequestDispatcher("/gestione/modifica.jsp").forward(request, response);
//			response.sendRedirect("./gestione/modifica.jsp");
			return;
		} else if (((ingredienti.contains("@") || ingredienti.contains("?") || ingredienti.contains("!")))) {

			request.setAttribute("errorTest",
					"L’inserimento fallisce perché il formato del campo ‘ingredienti’ non è adatto");

			session.setAttribute("errorType", "ingredienti");
			session.setAttribute("error", "il campo non rispetta il formato");

			request.getRequestDispatcher("/gestione/modifica.jsp").forward(request, response);
//			response.sendRedirect("./gestione/modifica.jsp");
			return;
		} else if (categoria.isEmpty()) {

			request.setAttribute("errorTest", "L’inserimento fallisce perché il campo ‘categoria’ è vuoto");

			session.setAttribute("errorType", "categoria");
			session.setAttribute("error", "il campo non rispetta la lunghezza minima");
			request.getRequestDispatcher("/gestione/modifica.jsp").forward(request, response);
//			response.sendRedirect("./gestione/modifica.jsp");
			return;
		} else if (!(categoria.equals("primo") || categoria.equals("pizza") || categoria.equals("secondo")
				|| categoria.equals("antipasto") || categoria.equals("bibite") || categoria.equals("contorno"))) {

			request.setAttribute("errorTest",
					"L’inserimento fallisce perché il formato del campo ‘categoria’ è errato");

			session.setAttribute("errorType", "categoria");
			session.setAttribute("error", "il campo non rispetta il formato");
			request.getRequestDispatcher("/gestione/modifica.jsp").forward(request, response);
//			response.sendRedirect("./gestione/modifica.jsp");	
			return;
		} else if (foto.isEmpty()) {

			request.setAttribute("errorTest", "L’inserimento fallisce perché il campo ‘immagine’ è vuoto");

			session.setAttribute("errorType", "foto");
			session.setAttribute("error", "il campo non rispetta la lunghezza minima");
			request.getRequestDispatcher("/gestione/modifica.jsp").forward(request, response);
//			response.sendRedirect("./gestione/modifica.jsp");	
			return;
		} else {

			// if(nomeProdotto!=null && !nomeProdotto.isEmpty())
			prodotto.setNomeprodotto(nomeProdotto);
			// if(categoria!=null && !categoria.isEmpty())
			prodotto.setCategoria(categoria);
			// if(descrizione!=null && !descrizione.isEmpty())
			prodotto.setDescrizione(descrizione);
			// if(prezzo!=null && !prezzo.isEmpty()) {
			if (prezzo.contains(",")) {
				String[] cifre = prezzo.split(",");
				prezzo = cifre[0] + "." + cifre[1];
			}

			prodotto.setPrezzo(Float.parseFloat(prezzo));
			// }
			prodotto.setImmagine(foto);

			// if(ingredienti!=null && !ingredienti.isEmpty()) {
			List<String> lista = new ArrayList<String>(Arrays.asList(ingredienti.split(",")));

			prodotto.setIngredienti(lista);
			// }

			try {
				pmodel.update(id, prodotto);
				request.setAttribute("errorTest", "L’inserimento va a buon fine");
			} catch (SQLException | NumberFormatException e) {
				System.out.println("Error: " + e.getMessage());
				request.setAttribute("error", e.getMessage());

			}

		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("./gestione/prodotti.jsp");
		dispatcher.forward(request, response);

	}

}