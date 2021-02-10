package it.RistoManager.Test.Control;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import it.RistoManager.Control.Menu.AggiungiProdotto;

class TestAggiungiProdotto extends Mockito {
	
	private AggiungiProdotto servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
			
	@BeforeEach
	void setUp() throws Exception {
		servlet= new AggiungiProdotto();
		request= new MockHttpServletRequest();
		response= new MockHttpServletResponse();
	}

	@AfterEach
	void tearDown() throws Exception {
		servlet=null;
		request=null;
		response=null;
		
	}

	@Test
	void testAggiuntaProdotto0() throws ServletException, IOException {
		request.addParameter("nome","");
		request.addParameter("prezzo","");
		request.addParameter("descrizione","");
		request.addParameter("categoria","");
		request.addParameter("ingredienti","");
		request.addParameter("foto","");
		
		String message = "L’inserimento fallisce perché il campo ‘nome prodotto’ è vuoto";
		servlet.doGet(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@Test
	void testAggiuntaProdotto1() throws ServletException, IOException {
		request.addParameter("nome","pizza");
		request.addParameter("prezzo","");
		request.addParameter("descrizione","");
		request.addParameter("categoria","");
		request.addParameter("ingredienti","");
		request.addParameter("foto","");
		
		String message = "L’inserimento fallisce perché il campo ‘prezzo’ è vuoto";
		servlet.doGet(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@Test
	void testAggiuntaProdotto2() throws ServletException, IOException {
		request.addParameter("nome","pizza");
		request.addParameter("prezzo","0.0001");
		request.addParameter("descrizione","");
		request.addParameter("categoria","");
		request.addParameter("ingredienti","");
		request.addParameter("foto","");
		
		String message = "L’inserimento fallisce perché la dimensione del campo ‘prezzo’ non è adatta";
		servlet.doGet(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	
	@Test
	void testAggiuntaProdotto3() throws ServletException, IOException {
		request.addParameter("nome","pizza");
		request.addParameter("prezzo","6,5");
		request.addParameter("descrizione","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		request.addParameter("categoria","");
		request.addParameter("ingredienti","");
		request.addParameter("foto","");
		
		String message = "L’inserimento fallisce perché la lunghezza del campo ‘descrizione’ è eccessiva";
		servlet.doGet(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@Test
	void testAggiuntaProdotto4() throws ServletException, IOException {
		request.addParameter("nome","pizza");
		request.addParameter("prezzo","6,5");
		request.addParameter("descrizione","Pizza rossa DOC");
		request.addParameter("ingredienti","");
		request.addParameter("categoria","");
		request.addParameter("foto","");
		
		String message = "L’inserimento fallisce perché il campo ‘ingredienti’ è vuoto";
		servlet.doGet(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@Test
	void testAggiuntaProdotto5() throws ServletException, IOException {
		request.addParameter("nome","pizza");
		request.addParameter("prezzo","6,5");
		request.addParameter("descrizione","Pizza rossa DOC");
		request.addParameter("ingredienti","cose?!");
		request.addParameter("categoria","");
		request.addParameter("foto","");
		
		String message = "L’inserimento fallisce perché il formato del campo ‘ingredienti’ non è adatto";
		servlet.doGet(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@Test
	void testAggiuntaProdotto6() throws ServletException, IOException {
		request.addParameter("nome","pizza");
		request.addParameter("prezzo","6,5");
		request.addParameter("descrizione","Pizza rossa DOC");
		request.addParameter("ingredienti","farina, acqua");
		request.addParameter("categoria","");
		request.addParameter("foto","");
		
		String message = "L’inserimento fallisce perché il campo ‘categoria’ è vuoto";
		servlet.doGet(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@Test
	void testAggiuntaProdotto7() throws ServletException, IOException {
		request.addParameter("nome","pizza");
		request.addParameter("prezzo","6,5");
		request.addParameter("descrizione","Pizza rossa DOC");
		request.addParameter("ingredienti","farina, acqua");
		request.addParameter("categoria","matematica");
		request.addParameter("foto","");
		
		String message = "L’inserimento fallisce perché il formato del campo ‘categoria’ è errato";
		servlet.doGet(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}

	@Test
	void testAggiuntaProdotto8() throws ServletException, IOException {
		request.addParameter("nome","pizza");
		request.addParameter("prezzo","6,5");
		request.addParameter("descrizione","Pizza rossa DOC");
		request.addParameter("ingredienti","farina, acqua");
		request.addParameter("categoria","pizza");
		request.addParameter("foto","");
		
		String message = "L’inserimento fallisce perché il campo ‘immagine’ è vuoto";
		servlet.doGet(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}

	@Test
	void testAggiuntaProdotto9() throws ServletException, IOException {
		request.addParameter("nome","pizza");
		request.addParameter("prezzo","6,5");
		request.addParameter("descrizione","Pizza rossa DOC");
		request.addParameter("ingredienti","farina, acqua");
		request.addParameter("categoria","pizza");
		request.addParameter("foto","margherita.png");
		
		String message = "L’inserimento va a buon fine";
		servlet.doGet(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
}
