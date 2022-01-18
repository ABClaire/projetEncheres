package fr.eni.encheres.ihm;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import fr.eni.encheres.bll.ArticleVenduManagerImpl;
import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bo.Utilisateur;

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
		Utilisateur login = (Utilisateur) ((HttpServletRequest) request).getSession().getAttribute("utilisateur");

		String path = ((HttpServletRequest) request).getRequestURI();

		if (!path.endsWith("AccueilServlet") & !path.endsWith("MotDePasseOublie")) {
			if (login == null) {
				request.getRequestDispatcher("Connexion").forward(request, response);
			}
		}
		
		// Récupération de la date du jour
		LocalDate dateDuJour = LocalDate.now();
		
		// Envoi de la date du jour en BLL

		try {
			ArticleVenduManagerImpl.getInstance().actualisationEtatEnchereBDD(dateDuJour);
		} catch (BLLException e) {
			e.printStackTrace();
		}


		chain.doFilter(request, response);

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
