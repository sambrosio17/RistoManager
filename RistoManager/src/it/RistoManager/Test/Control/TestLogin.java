package it.RistoManager.Test.Control;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import it.RistoManager.Control.Utente.LoginControl;
import it.RistoManager.Model.DAO.AccountStaffDAO;
import it.RistoManager.Model.Enity.AccountStaffBean;
import it.RistoManager.Model.Enity.AccountStaffBean.Ruolo;

class TestLogin extends Mockito{
	private LoginControl servlet = new LoginControl();
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private AccountStaffBean personale=new AccountStaffBean("marco@unisa.it","tizio","test","PasswordStaff_99",Ruolo.cucina);
	private AccountStaffDAO personaleDAO;
	@BeforeEach
	void setUp() throws Exception {
		servlet = new LoginControl();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		personaleDAO=new AccountStaffDAO();
		personaleDAO.create(personale);
	}

	@Test
	void testLogin1() throws ServletException, IOException {
		request.addParameter("email","");
		request.addParameter("password","");
		String message = "Il login non va a buon fine poiché il campo email è vuoto";
		servlet.doGet(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}

	@Test
	void testLogin2() throws ServletException, IOException {
		request.addParameter("email","abc1d");
		request.addParameter("password","");
		String message = "Il login non va a buon fine poiché il formato mail è errato";
		servlet.doGet(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@Test
	void testLogin3() throws ServletException, IOException {
		request.addParameter("email","marco@unisa.it");
		request.addParameter("password","");
		String message = "Il login non va a buon fine poiché il campo password è vuoto";
		servlet.doGet(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@Test
	void testLogin4() throws ServletException, IOException {
		request.addParameter("email","marco@unisa.it");
		request.addParameter("password","pass");
		String message = "Il login non va a buon fine poiché non viene trovata corrispondenza";
		servlet.doGet(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@Test
	void testLogin5() throws ServletException, IOException {
		request.addParameter("email","marco@unisa.it");
		request.addParameter("password","PasswordStaff_99");
		String message = "Il login viene effettuato correttamente";
		servlet.doGet(request, response);
		String result = (String) request.getAttribute("errorTest");
		assertEquals(message, result);
	}
	
	@AfterEach
	void tearDown() throws Exception {
		servlet = null;
		request = null;
		response = null;
		personaleDAO.delete(personale.getId());
	}

	
}
