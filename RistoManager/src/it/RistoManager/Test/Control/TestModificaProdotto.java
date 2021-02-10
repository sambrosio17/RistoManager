package it.RistoManager.Test.Control;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import it.RistoManager.Control.Menu.ModificaProdotto;
import it.RistoManager.Model.DAO.ProdottoDAO;
import it.RistoManager.Model.Enity.ProdottoBean;

class TestModificaProdotto extends Mockito {
	private ModificaProdotto servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	
	private ProdottoBean pb;
	private ProdottoDAO prodottoDAO;
	
	@BeforeEach
	void setUp() throws Exception {
		servlet= new ModificaProdotto();
		request= new MockHttpServletRequest();
		response= new MockHttpServletResponse();
		
		List<String> ingredientiTest= new ArrayList<String>(Arrays.asList("uno"));
		pb=new ProdottoBean("prodottoTest",10,"descrizioneTest","immagineTest.jpg","categoriaTest",ingredientiTest);
		
		prodottoDAO= new ProdottoDAO();
		prodottoDAO.create(pb);
		
		int id= pb.getId();
		request.removeParameter("id");
		request.setParameter("id",String.valueOf(id));
		
	}

	@AfterEach
	void tearDown() throws Exception {
		servlet=null;
		request=null;
		response=null;
		prodottoDAO.delete(pb.getId());
	}

	@Test
	void testModificaProdotto0() throws ServletException, IOException {
		
		request.addParameter("nome","");
		request.addParameter("prezzo","6.5");
		request.addParameter("descrizione","Pizza Rossa DOC");
		request.addParameter("categoria","pizza");
		request.addParameter("ingredienti","farina, acqua");
		request.addParameter("immagine","margherita.png");
		
		String message = "L�inserimento fallisce perch� il campo �nome prodotto� � vuoto";
		servlet.doGet(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);	
		
	}
	
	@Test
	void testModificaProdotto1() throws ServletException, IOException {
		
		request.addParameter("nome","pizza");
		request.addParameter("prezzo","");
		request.addParameter("descrizione","Pizza Rossa DOC");
		request.addParameter("categoria","pizza");
		request.addParameter("ingredienti","farina, acqua");
		request.addParameter("immagine","margherita.png");
		
		String message = "L�inserimento fallisce perch� il campo �prezzo� non rispetta il formato";
		servlet.doGet(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);	
		
	}
	
	@Test
	void testModificaProdotto2() throws ServletException, IOException {
		
		request.addParameter("nome","pizza");
		request.addParameter("prezzo","0.0001");
		request.addParameter("descrizione","Pizza Rossa DOC");
		request.addParameter("categoria","pizza");
		request.addParameter("ingredienti","farina, acqua");
		request.addParameter("immagine","margherita.png");
		
		String message = "L�inserimento fallisce perch� la dimensione del campo �prezzo� non � adatta";
		servlet.doGet(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);	
		
	}
	
	@Test
	void testModificaProdotto3() throws ServletException, IOException {
		
		request.addParameter("nome","pizza");
		request.addParameter("prezzo","6.5");
		request.addParameter("descrizione","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		request.addParameter("categoria","pizza");
		request.addParameter("ingredienti","farina, acqua");
		request.addParameter("immagine","margherita.png");
		
		String message = "L�inserimento fallisce perch� la lunghezza del campo �descrizione� � eccessiva";
		servlet.doGet(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);	
		
	}

	@Test
	void testModificaProdotto4() throws ServletException, IOException {
		
		request.addParameter("nome","pizza");
		request.addParameter("prezzo","6.5");
		request.addParameter("descrizione","Pizza rossa DOC");
		request.addParameter("categoria","pizza");
		request.addParameter("ingredienti","");
		request.addParameter("immagine","margherita.png");
		
		String message = "L�inserimento fallisce perch� il campo �ingredienti� � vuoto";
		servlet.doGet(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);	
		
	}
	
	@Test
	void testModificaProdotto5() throws ServletException, IOException {
		
		request.addParameter("nome","pizza");
		request.addParameter("prezzo","6.5");
		request.addParameter("descrizione","Pizza rossa DOC");
		request.addParameter("categoria","pizza");
		request.addParameter("ingredienti","cose?-");
		request.addParameter("immagine","margherita.png");
		
		String message = "L�inserimento fallisce perch� il formato del campo �ingredienti� non � adatto";
		servlet.doGet(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);	
		
	}
	
	@Test
	void testModificaProdotto6() throws ServletException, IOException {
		
		request.addParameter("nome","pizza");
		request.addParameter("prezzo","6.5");
		request.addParameter("descrizione","Pizza rossa DOC");
		request.addParameter("categoria","");
		request.addParameter("ingredienti","farina, acqua");
		request.addParameter("immagine","margherita.png");
		
		String message = "L�inserimento fallisce perch� il campo �categoria� � vuoto";
		servlet.doGet(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);	
		
	}
	
	@Test
	void testModificaProdotto7() throws ServletException, IOException {
		
		request.addParameter("nome","pizza");
		request.addParameter("prezzo","6.5");
		request.addParameter("descrizione","Pizza rossa DOC");
		request.addParameter("categoria","matematica");
		request.addParameter("ingredienti","farina, acqua");
		request.addParameter("immagine","margherita.png");
		
		String message = "L�inserimento fallisce perch� il formato del campo �categoria� � errato";
		servlet.doGet(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);	
		
	}
	
	@Test
	void testModificaProdotto8() throws ServletException, IOException {
		
		request.addParameter("nome","pizza");
		request.addParameter("prezzo","6.5");
		request.addParameter("descrizione","Pizza rossa DOC");
		request.addParameter("categoria","pizza");
		request.addParameter("ingredienti","farina, acqua");
		request.addParameter("immagine","");
		
		String message = "L�inserimento fallisce perch� il campo �immagine� � vuoto";
		servlet.doGet(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);	
		
	}
	
	@Test
	void testModificaProdotto9() throws ServletException, IOException {
		
		request.addParameter("nome","pizza");
		request.addParameter("prezzo","6.5");
		request.addParameter("descrizione","Pizza rossa DOC");
		request.addParameter("categoria","pizza");
		request.addParameter("ingredienti","farina, acqua");
		request.addParameter("immagine","margherita.png");
		
		String message = "L�inserimento va a buon fine";
		servlet.doGet(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);	
		
	}

}
