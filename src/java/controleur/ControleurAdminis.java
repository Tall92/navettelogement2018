package controleur;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.Administrateur;
import service.ServiceAdminis;
import service.ServiceAdminisImpl;

public class ControleurAdminis extends HttpServlet {

    private ServiceAdminis  sa;

    @Override
    public void init() throws ServletException {
        sa = new ServiceAdminisImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            this.getServletContext().getRequestDispatcher("/WEB-INF/admin/login.jsp").forward(request, response);
        } else if (action.equals("admin")) {

            List<Administrateur> administrateurs = sa.listeAdministrateur();

            request.setAttribute("administrateurs", administrateurs);

            this.getServletContext().getRequestDispatcher("/WEB-INF/admin/administrateur.jsp").forward(request, response);
        } else if (action.equals("admin_modifier")) {
            
            String user = request.getParameter("iduser");
            
            int id = Integer.parseInt(user);
            
            Administrateur a = sa.rechercher(id);
            
            List<Administrateur> administrateurs = sa.listeAdministrateur();

            request.setAttribute("administrateurs", administrateurs);
            
            request.setAttribute("objet", "modifier");
            
            request.setAttribute("admin", a);

            this.getServletContext().getRequestDispatcher("/WEB-INF/admin/administrateur.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        

        if (action != null) {
            
            if (action.equals("Connexion")) {
                String login = request.getParameter("email");
                String password = request.getParameter("motdepasse");

                Administrateur a = sa.connexion(login, password);

                if (a == null) {
                    this.getServletContext().getRequestDispatcher("/WEB-INF/admin/login.jsp").forward(request, response);

                } else {
                    HttpSession session = request.getSession();
                    session.setAttribute("admin", a);
                    this.getServletContext().getRequestDispatcher("/WEB-INF/admin/administrateur.jsp").forward(request, response);
                    //response.sendRedirect("WEB-INF/admin/administrateur.jsp");
                }
            } else if (action.equals("ajouter")) {
                
                String prenom = request.getParameter("prenom");
                String nom = request.getParameter("nom");
                String telephone = request.getParameter("telephone");
                String email = request.getParameter("email");

                if (!prenom.equals("") && !nom.equals("") && !telephone.equals("") && !email.equals("")) {
                    
                    Administrateur a = new Administrateur();
                    a.setPrenom(prenom);
                    a.setNom(nom);
                    a.setTelephone(telephone);
                    a.setLogin(email);

                    String message = sa.ajouterAdmin(a);

                    List<Administrateur> administrateurs = sa.listeAdministrateur();

                    request.setAttribute("message", message);
                    request.setAttribute("administrateurs", administrateurs);
                    this.getServletContext().getRequestDispatcher("/WEB-INF/admin/administrateur.jsp").forward(request, response);

                }
            } else if (action.equals("modifier")) {
                
                String prenom = request.getParameter("prenom");
                String nom = request.getParameter("nom");
                String telephone = request.getParameter("telephone");
                String email = request.getParameter("email");
                 String user = request.getParameter("iduser");
            
            int id = Integer.parseInt(user);

                if (!prenom.equals("") && !nom.equals("") && !telephone.equals("") && !email.equals("")) {
                    
                    Administrateur a = new Administrateur();
                    a.setIdUser(id);
                    a.setPrenom(prenom);
                    a.setNom(nom);
                    a.setTelephone(telephone);
                    a.setLogin(email);

                    String message = sa.modifier(a);

                    List<Administrateur> administrateurs = sa.listeAdministrateur();

                    request.setAttribute("message", message);
                    request.setAttribute("administrateurs", administrateurs);
                    this.getServletContext().getRequestDispatcher("/WEB-INF/admin/administrateur.jsp").forward(request, response);

                }
            }
        }
    }

}
