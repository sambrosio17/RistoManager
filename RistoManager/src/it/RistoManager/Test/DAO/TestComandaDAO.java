package it.RistoManager.Test.DAO;

import static org.junit.Assert.*;


import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.RistoManager.Model.*;
import it.RistoManager.Model.DAO.*;
import it.RistoManager.Model.Enity.ClienteBean;
import it.RistoManager.Model.Enity.ComandaBean;
import it.RistoManager.Model.Enity.ComandaItemBean;
import it.RistoManager.Model.Enity.ProdottoBean;
import junit.framework.TestCase;

public class TestComandaDAO extends TestCase{
	private ComandaDAO comandaDAO;
	private ProdottoDAO prodottoDAO;
	private ClienteDAO clienteDAO;
	
	private ClienteBean c;
    private ProdottoBean pb;
	private ComandaBean cb;
	private ComandaBean comandaInesistente;
	
	@Before
	public void setUp() throws Exception {
		comandaDAO= new ComandaDAO();
		clienteDAO= new ClienteDAO();
		prodottoDAO= new ProdottoDAO();
		
		LocalDate date= LocalDate.of(2021, Month.JANUARY, 25);
		LocalTime time= LocalTime.now();
		
		ClienteBean cliente=new ClienteBean("nomeTest","cognTest","cellTest","docuTest","mailTest","codTest",date,1,time);
		c=clienteDAO.create(cliente);
		
		List<String> ingredientiTest= new ArrayList<String>(Arrays.asList("uno"));
		ProdottoBean p = new ProdottoBean("prodottoTest",10f,"descrizioneTest","immagineTest.jpg","categoriaTest",ingredientiTest);
		pb=prodottoDAO.create(p);
		
		ComandaItemBean cib=new ComandaItemBean(pb,1);
		List<ComandaItemBean> prodotti=new ArrayList<ComandaItemBean>();
		prodotti.add(cib);
		
		ComandaBean comandaBean=new ComandaBean(10f,prodotti,c,false);
		cb=comandaDAO.create(comandaBean);
		
		comandaInesistente=new ComandaBean();
		
	}


	@Test
	public void testCreate() throws SQLException {
		assertNotEquals(0,cb.getId());
	}

	@Test
	public void testRetrieveById() throws SQLException {
		ComandaBean comanda=comandaDAO.retrieveById(cb.getId());
		assertEquals(comanda.getId(),cb.getId());
	}
	
	@Test
	public void testRetrieveByIdInesistente() throws SQLException {
		ComandaBean comanda=comandaDAO.retrieveById(comandaInesistente.getId());
		assertNull(comanda);
	}

	@Test
	public void testRetrieveAll() throws SQLException{
		List<ComandaBean> comandeLista=new ArrayList<ComandaBean>();
		assertNotEquals(comandeLista,comandaDAO.retrieveAll());
	}

	
	@Test
	public void testRetrieveNonCompletate() throws SQLException{
		//la comanda creata nel setup e non completata di default
		assertNotEquals(0,(comandaDAO.retrieveNonCompletate()).size());
	}
	
	@Test
	public void testRetrieveByCodice() throws SQLException {
		List<ComandaBean> comanda=comandaDAO.retrieveByCodiceTavolo(cb.getCliente().getCodiceTavolo());
		assertNotEquals(comanda.size(),0);
	}
	
	@Test
	public void testRetrieveByCodiceInesistente() throws SQLException {
		List<ComandaBean> comanda=comandaDAO.retrieveByCodiceTavolo("nonesisto");
		assertEquals(comanda.size(),0);
	}
	
	@Test
	public void testUpdate() throws SQLException{
		ComandaBean cb2= new ComandaBean();
		
		cb2.setCliente(c);
		ComandaItemBean cib=new ComandaItemBean(pb,1);
		List<ComandaItemBean> prodotti=new ArrayList<ComandaItemBean>();
		prodotti.add(cib);
		
		cb2.setProdotti(prodotti);
		cb2.setCompletata(true);
		
		assertEquals(comandaDAO.update(cb.getId(), cb2),cb2);
	}
	
	@Test
	public void testUpdateInesistente() throws SQLException{
		ComandaBean cb2= new ComandaBean();
		
		cb2.setCliente(c);
		ComandaItemBean cib=new ComandaItemBean(pb,1);
		List<ComandaItemBean> prodotti=new ArrayList<ComandaItemBean>();
		prodotti.add(cib);
		
		cb2.setProdotti(prodotti);
		cb2.setCompletata(true);
		
		assertNull(comandaDAO.update(comandaInesistente.getId(), cb2));
	}
	
	@Test
	public void testDelete() throws SQLException {
		assertEquals(1,comandaDAO.delete(cb.getId()));
	}
	
	@Test
	public void testDeleteInesistente() throws SQLException {
		assertEquals(0,comandaDAO.delete(comandaInesistente.getId()));
	}
	
	@After
	public void tearDown() throws Exception {
		comandaDAO.delete(cb.getId());
		clienteDAO.delete(c.getId());
	}

}
