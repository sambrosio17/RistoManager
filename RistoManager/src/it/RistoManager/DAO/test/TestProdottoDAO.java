package it.RistoManager.DAO;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import it.RistoManager.Model.ProdottoBean;
import junit.framework.TestCase;
import it.RistoManager.DAO.ProdottoDAO;

public class TestProdottoDAO extends TestCase{
	
	private ProdottoDAO prodottoDAO;
	private ProdottoBean prodottoDaInserire;
	private ProdottoBean prodottoInventato;

	@Before
	public void setUp() throws Exception {

		prodottoDAO= new ProdottoDAO();
		
		List<String> ingredientiTest= new ArrayList<String>(Arrays.asList("uno"));
		prodottoDaInserire=new ProdottoBean("prodottoTest",10,"descrizioneTest","immagineTest.jpg","categoriaTest",ingredientiTest);
	
		prodottoDAO.create(prodottoDaInserire);
		
		prodottoInventato=new ProdottoBean("falso",1,"descrizionefalsa","immaginefalsa","categoriafalsa",ingredientiTest);
	}

	@Test
	public void testCreate() throws SQLException {
		
		assertEquals(prodottoDaInserire,prodottoDAO.create(prodottoDaInserire));
	}
	
	@Test
	public void testRetrieveById() throws SQLException {
	
		assertEquals(prodottoDaInserire.getId(),(prodottoDAO.retrieveById(prodottoDaInserire.getId()).getId()));
	}
	
	@Test
	public void testRetrieveByIdInesistente() throws SQLException{
		
		assertNull(prodottoDAO.retrieveById(prodottoInventato.getId()));
	}
	
	@Test
	public void testRetrieveAll() throws SQLException{
		List<ProdottoBean> prodottiLista=new ArrayList<ProdottoBean>();
		assertNotEquals(prodottiLista,prodottoDAO.retrieveAll());
	}

	@Test
	public void testRetrieveByCategoria() throws SQLException{
		List<ProdottoBean> prodottoCategoria=prodottoDAO.retrieveByCategory(prodottoDaInserire.getCategoria());
	    List<ProdottoBean> prodotti= new ArrayList<ProdottoBean>();
		assertNotEquals(prodottoCategoria,prodotti);
	}
	
	@Test
	public void testRetrieveByCategoriaInesistente() throws SQLException{
		  List<ProdottoBean> prodotti= prodottoDAO.retrieveByCategory(prodottoInventato.getCategoria());
		  assertEquals(prodotti.isEmpty(),true);
	}
	
	@Test
	public void testUpdate() throws SQLException{
		List<String> ingredientiTest2= new ArrayList<String>(Arrays.asList("tre", "quattro", "cinque"));
		ProdottoBean prodottoNuovo=new ProdottoBean("prodottoTest",10,"descrizioneTest","immagineTest.jpg","categoriaTest",ingredientiTest2);

		assertEquals(prodottoNuovo,(prodottoDAO.update(prodottoDaInserire.getId(), prodottoNuovo)));
	}
	

	@Test
	public void testUpdateInesistente() throws SQLException{
		List<String> ingredientiTest2= new ArrayList<String>(Arrays.asList("tre", "quattro", "cinque"));
		ProdottoBean prodottoNuovo=new ProdottoBean("prodottoTest",10,"descrizioneTest","immagineTest.jpg","categoriaTest",ingredientiTest2);

		assertNull((prodottoDAO.update(prodottoInventato.getId(), prodottoNuovo)));
	}
	
	
	@Test
	public void testDelete() throws SQLException{	
		assertEquals(1,prodottoDAO.delete(prodottoDaInserire.getId()));
	}
	
	@Test
	public void testDeleteInesistente() throws SQLException{	
		assertNotEquals(1,prodottoDAO.delete(prodottoInventato.getId()));
		
	}
	
	@After
	public void tearDown() throws Exception {
		prodottoDAO.delete(prodottoDaInserire.getId());
		prodottoDAO.delete(prodottoInventato.getId());
	}


}
