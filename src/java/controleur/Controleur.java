package controleur;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.Formation;
import modele.Utilisateur;
import service.ServiceForm;
import service.ServiceFormImpl;
import service.ServiceUtilisateur;
import service.ServiceUtilisateurImpl;

/**
 *
 * @author tall
 */
public class Controleur extends HttpServlet {

    private ServiceUtilisateur su;
    private ServiceForm sf;
    
    @Override
    public void init() throws ServletException {
        su = new ServiceUtilisateurImpl();
        sf = new ServiceFormImpl();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        } else if (action.equals("logout")) {
            
            HttpSession session = request.getSession();
            
            session.removeAttribute("ses_user");
            
            session.invalidate();

            this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        String action = request.getParameter("action");
        
        if (action != null) {
            
            if (action.equals("Connexion")) {
                String login = request.getParameter("email");
                String password = request.getParameter("motdepasse");
                
                Utilisateur u = su.connexion(login, password);
                
                if (u == null) {
                    String message = "Login et/ou mot de passe incorrect ou compte inactif";
                    request.setAttribute("message", message);
                    this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                } else {
                    String profil = u.getProfil().toLowerCase();
                    
                    HttpSession session = request.getSession();
                    
                    session.setAttribute("ses_user", u);
                     
                    if (profil.equals("responsable")) {
                        
                        List<Formation> formations = sf.mesFormations(u.getIdUser());
                        
                        session.setAttribute("mesformations", formations);
                        
                        this.getServletContext().getRequestDispatcher("/WEB-INF/responsable/index.jsp").forward(request, response);
                    } else if (profil.equals("chef_de_departement")) {
                        this.getServletContext().getRequestDispatcher("/WEB-INF/chef/index.jsp").forward(request, response);
                    } else if (profil.equals("directeur_ufr")) {
                        this.getServletContext().getRequestDispatcher("/WEB-INF/directeur/index.jsp").forward(request, response);
                    }
                }
            }
        }

    }

}
