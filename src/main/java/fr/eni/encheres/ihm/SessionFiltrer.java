package fr.eni.encheres.ihm;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class SessionFiltrer
 */
@WebFilter("/*")
public class SessionFiltrer implements Filter {

	/**
	 * Default constructor.
	 */
	public SessionFiltrer() {
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String login = (String) ((HttpServletRequest) request).getSession().getAttribute("login");

		String path = ((HttpServletRequest) request).getRequestURI();

		if (!path.endsWith("AccueilServlet")) {
			if (login == null) {
				request.getRequestDispatcher("Connexion").forward(request, response);
			}
		}
		
		chain.doFilter(request, response);

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
