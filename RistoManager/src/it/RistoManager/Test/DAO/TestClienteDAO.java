package it.RistoManager.Test.DAO;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.RistoManager.Model.DAO.AccountStaffDAO;
import it.RistoManager.Model.DAO.ClienteDAO;
import it.RistoManager.Model.Enity.AccountStaffBean;
import it.RistoManager.Model.Enity.ClienteBean;
import it.RistoManager.Model.Enity.AccountStaffBean.Ruolo;

class TestClienteDAO {

	private ClienteDAO dao;
	private ClienteBean clienteAbilitato;
	private ClienteBean clientePrenotato;
	private ClienteBean clienteNonEsistente;
	
	@BeforeEach
	void setUp() throws Exception {
		dao = new ClienteDAO();
		clienteAbilitato = new ClienteBean("luca", "bianchi", "3334617409", "AXgfufg7", "lucabianchi@gmail.com", "ggde6", LocalDate.now(), 3, LocalTime.now());
		clienteAbilitato = dao.create(clienteAbilitato);
		
		
		clientePrenotato = new ClienteBean("mario", "neri", "3334614648", "AXg8gs", "marioneri@gmail.com", null, LocalDate.of(2021, 2, 25), 3, LocalTime.now());
		clientePrenotato = dao.create(clientePrenotato);
		
		clienteNonEsistente = new ClienteBean("ciro", "immobile", "3284614648", "HSC284O", "ciroimm@gmail.com", "620xc", LocalDate.of(2020, 2, 15), 5, LocalTime.now());
	}

	@AfterEach
	void tearDown() throws Exception {
		dao.delete(clienteAbilitato.getId());
		dao.delete(clientePrenotato.getId());
	}

	@Test
	public void testCreate() {
		assertNotEquals(0, clienteAbilitato.getId());
		assertNotEquals(0, clientePrenotato.getId());
	}
	
	@Test
	public void testMissingCreate() {
		assertEquals(0, clienteNonEsistente.getId());
	}
	
	@Test
	public void testRetrieveById() throws SQLException {
		System.out.println(dao.retrieveById(clienteAbilitato.getId()));
		assertNotEquals(null, dao.retrieveById(clienteAbilitato.getId()));
	}
	
	@Test
	public void testNullRetrieveById() throws SQLException {
		assertEquals(null, dao.retrieveById(clienteNonEsistente.getId()));
	}
	
	@Test
	public void testRetrieveByEmail() throws SQLException {
		assertNotEquals(null, dao.retrieveByEmail(clientePrenotato.getEmail()));
	}
	
	@Test
	public void testNullRetrieveByEmail() throws SQLException {
		List<ClienteBean> collection = new ArrayList<ClienteBean>();
		assertEquals(collection, dao.retrieveByEmail(clienteNonEsistente.getEmail()));
	}
	
	@Test
	public void testRetrieveByCodice() throws SQLException {
		assertNotEquals(null, dao.retrieveByCodice(clienteAbilitato.getCodiceTavolo()));
	}
	
	@Test
	public void testRetrieveByCodiceMissing() throws SQLException {
		assertEquals(null, dao.retrieveByCodice(clientePrenotato.getCodiceTavolo()));
	}
	
	@Test
	public void testRetrieveByNome() throws SQLException {
		List<ClienteBean> collection = new ArrayList<ClienteBean>();
		assertNotEquals(collection, dao.retrieveByNome(clienteAbilitato.getNome(), clienteAbilitato.getCognome()));
	}
	
	@Test
	public void testRetrieveByNomeMissing() throws SQLException {
		List<ClienteBean> collection = new ArrayList<ClienteBean>();
		assertEquals(collection, dao.retrieveByNome(clientePrenotato.getNome(), clienteNonEsistente.getCognome()));
	}
	
	@Test
	public void testRetrieveByCellulare() throws SQLException {
		List<ClienteBean> collection = new ArrayList<ClienteBean>();
		assertNotEquals(collection, dao.retrieveByCellulare(clienteAbilitato.getCellulare()));
	}
	
	@Test
	public void testRetrieveByCellulareMissing() throws SQLException {
		List<ClienteBean> collection = new ArrayList<ClienteBean>();
		assertEquals(collection, dao.retrieveByCellulare(clienteNonEsistente.getCellulare()));
	}
	
	@Test
	public void testRetrieveByDate() throws SQLException {
		List<ClienteBean> collection = new ArrayList<ClienteBean>();
		assertNotEquals(collection, dao.retrieveByDate(clienteAbilitato.getData()));
	}
	
	@Test
	public void testRetrieveByDateMissing() throws SQLException {
		List<ClienteBean> collection = new ArrayList<ClienteBean>();
		assertEquals(collection, dao.retrieveByDate(clienteNonEsistente.getData()));
	}
	
	@Test
	public void testRetrieveBetweenDates() throws SQLException {
		List<ClienteBean> collection = new ArrayList<ClienteBean>();
		assertNotEquals(collection, dao.retrieveBetweenDates(clienteAbilitato.getData(), LocalDate.now()));
	}
	
	@Test
	public void testRetrieveAll() throws SQLException {
		List<ClienteBean> collection = new ArrayList<ClienteBean>();
		assertNotEquals(collection, dao.retrieveAll());
	}

	@Test
	public void testUpdate() throws SQLException {
		String nuovoCodice = "gsn3n";
		clientePrenotato.setCodiceTavolo(nuovoCodice);
		dao.update(clientePrenotato.getId(), clientePrenotato);
		assertEquals(nuovoCodice, (dao.retrieveById(clientePrenotato.getId())).getCodiceTavolo());
	}
	
	@Test
	public void testUpdateNonEsistente() throws SQLException {
		clienteNonEsistente.setNome("Francesco");
		assertEquals(null, dao.update(clienteNonEsistente.getId(), clienteNonEsistente));
	}
	
	@Test
	public void testDelete() throws SQLException {
		assertEquals(1, dao.delete(clientePrenotato.getId()));
		assertEquals(null, dao.retrieveById(clientePrenotato.getId()));
	}
	
	@Test
	public void testDeleteNonEsistente() throws SQLException {
		assertEquals(0, dao.delete(clienteNonEsistente.getId()));
	}
	
}
