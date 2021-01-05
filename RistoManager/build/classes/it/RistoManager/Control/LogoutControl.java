package it.RistoManager.Control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Logout")
public class LogoutControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public LogoutControl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute("ruolo");
		request.getSession().invalidate();

		String redirectedPage = "/login.jsp";
		response.sendRedirect(request.getContextPath() + redirectedPage);	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
