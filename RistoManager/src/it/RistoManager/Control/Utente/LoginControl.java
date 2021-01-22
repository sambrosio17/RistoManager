package it.RistoManager.Control.Utente;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.RistoManager.DAO.AccountStaffDAO;
import it.RistoManager.Model.AccountStaffBean;

@WebServlet("/login")
public class LoginControl extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
    public LoginControl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		String redirectedPage = "";
		try {
			String ruolo = checkLogin(email, password);
			
			if(ruolo.equalsIgnoreCase("gestione")) {
				request.getSession().setAttribute("ruolo", "gestione");
				redirectedPage = "/gestione/index.jsp";
			} else if(ruolo.equalsIgnoreCase("sala")) {
				request.getSession().setAttribute("ruolo", "sala");
				redirectedPage = "/sala/index.jsp";
			} else if(ruolo.equalsIgnoreCase("cucina")) {
				request.getSession().setAttribute("ruolo", "cucina");
				request.setAttribute("fromLogin", true);
				redirectedPage = "/cucina/index.jsp";
			}
			
		} catch (Exception e) {
			System.out.println("LOGIN ERROR");
			request.getSession().setAttribute("loginError", "Credenziali non valide");
			redirectedPage = "/login.jsp";
		}
		response.sendRedirect(request.getContextPath() + redirectedPage);
	}
	
	private String checkLogin(String email, String password) throws Exception {
		AccountStaffDAO account = new AccountStaffDAO();
		AccountStaffBean bean = account.retrieveByEmail(email);
		
		System.out.println(password);
		
		System.out.println(email);
		System.out.println(bean);
		System.out.println(account.retrieveAll());
		
		if (bean == null || !bean.getPassword().equals(password)) {
			throw new Exception("Invalid login and password");
		} else {
			return bean.getRuolo().toString();
		}
	}

}
