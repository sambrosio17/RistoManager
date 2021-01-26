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

import it.RistoManager.Control.Utente.PrenotazioneTavolo;
import it.RistoManager.Control.Utente.Registrazione;

class TestPrenotazione {

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private PrenotazioneTavolo servlet;
	
	@BeforeEach
	void setUp() throws Exception {
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		servlet = new PrenotazioneTavolo();
	}

	@AfterEach
	void tearDown() throws Exception {
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
		assertEquals("L'inserimento non va a buon fine perchè il numero di posti deve essere compreso tra 1 e 15",
				request.getAttribute("errorTest"));
	}
	
	@Test
	void testFormatoData() throws ServletException, IOException {
		request.setParameter("numPosti", "12");
		request.setParameter("nome", "Rosalba");
		request.setParameter("cognome", "Romano");
		request.setParameter("cellulare", "3138546099");
		request.setParameter("documento", "CX567G9");
		request.setParameter("email", "rromano@gmail.it");
		request.setParameter("data", "10/02/21");
		
		servlet.doGet(request, response);
		assertEquals("L'inserimento non va a buon fine perchè il campo data non rispetta il formato",
				request.getAttribute("errorTest"));
	}
	
	@Test
	void testRangeData() throws ServletException, IOException {
		request.setParameter("numPosti", "12");
		request.setParameter("nome", "Rosalba");
		request.setParameter("cognome", "Romano");
		request.setParameter("cellulare", "3138546099");
		request.setParameter("documento", "CX567G9");
		request.setParameter("email", "rromano@gmail.it");
		request.setParameter("data", "2021-01-20");
		
		servlet.doGet(request, response);
		assertEquals("L'inserimento non va a buon fine perchè la data non può essere precedente a quella odierna",
				request.getAttribute("errorTest"));
	}
	
	@Test
	void testFormatoOra() throws ServletException, IOException {
		request.setParameter("numPosti", "12");
		request.setParameter("nome", "Rosalba");
		request.setParameter("cognome", "Romano");
		request.setParameter("cellulare", "3138546099");
		request.setParameter("documento", "CX567G9");
		request.setParameter("email", "rromano@gmail.it");
		request.setParameter("data", "2021-07-18");
		request.setParameter("ora", "1130");
		
		servlet.doGet(request, response);
		assertEquals("L'inserimento non va a buon fine perchè il campo ora non rispetta il formato",
				request.getAttribute("errorTest"));
	}
	
	@Test
	void testRangeOra() throws ServletException, IOException {
		request.setParameter("numPosti", "12");
		request.setParameter("nome", "Rosalba");
		request.setParameter("cognome", "Romano");
		request.setParameter("cellulare", "3138546099");
		request.setParameter("documento", "CX567G9");
		request.setParameter("email", "rromano@gmail.it");
		request.setParameter("data", "2021-07-18");
		request.setParameter("ora", "11:30:00");
		
		servlet.doGet(request, response);
		assertEquals("L'inserimento non va a buon fine perchè l'orario deve essere compreso tra le 12:00 e le 23:00",
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
		request.setParameter("data", "2021-07-18");
		request.setParameter("ora", "19:30:00");
		
		servlet.doGet(request, response);
		assertEquals("La prenotazione è andata a buon fine!",
				request.getAttribute("errorTest"));
	}


}
