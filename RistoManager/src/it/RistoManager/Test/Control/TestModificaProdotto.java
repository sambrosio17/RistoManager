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
		
		String message = "L’inserimento fallisce perché il campo ‘nome prodotto’ è vuoto";
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
		
		String message = "L’inserimento fallisce perché il campo ‘prezzo’ non rispetta il formato";
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
		
		String message = "L’inserimento fallisce perché la dimensione del campo ‘prezzo’ non è adatta";
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
		
		String message = "L’inserimento fallisce perché la lunghezza del campo ‘descrizione’ è eccessiva";
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
		
		String message = "L’inserimento fallisce perché il campo ‘ingredienti’ è vuoto";
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
		
		String message = "L’inserimento fallisce perché il formato del campo ‘ingredienti’ non è adatto";
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
		
		String message = "L’inserimento fallisce perché il campo ‘categoria’ è vuoto";
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
		
		String message = "L’inserimento fallisce perché il formato del campo ‘categoria’ è errato";
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
		
		String message = "L’inserimento fallisce perché il campo ‘immagine’ è vuoto";
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
		
		String message = "L’inserimento va a buon fine";
		servlet.doGet(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);	
		
	}

}
