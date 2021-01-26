package it.RistoManager.Control.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.ServletException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import it.RistoManager.Control.Utente.Registrazione;
import it.RistoManager.Control.Utente.VisualizzaCodicePrenotato;
import it.RistoManager.DAO.ClienteDAO;
import it.RistoManager.Model.ClienteBean;

class TestRegistrazione {

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private Registrazione servlet;
	ClienteDAO dao = new ClienteDAO();
	ClienteBean c = new ClienteBean();

	@BeforeEach
	void setUp() throws Exception {
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		servlet = new Registrazione();
		
		c.setCodiceTavolo("xxxxx");
		c.setData(LocalDate.now());
		c.setOra(LocalTime.now());
		dao.create(c);
		request.getSession().setAttribute("codiceTavolo", c.getCodiceTavolo());
	}

	@AfterEach
	void tearDown() throws Exception {
		dao.delete(c.getId());
	}

	@Test
	void testFormatoNumeroPosti() throws ServletException, IOException {
		request.setParameter("numPosti", "3R");
		servlet.doGet(request, response);
		assertEquals("L'inserimento non va a buon fine perchè il campo numero di posti non è un numero",
				request.getAttribute("errorTest"));
	}

	@Test
	void testLunghezzaNome() throws ServletException, IOException {
		request.setParameter("numPosti", "17");
		request.setParameter("nome", "Rosalba Mariantonietta");
		servlet.doGet(request, response);
		assertEquals("L'inserimento non va a buon fine perchè il campo nome non rispetta la lunghezza",
				request.getAttribute("errorTest"));
	}

	@Test
	void testFormatoNome() throws ServletException, IOException {
		request.setParameter("numPosti", "17");
		request.setParameter("nome", "Rosalba00");
		servlet.doGet(request, response);
		assertEquals("L'inserimento non va a buon fine perchè il campo nome non rispetta il formato",
				request.getAttribute("errorTest"));
	}

	@Test
	void testLunghezzaCognome() throws ServletException, IOException {
		request.setParameter("numPosti", "17");
		request.setParameter("nome", "Rosalba");
		request.setParameter("cognome", "Romano Castrogiovanni");
		
		servlet.doGet(request, response);
		assertEquals("L'inserimento non va a buon fine perchè il campo cognome non rispetta la lunghezza",
				request.getAttribute("errorTest"));
	}

	@Test
	void testFormatoCognome() throws ServletException, IOException {
		request.setParameter("numPosti", "17");
		request.setParameter("nome", "Rosalba");
		request.setParameter("cognome", "Romano2#");
		
		servlet.doGet(request, response);
		assertEquals("L'inserimento non va a buon fine perchè il campo cognome non rispetta il formato",
				request.getAttribute("errorTest"));
	}

	@Test
	void testLunghezzaCellulare() throws ServletException, IOException {
		request.setParameter("numPosti", "17");
		request.setParameter("nome", "Rosalba");
		request.setParameter("cognome", "Romano");
		request.setParameter("cellulare", "31385");
		
		servlet.doGet(request, response);
		assertEquals("L'inserimento non va a buon fine perchè il campo cellulare non rispetta la lunghezza",
				request.getAttribute("errorTest"));
	}
	
	@Test
	void testFormatoCellulare() throws ServletException, IOException {
		request.setParameter("numPosti", "17");
		request.setParameter("nome", "Rosalba");
		request.setParameter("cognome", "Romano");
		request.setParameter("cellulare", "31385460L&");
		
		servlet.doGet(request, response);
		assertEquals("L'inserimento non va a buon fine perchè il campo cellulare non rispetta il formato",
				request.getAttribute("errorTest"));
	}
	
	@Test
	void testLunghezzaDocumento() throws ServletException, IOException {
		request.setParameter("numPosti", "17");
		request.setParameter("nome", "Rosalba");
		request.setParameter("cognome", "Romano");
		request.setParameter("cellulare", "3138546099");
		request.setParameter("documento", "");
		
		servlet.doGet(request, response);
		assertEquals("L'inserimento non va a buon fine perchè il campo documento non rispetta la lunghezza",
				request.getAttribute("errorTest"));
	}
	
	@Test
	void testFormatoDocumento() throws ServletException, IOException {
		request.setParameter("numPosti", "17");
		request.setParameter("nome", "Rosalba");
		request.setParameter("cognome", "Romano");
		request.setParameter("cellulare", "3138546099");
		request.setParameter("documento", "CX567@#");
		
		servlet.doGet(request, response);
		assertEquals("L'inserimento non va a buon fine perchè il campo documento non rispetta il formato",
				request.getAttribute("errorTest"));
	}
	
	@Test
	void testLunghezzaEmail() throws ServletException, IOException {
		request.setParameter("numPosti", "17");
		request.setParameter("nome", "Rosalba");
		request.setParameter("cognome", "Romano");
		request.setParameter("cellulare", "3138546099");
		request.setParameter("documento", "CX567G9");
		request.setParameter("email", "rr.i");
		
		servlet.doGet(request, response);
		assertEquals("L'inserimento non va a buon fine perchè il campo email non rispetta la lunghezza",
				request.getAttribute("errorTest"));
	}
	
	@Test
	void testFormatoEmail() throws ServletException, IOException {
		request.setParameter("numPosti", "17");
		request.setParameter("nome", "Rosalba");
		request.setParameter("cognome", "Romano");
		request.setParameter("cellulare", "3138546099");
		request.setParameter("documento", "CX567G9");
		request.setParameter("email", "rromano.it");
		
		servlet.doGet(request, response);
		assertEquals("L'inserimento non va a buon fine perchè il campo email non rispetta il formato",
				request.getAttribute("errorTest"));
	}
	
	@Test
	void testValoreNumeroPosti() throws ServletException, IOException {
		request.setParameter("numPosti", "17");
		request.setParameter("nome", "Rosalba");
		request.setParameter("cognome", "Romano");
		request.setParameter("cellulare", "3138546099");
		request.setParameter("documento", "CX567G9");
		request.setParameter("email", "rromano@gmail.it");
		
		servlet.doGet(request, response);
		assertEquals("L'inserimento non va a buon fine perchè il campo numero di posti deve essere compreso tra 1 e 15",
				request.getAttribute("errorTest"));
	}
	
	@Test
	void testOK() throws ServletException, IOException {
		request.setParameter("numPosti", "12");
		request.setParameter("nome", "Rosalba");
		request.setParameter("cognome", "Romano");
		request.setParameter("cellulare", "3138546099");
		request.setParameter("documento", "CX567G9");
		request.setParameter("email", "rromano@gmail.it");
		
		servlet.doGet(request, response);
		assertEquals("La registrazione è andata a buon fine!",
				request.getAttribute("errorTest"));
	}
}
