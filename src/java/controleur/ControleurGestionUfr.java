package controleur;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.GestionnaireUfr;
import modele.Ufr;
import service.ServiceGestionUfrImpl;
import service.ServiceGestionnaireUfr;
import service.ServiceUfr;
import service.ServiceUfrImpl;

/**
 *
 * @author tall
 */
public class ControleurGestionUfr extends HttpServlet {

    private ServiceGestionnaireUfr sgu;
    private ServiceUfr su;

    @Override
    public void init() throws ServletException {
        sgu = new ServiceGestionUfrImpl();
        su = new ServiceUfrImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            List<GestionnaireUfr> gs = sgu.listeGestion();

            List<Ufr> ufrs = su.listeUfr();

            request.setAttribute("ufrs", ufrs);

            request.setAttribute("gestions", gs);

            this.getServletContext().getRequestDispatcher("/WEB-INF/admin/gestionufr.jsp").forward(request, response);
        } else if (action.equals("admin_modifier")) {

            String user = request.getParameter("idges");

            int id = Integer.parseInt(user);

            GestionnaireUfr g = sgu.rechercherGestion(id);

            List<GestionnaireUfr> gestions = sgu.listeGestion();

            List<Ufr> ufrs = su.listeUfr();

            request.setAttribute("ufrs", ufrs);

            request.setAttribute("gestions", gestions);

            request.setAttribute("objet", "modifier");

            request.setAttribute("gestion", g);

            this.getServletContext().getRequestDispatcher("/WEB-INF/admin/gestionufr.jsp").forward(request, response);
        } else if (action.equals("admin_supprimer")) {

            String user = request.getParameter("idges");

            int id = Integer.parseInt(user);

            String message = sgu.supprimer(id);

            List<GestionnaireUfr> gestions = sgu.listeGestion();

            List<Ufr> ufrs = su.listeUfr();

            request.setAttribute("ufrs", ufrs);

            request.setAttribute("gestions", gestions);

            this.getServletContext().getRequestDispatcher("/WEB-INF/admin/gestionufr.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");

        if (action != null) {
            if (action.equals("ajouter")) {

                String ufr = request.getParameter("ufr");

                int idUfr = Integer.parseInt(ufr);

                String prenom = request.getParameter("prenom");
                String nom = request.getParameter("nom");
                String telephone = request.getParameter("telephone");
                String login = request.getParameter("email");

                if (!prenom.equals("") && !nom.equals("") && !login.equals("") && !telephone.equals("")) {
                    GestionnaireUfr g = new GestionnaireUfr();

                    g.setPrenom(prenom);
                    g.setNom(nom);
                    g.setTelephone(telephone);
                    g.setLogin(login);
                    g.setUfr(new Ufr(idUfr));

                    sgu.ajouterGes(g);

                }

                List<GestionnaireUfr> gestions = sgu.listeGestion();

                List<Ufr> ufrs = su.listeUfr();

                request.setAttribute("ufrs", ufrs);

                request.setAttribute("gestions", gestions);

                this.getServletContext().getRequestDispatcher("/WEB-INF/admin/gestionufr.jsp").forward(request, response);
            } else if (action.equals("modifier")) {

                String ufr = request.getParameter("ufr");

                int idUfr = Integer.parseInt(ufr);
                
                String idGesUfr = request.getParameter("idGesUfr");

                int idges = Integer.parseInt(idGesUfr);

                String prenom = request.getParameter("prenom");
                String nom = request.getParameter("nom");
                String telephone = request.getParameter("telephone");
                String login = request.getParameter("email");

                if (!prenom.equals("") && !nom.equals("") && !login.equals("") && !telephone.equals("")) {
                    GestionnaireUfr g = new GestionnaireUfr();

                    g.setIdGesUfr(idges);
                    g.setPrenom(prenom);
                    g.setNom(nom);
                    g.setTelephone(telephone);
                    g.setLogin(login);
                    g.setUfr(new Ufr(idUfr));

                    sgu.modifierGes(g);

                }

                List<GestionnaireUfr> gestions = sgu.listeGestion();

                List<Ufr> ufrs = su.listeUfr();

                request.setAttribute("ufrs", ufrs);

                request.setAttribute("gestions", gestions);

                this.getServletContext().getRequestDispatcher("/WEB-INF/admin/gestionufr.jsp").forward(request, response);
            }
        }
    }

}
