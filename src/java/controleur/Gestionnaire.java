package controleur;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.GestionnaireUfr;
import modele.ReserveLogement;
import service.ServiceChambre;
import service.ServiceChambreImpl;

/**
 *
 * @author kane
 */
public class Gestionnaire extends HttpServlet {
    
    private ServiceChambre sc;

    @Override
    public void init() throws ServletException {
        sc = new ServiceChambreImpl();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            this.getServletContext().getRequestDispatcher("/WEB-INF/gestionufr/index.jsp").forward(request, response);
        } else if (action.equals("reservation")) {
            HttpSession session = request.getSession();
            GestionnaireUfr gu = (GestionnaireUfr) session.getAttribute("ses_ges");
            
            int idUfr = gu.getUfr().getIdUfr();
            
            List<ReserveLogement> reservations = sc.listeReserveLogementsUfr(idUfr);
            
            request.setAttribute("reservations", reservations);
            
            this.getServletContext().getRequestDispatcher("/WEB-INF/gestionufr/reservation.jsp").forward(request, response);
        } else if (action.equals("valider")) {
            
            String iduser = request.getParameter("iduser");
            String idcham = request.getParameter("idcham");

            int idU = Integer.parseInt(iduser);

            int idC = Integer.parseInt(idcham);

            String entree = request.getParameter("entree");
            String sortie = request.getParameter("sortie");
            
            String message = sc.validerReservation(idU, idC, entree, sortie);
            
            HttpSession session = request.getSession();
            GestionnaireUfr gu = (GestionnaireUfr) session.getAttribute("ses_ges");
            
            int idUfr = gu.getUfr().getIdUfr();
            
            List<ReserveLogement> reservations = sc.listeReserveLogementsUfr(idUfr);
            
            request.setAttribute("reservations", reservations);
            
            this.getServletContext().getRequestDispatcher("/WEB-INF/gestionufr/reservation.jsp").forward(request, response);
        }  else if (action.equals("annuler_log")) {
            
            String iduser = request.getParameter("iduser");
            String idcham = request.getParameter("idcham");

            int idU = Integer.parseInt(iduser);

            int idC = Integer.parseInt(idcham);

            String entree = request.getParameter("entree");
            String sortie = request.getParameter("sortie");
            
            String message = sc.annulerReservation(idU, idC, entree, sortie);
            
            HttpSession session = request.getSession();
            GestionnaireUfr gu = (GestionnaireUfr) session.getAttribute("ses_ges");
            
            int idUfr = gu.getUfr().getIdUfr();
            
            List<ReserveLogement> reservations = sc.listeReserveLogementsUfr(idUfr);
            
            request.setAttribute("reservations", reservations);
            
            this.getServletContext().getRequestDispatcher("/WEB-INF/gestionufr/reservation.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

}
