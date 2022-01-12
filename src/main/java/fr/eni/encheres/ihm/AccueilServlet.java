package fr.eni.encheres.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.UtilisateurManagerImplAngelo;


@WebServlet("/AccueilServlet")
public class AccueilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AccueilServlet() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String option = "Toutes";
		
		if (request.getParameter("recherche")!= null) {
			option = request.getParameter("listeDeroulante");

			
			
			// je récupère sous forme de String l'option choisie quand le bouton recherche est choisie

			System.out.println(option);
			// je rï¿½cupï¿½re sous forme de String l'option choisie quand le bouton recherche est choisie

			
			
		}
		
		
		try {
			request.setAttribute("Liste", UtilisateurManagerImplAngelo.getInstance().FiltreSuivantCategorie(option));
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		request.getRequestDispatcher("WEB-INF/Accueil.jsp").forward(request, response);

		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
