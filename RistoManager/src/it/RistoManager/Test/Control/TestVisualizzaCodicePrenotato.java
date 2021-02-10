package it.RistoManager.Test.Control;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import it.RistoManager.Control.Utente.Sala.VisualizzaCodicePrenotato;
import it.RistoManager.Model.DAO.ClienteDAO;
import it.RistoManager.Model.Enity.ClienteBean;

class TestVisualizzaCodicePrenotato {

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private VisualizzaCodicePrenotato servlet;
	ClienteDAO dao = new ClienteDAO();
	ClienteBean c;

	@BeforeEach
	void setUp() throws Exception {
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		servlet = new VisualizzaCodicePrenotato();

		c = new ClienteBean();
	}

	@AfterEach
	void tearDown() throws Exception {
		dao.delete(c.getId());
	}

	@Test
	void testLunghezza() throws ServletException, IOException {
		request.setParameter("email", "a@b");
		servlet.doGet(request, response);
		assertEquals(VisualizzaCodicePrenotato.LUNGHEZZA_ERR, request.getAttribute("errorTest"));
	}

	@Test
	void testFormato() throws ServletException, IOException {
		request.setParameter("email", "aaabbbb");
		servlet.doGet(request, response);
		assertEquals(VisualizzaCodicePrenotato.FORMATO_ERR, request.getAttribute("errorTest"));
	}

	@Test
	void testMancataCorrispondenza() throws ServletException, IOException, SQLException {
		String email = "gigi@libero.it";
		List<ClienteBean> clienti = dao.retrieveByEmail(email);
		for (ClienteBean cliente : clienti)
			if (cliente != null)
				dao.delete(cliente.getId());

		request.setParameter("email", email);
		servlet.doGet(request, response);

		assertEquals(VisualizzaCodicePrenotato.CORRISPONDENZA_ERR, request.getAttribute("errorTest"));
	}

	@Test
	void testOK() throws ServletException, IOException, SQLException {
		String email = "gigibianchi@gmail.com";
		
		c.setCodiceTavolo("aaaaa");
		c.setData(LocalDate.now());
		c.setOra(LocalTime.now());
		c.setNome("Gigi");
		c.setCognome("Bianchi");
		c.setEmail(email);
		dao.create(c);

		request.setParameter("email", email);
		servlet.doGet(request, response);

		assertEquals(VisualizzaCodicePrenotato.OK, request.getAttribute("errorTest"));
	}

}
