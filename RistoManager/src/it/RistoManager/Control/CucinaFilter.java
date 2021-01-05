package it.RistoManager.Control;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/CucinaFIlter")
public class CucinaFilter implements Filter {

	public CucinaFilter() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest hrequest = (HttpServletRequest) request;
		HttpServletResponse hresponse = (HttpServletResponse) response;
		
		String loginURI = hrequest.getContextPath() + "/cucina";
		boolean loginRequest = hrequest.getRequestURI().startsWith(loginURI);

		if(loginRequest) {
			
			HttpSession session = hrequest.getSession(false);
			boolean loggedIn = session != null && session.getAttribute("ruolo") != null;
			String ruolo = (String)session.getAttribute("ruolo");
			boolean authorized = ruolo.equalsIgnoreCase("cucina");

			if(!loggedIn || !authorized) {
				hresponse.sendRedirect(hrequest.getContextPath()+ "/login.jsp");
			} else {
				chain.doFilter(request, response);
			}
		} else {
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
