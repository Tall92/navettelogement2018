package controleur;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.Formation;
import modele.Utilisateur;
import service.ServiceForm;
import service.ServiceFormImpl;

/**
 *
 * @author kane
 */
public class Responsable extends HttpServlet {

    private ServiceForm sf;

    @Override
    public void init() throws ServletException {
        sf = new ServiceFormImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            this.getServletContext().getRequestDispatcher("/WEB-INF/responsable/index.jsp").forward(request, response);
        } else if (action.equals("enseignant")) {
            String maformation = request.getParameter("formation");

            int f = Integer.parseInt(maformation);

            List<Utilisateur> enseignants = sf.listeEnseignants(f);

            Formation formation = sf.rechercher(f);

            request.setAttribute("formation", formation);

            request.setAttribute("enseignants", enseignants);
            
            this.getServletContext().getRequestDispatcher("/WEB-INF/responsable/enseignant.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
