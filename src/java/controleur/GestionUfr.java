package controleur;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.GestionnaireUfr;
import service.ServiceGestionUfrImpl;
import service.ServiceGestionnaireUfr;

/**
 *
 * @author kane
 */
public class GestionUfr extends HttpServlet {
    
    private ServiceGestionnaireUfr sgu;

    @Override
    public void init() throws ServletException {
        sgu = new ServiceGestionUfrImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            this.getServletContext().getRequestDispatcher("/WEB-INF/logingestionufr.jsp").forward(request, response);
        } else if (action.equals("logout")) {

            HttpSession session = request.getSession();

            session.removeAttribute("ses_ges");

            session.invalidate();

            this.getServletContext().getRequestDispatcher("/WEB-INF/logingestionufr.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {

            if (action.equals("Connexion")) {
                String login = request.getParameter("email");
                String password = request.getParameter("motdepasse");

                GestionnaireUfr gu = sgu.connexion(login, password);

                if (gu == null) {
                    String message = "Login et/ou mot de passe incorrect ou compte inactif";
                    request.setAttribute("message", message);
                    this.getServletContext().getRequestDispatcher("/WEB-INF/logingestionufr.jsp").forward(request, response);
                } else {

                    HttpSession session = request.getSession();

                    session.setAttribute("ses_ges", gu);

                    this.getServletContext().getRequestDispatcher("/WEB-INF/gestionufr/index.jsp").forward(request, response);

                }
            }
        }
    }

}
