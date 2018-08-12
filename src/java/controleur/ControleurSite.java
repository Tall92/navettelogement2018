package controleur;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Gestionnaire;
import modele.Site;
import service.ServiceGestionImpl;
import service.ServiceGestionnaire;
import service.ServiceSite;
import service.ServiceSiteImpl;

/**
 *
 * @author tall
 */
public class ControleurSite extends HttpServlet {

    private ServiceGestionnaire sg;
    private ServiceSite si;

    @Override
    public void init() throws ServletException {
        si = new ServiceSiteImpl();
        sg = new ServiceGestionImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            List<Site> sites = si.listeSite();

            List<Gestionnaire> gestions = sg.listeGestion();
            request.setAttribute("sites", sites);
            request.setAttribute("gestions", gestions);
            this.getServletContext().getRequestDispatcher("/WEB-INF/admin/site.jsp").forward(request, response);
        } else if (action.equals("admin_modifier")) {

            String d = request.getParameter("iddep");

            int id = Integer.parseInt(d);

            Site site = si.rechercherSite(id);

            List<Gestionnaire> gestions = sg.listeGestion();
            List<Site> sites = si.listeSite();

            request.setAttribute("gestions", gestions);
            request.setAttribute("sites", sites);

            request.setAttribute("site", site);

            request.setAttribute("objet", "modifier");

            this.getServletContext().getRequestDispatcher("/WEB-INF/admin/site.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {

            if (action.equals("ajouter")) {

                String nomSite = request.getParameter("nomSite");

                String gestion = request.getParameter("gestion");

                int id = Integer.parseInt(gestion);
                
                if (!nomSite.equals("") && !gestion.equals("")) {

                    Site s = new Site();
                    s.setGes(new Gestionnaire(id));
                    s.setNomSite(nomSite);
                    
                    
                    String message = si.ajouterSite(s);
                }
                
                
                List<Site> sites = si.listeSite();

                List<Gestionnaire> gestions = sg.listeGestion();
                request.setAttribute("sites", sites);
                request.setAttribute("gestions", gestions);

                this.getServletContext().getRequestDispatcher("/WEB-INF/admin/site.jsp").forward(request, response);

            } else if (action.equals("modifier")) {

                String site = request.getParameter("site");

                int idsite = Integer.parseInt(site);

                String nomSite = request.getParameter("nomSite");

                String gestions = request.getParameter("gestion");

                int id = Integer.parseInt(gestions);

                if (!nomSite.equals("") && !site.equals("")) {

                    System.out.println("bonjour");

                    Site a = new Site();
                    a.setSit(idsite);
                    a.setGes(new Gestionnaire(id, "", "", "", ""));
                    a.setNomSite(nomSite);

                    String message = si.modifierSite(a);
                }

                List<Site> sites = si.listeSite();

                List<Gestionnaire> gestion = sg.listeGestion();
                request.setAttribute("site", sites);

                request.setAttribute("gestion", gestion);

                this.getServletContext().getRequestDispatcher("/WEB-INF/admin/site.jsp").forward(request, response);

            }

        }
    }
}
