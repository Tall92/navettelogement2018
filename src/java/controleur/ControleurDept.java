package controleur;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Departement;
import modele.Ufr;
import service.ServiceDept;
import service.ServiceDeptImpl;
import service.ServiceUfr;
import service.ServiceUfrImpl;

/**
 *
 * @author tall
 */
public class ControleurDept extends HttpServlet {

    private ServiceUfr su;
    private ServiceDept sd;

    @Override
    public void init() throws ServletException {
        sd = new ServiceDeptImpl();
        su = new ServiceUfrImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            List<Departement> depts = sd.listeDep();

            List<Ufr> ufrs = su.listeUfr();
            request.setAttribute("depts", depts);
            request.setAttribute("ufrs", ufrs);
            this.getServletContext().getRequestDispatcher("/WEB-INF/admin/departement.jsp").forward(request, response);
        } else if (action.equals("admin_modifier")) {

            String d = request.getParameter("iddep");

            int id = Integer.parseInt(d);

            Departement dept = sd.rechercherDept(id);

            List<Ufr> ufrs = su.listeUfr();
            List<Departement> depts = sd.listeDep();

            request.setAttribute("depts", depts);
            request.setAttribute("ufrs", ufrs);

            request.setAttribute("dept", dept);

            request.setAttribute("objet", "modifier");

            this.getServletContext().getRequestDispatcher("/WEB-INF/admin/departement.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {

            if (action.equals("ajouter")) {

                String nomDep = request.getParameter("nomDep");

                String ufr = request.getParameter("ufr");

                int id = Integer.parseInt(ufr);

                if (!nomDep.equals("") && !ufr.equals("")) {

                    Departement a = new Departement();
                    a.setUfr(new Ufr(id, ""));
                    a.setNomDept(nomDep);

                    String message = sd.ajouterDep(a);
                }

                List<Departement> depts = sd.listeDep();

                List<Ufr> ufrs = su.listeUfr();
                request.setAttribute("depts", depts);
                request.setAttribute("ufrs", ufrs);

                this.getServletContext().getRequestDispatcher("/WEB-INF/admin/departement.jsp").forward(request, response);

            } else if (action.equals("modifier")) {
                
                String dept = request.getParameter("iddep");

                int iddep = Integer.parseInt(dept);

                String nomDep = request.getParameter("nomDep");

                String ufr = request.getParameter("ufr");

                int id = Integer.parseInt(ufr);

                if (!nomDep.equals("") && !dept.equals("")) {
                    
                    System.out.println("bonjour");

                    Departement a = new Departement();
                    a.setIdDept(iddep);
                    a.setUfr(new Ufr(id, ""));
                    a.setNomDept(nomDep);

                    String message = sd.modifierDep(a);
                }

                List<Departement> depts = sd.listeDep();

                List<Ufr> ufrs = su.listeUfr();
                request.setAttribute("depts", depts);

                request.setAttribute("ufrs", ufrs);

                this.getServletContext().getRequestDispatcher("/WEB-INF/admin/departement.jsp").forward(request, response);

            }

        }
    }
}
