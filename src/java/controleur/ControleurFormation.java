package controleur;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Departement;
import modele.Formation;
import modele.Utilisateur;
import service.ServiceDept;
import service.ServiceDeptImpl;
import service.ServiceForm;
import service.ServiceFormImpl;

/**
 *
 * @author tall
 */
public class ControleurFormation extends HttpServlet {

    private ServiceForm sf;
    private ServiceDept sd;

    @Override
    public void init() throws ServletException {
        sd = new ServiceDeptImpl();
        sf = new ServiceFormImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            List<Departement> depts = sd.listeDep();

            List<Formation> forms = sf.listesFormation();
            request.setAttribute("depts", depts);
            request.setAttribute("forms", forms);
            this.getServletContext().getRequestDispatcher("/WEB-INF/admin/formation.jsp").forward(request, response);
        } else if (action.equals("admin_modifier")) {

            String d = request.getParameter("idform");

            int id = Integer.parseInt(d);

            Formation form = sf.rechercher(id);

            List<Formation> forms = sf.listesFormation();
            List<Departement> depts = sd.listeDep();

            request.setAttribute("depts", depts);
            request.setAttribute("forms", forms);

            request.setAttribute("form", form);

            request.setAttribute("objet", "modifier");

            this.getServletContext().getRequestDispatcher("/WEB-INF/admin/formation.jsp").forward(request, response);
        } else if (action.equals("admin_supprimer")) {

            String d = request.getParameter("idform");

            int id = Integer.parseInt(d);

            String message = sf.supprimer(id);

            List<Formation> forms = sf.listesFormation();
            List<Departement> depts = sd.listeDep();

            request.setAttribute("depts", depts);
            request.setAttribute("forms", forms);

            this.getServletContext().getRequestDispatcher("/WEB-INF/admin/formation.jsp").forward(request, response);
        } else if (action.equals("admin_forma")) {
            String d = request.getParameter("idform");

            int id = Integer.parseInt(d);

            List<Utilisateur> enseignants = sf.listeEnseignants(id);

            List<Utilisateur> listenotin = sf.listeNotInForm(id);

            Formation f = sf.rechercher(id);

            request.setAttribute("formation", f);

            request.setAttribute("enseignants", enseignants);

            request.setAttribute("listenotin", listenotin);

            this.getServletContext().getRequestDispatcher("/WEB-INF/admin/listeenseignant.jsp").forward(request, response);

        } else if (action.equals("admin_attribuer")) {
            String d = request.getParameter("idform");

            int id = Integer.parseInt(d);
            
            String u = request.getParameter("iduser");

            int iduser = Integer.parseInt(u);
            
            sf.attribuer(iduser, id);

            List<Utilisateur> enseignants = sf.listeEnseignants(id);

            List<Utilisateur> listenotin = sf.listeNotInForm(id);

            Formation f = sf.rechercher(id);

            request.setAttribute("formation", f);

            request.setAttribute("enseignants", enseignants);

            request.setAttribute("listenotin", listenotin);

            this.getServletContext().getRequestDispatcher("/WEB-INF/admin/listeenseignant.jsp").forward(request, response);

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");

        if (action != null) {

            if (action.equals("ajouter")) {

                String dept = request.getParameter("dept");

                String nomForm = request.getParameter("nomForm");

                int id = Integer.parseInt(dept);

                if (!nomForm.equals("") && !dept.equals("")) {

                    Formation f = new Formation();
                    f.setNomForm(nomForm);
                    f.setDept(new Departement(id, ""));

                    sf.ajouterFormation(f);
                }

                List<Departement> depts = sd.listeDep();

                List<Formation> forms = sf.listesFormation();
                request.setAttribute("depts", depts);
                request.setAttribute("forms", forms);

                this.getServletContext().getRequestDispatcher("/WEB-INF/admin/formation.jsp").forward(request, response);

            } else if (action.equals("modifier")) {

                String idform = request.getParameter("idform");

                int idf = Integer.parseInt(idform);

                String dept = request.getParameter("dept");

                String nomForm = request.getParameter("nomForm");

                int id = Integer.parseInt(dept);

                if (!nomForm.equals("") && !dept.equals("")) {

                    Formation f = new Formation();
                    f.setIdForm(idf);
                    f.setNomForm(nomForm);
                    f.setDept(new Departement(id, ""));

                    sf.modifierFormation(f);
                }

                List<Departement> depts = sd.listeDep();

                List<Formation> forms = sf.listesFormation();
                request.setAttribute("depts", depts);
                request.setAttribute("forms", forms);

                this.getServletContext().getRequestDispatcher("/WEB-INF/admin/formation.jsp").forward(request, response);
            } else if (action.equals("admin_addtoform")) {
                String idform = request.getParameter("idform");

                int idf = Integer.parseInt(idform);

                String[] iduser = request.getParameterValues("professeur");
                
                
                if (iduser != null) {
                    for (String id : iduser) {
                        int a = Integer.parseInt(id);

                        sf.addToForm(a, idf);
                    }
                }

                List<Utilisateur> enseignants = sf.listeEnseignants(idf);

                List<Utilisateur> listenotin = sf.listeNotInForm(idf);

                Formation f = sf.rechercher(idf);

                request.setAttribute("formation", f);

                request.setAttribute("enseignants", enseignants);

                request.setAttribute("listenotin", listenotin);

                this.getServletContext().getRequestDispatcher("/WEB-INF/admin/listeenseignant.jsp").forward(request, response);
            }
        }
    }
}
