package it.RistoManager.Control.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.ServletException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import it.RistoManager.Control.Utente.DispatchCliente;
import it.RistoManager.Control.Utente.DispatchCliente.*;
import it.RistoManager.DAO.ClienteDAO;
import it.RistoManager.Model.ClienteBean;

class TestDispatchCliente {

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private DispatchCliente servlet;
	ClienteDAO dao = new ClienteDAO();
	ClienteBean c;
	
	@BeforeEach
	void setUp() throws Exception {
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		servlet = new DispatchCliente();
		
		c = new ClienteBean();
	}

	@AfterEach
	void tearDown() throws Exception {
		dao.delete(c.getId());
	}

	@Test
	void testLunghezza() throws ServletException, IOException {
		request.setParameter("codiceTavolo", "fr43");
		servlet.doGet(request, response);
		assertEquals(DispatchCliente.LUNGHEZZA_ERR, request.getAttribute("errorTest"));
	}

	@Test
	void testFormato() throws ServletException, IOException {
		request.setParameter("codiceTavolo", "fr43@");
		servlet.doGet(request, response);
		assertEquals(DispatchCliente.FORMATO_ERR, request.getAttribute("errorTest"));
	}
	
	@Test
	void testMancataCorrispondenza() throws ServletException, IOException, SQLException {
		String codice = "xxxxx";
		ClienteBean cliente = dao.retrieveByCodice(codice);
		if(cliente != null)
			dao.delete(cliente.getId());
		
		request.setParameter("codiceTavolo", codice);
		servlet.doGet(request, response);
		
		assertEquals(DispatchCliente.CORRISPONDENZA_ERR, request.getAttribute("errorTest"));
	}
	
	@Test
	void testDaRegistrare() throws ServletException, IOException, SQLException {
		String codice = "12345";
		c.setCodiceTavolo(codice);
		c.setData(LocalDate.now());
		c.setOra(LocalTime.now());
		dao.create(c);
		
		request.setParameter("codiceTavolo", codice);
		servlet.doGet(request, response);
		
		assertEquals(DispatchCliente.DA_REGISTRARE, request.getAttribute("errorTest"));
		assertEquals(codice, request.getSession().getAttribute("codiceTavolo"));
	}
	
	@Test
	void testPrenotato() throws ServletException, IOException, SQLException {
		String codice = "aaaaa";
		
		c.setCodiceTavolo(codice);
		c.setData(LocalDate.now());
		c.setOra(LocalTime.now());
		c.setNome("Gigi");
		c.setCognome("Bianchi");
		c.setEmail("gigibianchi@gmail.com");
		dao.create(c);
		
		request.setParameter("codiceTavolo", codice);
		servlet.doGet(request, response);
		
		assertEquals(DispatchCliente.PRENOTATO, request.getAttribute("errorTest"));
		assertEquals(codice, request.getSession().getAttribute("codiceTavolo"));
	}
}
