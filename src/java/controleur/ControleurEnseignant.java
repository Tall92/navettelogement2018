package controleur;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Departement;
import modele.Ufr;
import modele.Enseignant;
import service.ServiceDept;
import service.ServiceDeptImpl;
import service.ServiceUfr;
import service.ServiceUfrImpl;
import service.ServiceEnseignt;
import service.ServiceEnseignantImpl;

/**
 *
 * @author tall
 */
public class ControleurEnseignant extends HttpServlet {

    private ServiceEnseignt us;
    private ServiceDept sd;
    private ServiceUfr su;

    @Override
    public void init() throws ServletException {
        su = new ServiceUfrImpl();
        sd = new ServiceDeptImpl();
        us = new ServiceEnseignantImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {

            List<Ufr> ufrs = su.listeUfr();
            List<Departement> depts = sd.listeDep();
            List<Enseignant> users = us.listeUtilisateur();
            request.setAttribute("ufrs", ufrs);
            request.setAttribute("depts", depts);
            request.setAttribute("users", users);
            this.getServletContext().getRequestDispatcher("/WEB-INF/admin/enseignant.jsp").forward(request, response);
        } else if (action.equals("admin_modifier")) {

            String d = request.getParameter("iduser");

            int id = Integer.parseInt(d);

            Enseignant user = us.rechercher(id);
            List<Enseignant> users = us.listeUtilisateur();
            List<Ufr> ufrs = su.listeUfr();
            List<Departement> depts = sd.listeDep();
            
            request.setAttribute("ufrs", ufrs);
            request.setAttribute("depts", depts);
            request.setAttribute("user", user);
            request.setAttribute("users", users);
            request.setAttribute("objet", "modifier");
            
            this.getServletContext().getRequestDispatcher("/WEB-INF/admin/enseignant.jsp").forward(request, response);
        }
          else if (action.equals("admin_supprimer")) {

            String e = request.getParameter("idUser");

            int id = Integer.parseInt(e);

            su.supprimer(id);

            List<Ufr> ens = su.listeUfr();

            request.setAttribute("ufrs", ens);

            this.getServletContext().getRequestDispatcher("/WEB-INF/admin/enseignant.jsp").forward(request, response);
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
                String telephone = request.getParameter("tel");
                String login = request.getParameter("email");
                String ufr = request.getParameter("ufr");
                int idUfr = Integer.parseInt(ufr);
                String departement = request.getParameter("dept");
                int dept = Integer.parseInt(departement);
                String profil = request.getParameter("profil");

                if (!prenom.equals("") && !nom.equals("") && !adresse.equals("") && !telephone.equals("") && !login.equals("") && !ufr.equals("") && !departement.equals("") && !profil.equals("")) {
                    Enseignant u = new Enseignant();

                    u.setPrenom(prenom);
                    u.setNom(nom);
                    u.setAdresse(adresse);
                    u.setTel(telephone);
                    u.setLogin(login);
                    u.setUfr(new Ufr(idUfr, ""));
                    u.setDept(new Departement(dept, ""));
                    u.setProfil(profil);

                    us.ajouterUsers(u);

                }

                List<Enseignant> users = us.listeUtilisateur();
                List<Ufr> ufrs = su.listeUfr();
                List<Departement> depts = sd.listeDep();

                request.setAttribute("ufrs", ufrs);
                request.setAttribute("depts", depts);
                request.setAttribute("users", users);

                this.getServletContext().getRequestDispatcher("/WEB-INF/admin/enseignant.jsp").forward(request, response);
            }
        }
    }

}
