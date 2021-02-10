package it.RistoManager.FIA;

import java.util.List;

import it.RistoManager.Model.DAO.ProdottoDAO;
import it.RistoManager.Model.Enity.ProdottoBean;

//Questa Classe � stata usata per eseguire test e prove sulle operazioni implementate
public class Main {

	public static void main(String[] args) throws Exception {
//		Long start = System.currentTimeMillis();
		KMeansExecutor k = new KMeansExecutor();
		int[] assignments = k.createClusters();
		
		for(int i = 0; i < assignments.length; i++) {
			System.out.println(k.getProdottoByLine(i).getCategoria() + " " + assignments[i] );
		}
		
		/*		
		ProdottoDAO dao = new ProdottoDAO();
		List<ProdottoBean> list = dao.retrieveAll();
				
		for(ProdottoBean p : list)
			System.out.println(p);

		List<ProdottoBean> list = k.getAllProdotti();
		for(ProdottoBean p : list)
			System.out.println(p);
		System.out.println(System.currentTimeMillis() - start);
//		System.out.println(2);
//		System.out.println(k.getProdottoById(319));
*/
	}

}
