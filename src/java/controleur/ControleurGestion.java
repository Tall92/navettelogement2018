package controleur;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Gestionnaire;
import service.ServiceGestionImpl;
import service.ServiceGestionnaire;

/**
 *
 * @author tall
 */
public class ControleurGestion extends HttpServlet {

    private ServiceGestionnaire sg;

    @Override
    public void init() throws ServletException {
        sg = new ServiceGestionImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            List<Gestionnaire> gs = sg.listeGestion();

            request.setAttribute("gestions", gs);

            this.getServletContext().getRequestDispatcher("/WEB-INF/admin/gestionnaire.jsp").forward(request, response);
        } else if (action.equals("admin_modifier")) {

            String user = request.getParameter("idges");

            int id = Integer.parseInt(user);

            Gestionnaire g = sg.rechercherGestion(id);

            List<Gestionnaire> gestions = sg.listeGestion();

            request.setAttribute("gestions", gestions);

            request.setAttribute("objet", "modifier");

            request.setAttribute("gestion", g);

            this.getServletContext().getRequestDispatcher("/WEB-INF/admin/gestionnaire.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action != null) {
            if (action.equals("ajouter")) {

                String prenom = request.getParameter("prenom");
                String nom = request.getParameter("nom");
                String adresse = request.getParameter("adresse");
                String telephone = request.getParameter("telephone");

                if (!prenom.equals("") && !nom.equals("") && !adresse.equals("") && !telephone.equals("")) {
                    Gestionnaire g = new Gestionnaire();

                    g.setPrenom(prenom);
                    g.setNom(nom);
                    g.setAdr(adresse);
                    g.setTel(telephone);

                    sg.ajouterGes(g);

                }

                List<Gestionnaire> gestions = sg.listeGestion();

                request.setAttribute("gestions", gestions);

                this.getServletContext().getRequestDispatcher("/WEB-INF/admin/gestionnaire.jsp").forward(request, response);
            }
        }
    }

}
