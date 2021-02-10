package it.RistoManager.Control.Utente;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.RistoManager.Model.DAO.AccountStaffDAO;
import it.RistoManager.Model.Enity.AccountStaffBean;

/**
 * 
 * Gestisce il Login alla piattaforma
 *
 */

@WebServlet("/login")
public class LoginControl extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
    public LoginControl() {
        super();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String redirectedPage = "";
		HttpSession session = request.getSession();
		session.setAttribute("errorType", null);
		session.setAttribute("error", null);
		try {
		
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			
			if((email.length())==0) {
				request.setAttribute("errorTest", "Il login non va a buon fine poiché il campo email è vuoto");
				session.setAttribute("errorType","email");
				session.setAttribute("error", "il campo non rispetta la lunghezza minima");
				response.sendRedirect(("./login.jsp"));
				return;
			}
			else if(!(email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"))){
				request.setAttribute("errorTest", "Il login non va a buon fine poiché il formato mail è errato");
				session.setAttribute("errorType","email");
				session.setAttribute("error", "il campo non rispetta il formato");
				response.sendRedirect(("./login.jsp"));
				return;
			}
			else if(password.length()==0){
				request.setAttribute("errorTest", "Il login non va a buon fine poiché il campo password è vuoto");
				session.setAttribute("errorType","password");
				session.setAttribute("error", "il campo non rispetta la lunghezza minima");
				response.sendRedirect(("./login.jsp"));
				return;
			}else {
		
				String ruolo = checkLogin(email, password);
				
				if(ruolo.equalsIgnoreCase("gestione")) {
					request.setAttribute("errorTest", "Il login viene effettuato correttamente");
					
					request.getSession().setAttribute("ruolo", "gestione");
					redirectedPage = "/gestione/index.jsp";
				} else if(ruolo.equalsIgnoreCase("sala")) {
					request.setAttribute("errorTest", "Il login viene effettuato correttamente");
					
					request.getSession().setAttribute("ruolo", "sala");
					redirectedPage = "/sala/index.jsp";
				} else if(ruolo.equalsIgnoreCase("cucina")) {
					request.setAttribute("errorTest", "Il login viene effettuato correttamente");
					
					request.getSession().setAttribute("ruolo", "cucina");
					request.setAttribute("fromLogin", true);
					redirectedPage = "/cucina/index.jsp";
				}else {
					
					request.setAttribute("errorTest", "Il login non va a buon fine poiché non viene trovata corrispondenza");
					session.setAttribute("errorType","emailPassword");
					session.setAttribute("error", "non esiste corrispondenza");
					redirectedPage= "/login.jsp";	
				}
				
			}
			
			response.sendRedirect(request.getContextPath() + redirectedPage);
		} catch (Exception e) {
			System.out.println("LOGIN ERROR");
			request.getSession().setAttribute("loginError", "Credenziali non valide");
		}

	}
	
	private String checkLogin(String email, String password) throws Exception {
		AccountStaffDAO account = new AccountStaffDAO();
		AccountStaffBean bean = account.retrieveByEmail(email);
		
		System.out.println(password);
		
		System.out.println(email);
		System.out.println(bean);
		System.out.println(account.retrieveAll());
		
		if (bean == null || !bean.getPassword().equals(password)) {
			return "invalid";
		} else {
			return bean.getRuolo().toString();
		}
	}

}
