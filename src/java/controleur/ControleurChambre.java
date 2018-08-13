package controleur;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Chambre;
import modele.Site;
import service.ServiceChambre;
import service.ServiceChambreImpl;
import service.ServiceSite;
import service.ServiceSiteImpl;

/**
 *
 * @author tall
 */
public class ControleurChambre extends HttpServlet {

    private ServiceSite ss;
    private ServiceChambre sc;

    @Override
    public void init() throws ServletException {
        ss = new ServiceSiteImpl();
        sc = new ServiceChambreImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            List<Chambre> chambres = sc.listeChambres();
            List<Site> sites = ss.listeSite();

            request.setAttribute("sites", sites);
            request.setAttribute("chambres", chambres);

            this.getServletContext().getRequestDispatcher("/WEB-INF/admin/chambre.jsp").forward(request, response);

        } else if (action.equals("admin_modifier")) {
            
            String d = request.getParameter("idcham");

            int id = Integer.parseInt(d);

            Chambre c = sc.rechercher(id);

            List<Chambre> chambres = sc.listeChambres();
            List<Site> sites = ss.listeSite();

            request.setAttribute("chambres", chambres);
            request.setAttribute("sites", sites);

            request.setAttribute("chambre", c);

            request.setAttribute("objet", "modifier");

            this.getServletContext().getRequestDispatcher("/WEB-INF/admin/chambre.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {

            if (action.equals("ajouter")) {

                String numero = request.getParameter("numero");

                String site = request.getParameter("nomsite");

                int id = Integer.parseInt(site);

                if (!numero.equals("") && !site.equals("")) {

                    Chambre c = new Chambre(new Site(id), numero);

                    sc.ajouter(c);

                }

                List<Chambre> chambres = sc.listeChambres();
                List<Site> sites = ss.listeSite();

                request.setAttribute("chambres", chambres);
                request.setAttribute("sites", sites);

                this.getServletContext().getRequestDispatcher("/WEB-INF/admin/chambre.jsp").forward(request, response);

            } else if (action.equals("modifier")) {
//                String dept = request.getParameter("i");
//
//                int iddep = Integer.parseInt(dept);
//
//                String nomDep = request.getParameter("nomDep");
//
//                String ufr = request.getParameter("ufr");
//
//                int id = Integer.parseInt(ufr);
//
//                if (!nomDep.equals("") && !dept.equals("")) {
//                    
//                    System.out.println("bonjour");
//
//                    Departement a = new Departement();
//                    a.setIdDept(iddep);
//                    a.setUfr(new Ufr(id, ""));
//                    a.setNomDept(nomDep);
//
//                    String message = sd.modifierDep(a);
//                }
//
//                List<Departement> depts = sd.listeDep();
//
//                List<Ufr> ufrs = su.listeUfr();
//                request.setAttribute("depts", depts);
//
//                request.setAttribute("ufrs", ufrs);
//
//                this.getServletContext().getRequestDispatcher("/WEB-INF/admin/departement.jsp").forward(request, response);

            }

        }

    }
}

