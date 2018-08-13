package controleur;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Navette;
import service.ServiceNavette;
import service.ServiceNavetteImpl;

/**
 *
 * @author tall
 */
public class ControleurNavette extends HttpServlet {

    private ServiceNavette sn;

    @Override
    public void init() throws ServletException {
        sn = new ServiceNavetteImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {

            List<Navette> navettes = sn.listeNavettes();

            request.setAttribute("navettes", navettes);

            this.getServletContext().getRequestDispatcher("/WEB-INF/admin/navette.jsp").forward(request, response);

        } else if (action.equals("admin_modifier")) {
            String matricule = request.getParameter("matricule");
            
             String nav = request.getParameter("nav");
             
            int idNav = Integer.parseInt(nav);

          Navette nave = sn.rechercherNavette(idNav);

            List<Navette> naves = sn.listeNavettes();

            request.setAttribute("naves", naves);

            request.setAttribute("objet", "modifier");

            request.setAttribute("nave", nave);

            this.getServletContext().getRequestDispatcher("/WEB-INF/admin/navette.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action != null) {

            if (action.equals("ajouter")) {

                String matricule = request.getParameter("matricule");

                String place = request.getParameter("place");

                int nbre = Integer.parseInt(place);

                if (!matricule.equals("") && !place.equals("")) {

                    Navette n = new Navette(matricule, nbre);

                    sn.ajouterNav(n);

                }

                List<Navette> navettes = sn.listeNavettes();

                request.setAttribute("navettes", navettes);

                this.getServletContext().getRequestDispatcher("/WEB-INF/admin/navette.jsp").forward(request, response);

            } else if (action.equals("modifier")) {
                String nav = request.getParameter("nav");

                String matricule = request.getParameter("matricule");

                String place = request.getParameter("place");

                int id = Integer.parseInt(matricule);

                if (!matricule.equals("")) {

                    Navette a = new Navette();
                    a.setIdNav(id);
                    a.setMatricule(matricule);

                    String message = sn.modifierNavette(a);
                }

                List<Navette> naves = sn.listeNavettes();
                request.setAttribute("naves", naves);

                this.getServletContext().getRequestDispatcher("/WEB-INF/admin/navette.jsp").forward(request, response);

            }
        }

    }

}
