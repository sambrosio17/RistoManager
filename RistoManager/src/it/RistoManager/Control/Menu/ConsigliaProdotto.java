package it.RistoManager.Control.Menu;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.RistoManager.FIA.KMeansExecutor;
import it.RistoManager.Model.DAO.ProdottoDAO;
import it.RistoManager.Model.Enity.ClienteBean;
import it.RistoManager.Model.Enity.ProdottoBean;

/**
 * Servlet implementation class GeneraProdottoCasuale
 */
@WebServlet("/consigliami")
public class ConsigliaProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProdottoDAO pDao = new ProdottoDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConsigliaProdotto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ProdottoBean p = null;
		ArrayList<ProdottoBean> consigli = new ArrayList<ProdottoBean>();
		// String category=request.getParameter("category");

		ClienteBean c = (ClienteBean) request.getSession().getAttribute("cliente");
		int[] preferenze = c.getPreferenze();

		//Scorre i Cluster
		for (int i = 0; i < KMeansExecutor.NUM_CLUSTERS; i++) {
			//Estrae l'indice di preferenza del cliente per il cluster corrente
			int percentage = preferenze[i];
			//Se la percantuale è >100 possono essere consigliati più prodotti di quel cluster
			while (percentage > 0) {
				double random = Math.random() * 100;
				
				if (percentage > random) {
					// Restituisce il cluster i
					ArrayList<ProdottoBean> cluster = KMeansExecutor.getCluster(i);
					//Sceglie un indice casuale dal cluster
					int randomProdotto = (int)(Math.random() * cluster.size());
					//Estrae il prodotto scelto dal cluster
					p = cluster.get(randomProdotto);
					
					//Lo aggiunge alla lista dei prodotti consigliati
					consigli.add(p);
					
				}
				percentage -= 100;
			}
		}

		
		/*
		try {
			List<ProdottoBean> pList = pDao.retrieveAll();
			p = pList.get((int) ((Math.random() * 10 * pList.size()) % pList.size()));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/
		request.setAttribute("consigli", consigli);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/prodotto.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
