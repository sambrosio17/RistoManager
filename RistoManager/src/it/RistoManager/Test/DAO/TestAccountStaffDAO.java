package it.RistoManager.Test.DAO;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.RistoManager.Model.DAO.AccountStaffDAO;
import it.RistoManager.Model.Enity.AccountStaffBean;
import it.RistoManager.Model.Enity.AccountStaffBean.Ruolo;

public class TestAccountStaffDAO {

	private AccountStaffDAO dao;
	private AccountStaffBean accountEsistente;
	private AccountStaffBean accountNonEsistente;
	
	@BeforeEach
	void setUp() throws Exception {
		dao = new AccountStaffDAO();
		accountEsistente = new AccountStaffBean("lucabianchi@libero.it", "luca", "bianchi", "password", Ruolo.sala);
		accountEsistente = dao.create(accountEsistente);
		
		accountNonEsistente = new AccountStaffBean("marioneri@libero.it", "mario", "neri", "password", Ruolo.cucina);
	}

	@AfterEach
	void tearDown() throws Exception {
		dao.delete(accountEsistente.getId());
		dao.delete(accountNonEsistente.getId());
	}


	@Test
	public void testCreate() {
		assertNotEquals(0, accountEsistente.getId());
	}
	
	@Test
	public void testMissingCreate() {
		assertEquals(0, accountNonEsistente.getId());
	}
	
	@Test
	public void testRetrieveById() throws SQLException {
		System.out.println(dao.retrieveById(accountEsistente.getId()));
		assertNotEquals(null, dao.retrieveById(accountEsistente.getId()));
	}
	
	@Test
	public void testNullRetrieveById() throws SQLException {
		assertEquals(null, dao.retrieveById(accountNonEsistente.getId()));
	}
	
	@Test
	public void testRetrieveByEmail() throws SQLException {
		assertNotEquals(null, dao.retrieveByEmail(accountEsistente.getEmail()));
	}
	
	@Test
	public void testNullRetrieveByEmail() throws SQLException {
		assertEquals(null, dao.retrieveByEmail(accountNonEsistente.getEmail()));
	}
	
	@Test
	public void testRetrieveAll() throws SQLException {
		List<AccountStaffBean> collection = new ArrayList<AccountStaffBean>();
		assertNotEquals(collection, dao.retrieveAll());
	}

	@Test
	public void testUpdate() throws SQLException {
		//Prima era sala
		accountEsistente.setRuolo(Ruolo.gestione);
		dao.update(accountEsistente.getId(), accountEsistente);
		assertEquals(Ruolo.gestione, (dao.retrieveById(accountEsistente.getId())).getRuolo());
	}
	
	@Test
	public void testUpdateNonEsistente() throws SQLException {
		//Update di un account che non esiste
		assertEquals(null, dao.update(accountNonEsistente.getId(), accountEsistente));
	}
	
	@Test
	public void testDelete() throws SQLException {
		assertEquals(1, dao.delete(accountEsistente.getId()));
		assertEquals(null, dao.retrieveById(accountEsistente.getId()));
	}
	
	@Test
	public void testDeleteNonEsistente() throws SQLException {
		assertEquals(0, dao.delete(accountNonEsistente.getId()));
	}
}
