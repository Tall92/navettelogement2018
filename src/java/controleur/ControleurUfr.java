package controleur;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Ufr;
import service.ServiceUfr;
import service.ServiceUfrImpl;

/**
 *
 * @author tall
 */
public class ControleurUfr extends HttpServlet {

    private ServiceUfr su;

    @Override
    public void init() throws ServletException {
        su = new ServiceUfrImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            List<Ufr> ufrs = su.listeUfr();
            request.setAttribute("ufrs", ufrs);
            this.getServletContext().getRequestDispatcher("/WEB-INF/admin/ufr.jsp").forward(request, response);
        } else if (action.equals("admin_modifier")) {

            String u = request.getParameter("idufr");

            int id = Integer.parseInt(u);

            Ufr ufr = su.rechercherUfr(id);

            List<Ufr> ufrs = su.listeUfr();

            request.setAttribute("ufrs", ufrs);

            request.setAttribute("objet", "modifier");

            request.setAttribute("ufr", ufr);

            this.getServletContext().getRequestDispatcher("/WEB-INF/admin/ufr.jsp").forward(request, response);
        } else if (action.equals("admin_supprimer")) {

            String u = request.getParameter("idufr");

            int id = Integer.parseInt(u);

            su.supprimer(id);

            List<Ufr> ufrs = su.listeUfr();

            request.setAttribute("ufrs", ufrs);

            this.getServletContext().getRequestDispatcher("/WEB-INF/admin/ufr.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action != null) {

            if (action.equals("ajouter")) {

                String nomUfr = request.getParameter("nomUfr");

                if (!nomUfr.equals("")) {

                    Ufr a = new Ufr();
                    a.setNomUfr(nomUfr);

                    String message = su.ajouterUfr(a);
                }

                List<Ufr> ufrs = su.listeUfr();
                request.setAttribute("ufrs", ufrs);

                this.getServletContext().getRequestDispatcher("/WEB-INF/admin/ufr.jsp").forward(request, response);

            } else if (action.equals("modifier")) {

                String nomUfr = request.getParameter("nomUfr");

                String u = request.getParameter("idufr");

                int id = Integer.parseInt(u);

                if (!nomUfr.equals("")) {

                    Ufr a = new Ufr();
                    a.setIdUfr(id);
                    a.setNomUfr(nomUfr);

                    String message = su.modifierUfr(a);
                }

                List<Ufr> ufrs = su.listeUfr();
                request.setAttribute("ufrs", ufrs);

                this.getServletContext().getRequestDispatcher("/WEB-INF/admin/ufr.jsp").forward(request, response);

            }
        }

    }
}
