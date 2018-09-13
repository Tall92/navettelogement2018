package controleur;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Chambre;
import modele.Formation;
import modele.Navette;
import modele.ReserveLogement;
import modele.ReserveNavette;
import modele.Site;
import modele.Utilisateur;
import service.ServiceChambre;
import service.ServiceChambreImpl;
import service.ServiceForm;
import service.ServiceFormImpl;
import service.ServiceNavette;
import service.ServiceNavetteImpl;
import service.ServiceSite;
import service.ServiceSiteImpl;
import service.ServiceUtilisateur;
import service.ServiceUtilisateurImpl;

/**
 *
 * @author kane
 */
public class Responsable extends HttpServlet {

    private ServiceForm sf;
    private ServiceUtilisateur su;
    private ServiceNavette sn;
    private ServiceSite ss;
    private ServiceChambre sc;

    @Override
    public void init() throws ServletException {
        sf = new ServiceFormImpl();
        su = new ServiceUtilisateurImpl();
        sn = new ServiceNavetteImpl();
        ss = new ServiceSiteImpl();
        sc = new ServiceChambreImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            this.getServletContext().getRequestDispatcher("/WEB-INF/responsable/index.jsp").forward(request, response);
        } else if (action.equals("enseignant")) {
            String maformation = request.getParameter("formation");

            int f = Integer.parseInt(maformation);

            List<Utilisateur> enseignants = sf.listeEnseignantsForm(f);

            Formation formation = sf.rechercher(f);

            request.setAttribute("formation", formation);

            request.setAttribute("enseignants", enseignants);

            this.getServletContext().getRequestDispatcher("/WEB-INF/responsable/enseignant.jsp").forward(request, response);
        } else if (action.equals("reserver_navette")) {

            String iduser = request.getParameter("iduser");

            int id = Integer.parseInt(iduser);

            List<Navette> navettes = sn.listeNavettes();

            Utilisateur u = su.rechercher(id);

            request.setAttribute("ens", u);

            request.setAttribute("navs", navettes);

            this.getServletContext().getRequestDispatcher("/WEB-INF/responsable/reservernavette.jsp").forward(request, response);
        } else if (action.equals("reserver_logement")) {
            String iduser = request.getParameter("iduser");

            int id = Integer.parseInt(iduser);

            Utilisateur u = su.rechercher(id);

            List<Site> sites = ss.listeSite();

            request.setAttribute("sites", sites);

            request.setAttribute("ens", u);

            this.getServletContext().getRequestDispatcher("/WEB-INF/responsable/reserverlogement.jsp").forward(request, response);
        } else if (action.equals("res_navette")) {

            List<ReserveNavette> rns = sn.listeReserveNavettes();

            request.setAttribute("rns", rns);

            this.getServletContext().getRequestDispatcher("/WEB-INF/responsable/listereservenavette.jsp").forward(request, response);
        } else if (action.equals("res_logement")) {

            List<ReserveLogement> rnl = sc.listeReserveLogements();

            request.setAttribute("rnl", rnl);

            this.getServletContext().getRequestDispatcher("/WEB-INF/responsable/listereservelogement.jsp").forward(request, response);
        } else if (action.equals("annuler_res_nav")) {

            String iduser = request.getParameter("iduser");
            String idnav = request.getParameter("idnav");

            int idU = Integer.parseInt(iduser);

            int idN = Integer.parseInt(idnav);

            String nature = request.getParameter("nature");
            String date = request.getParameter("date");

            String message = sn.annulerReservation(idU, idN, nature, date);

            List<ReserveNavette> rns = sn.listeReserveNavettes();

            request.setAttribute("rns", rns);

            this.getServletContext().getRequestDispatcher("/WEB-INF/responsable/listereservenavette.jsp").forward(request, response);
        } else if (action.equals("annuler_res_log")) {

            String iduser = request.getParameter("iduser");
            String idcham = request.getParameter("idcham");

            int idU = Integer.parseInt(iduser);

            int idC = Integer.parseInt(idcham);

            String entree = request.getParameter("entree");
            String sortie = request.getParameter("sortie");

            String message = sc.annulerReservation(idU, idC, entree, sortie);
            
            List<ReserveLogement> rnl = sc.listeReserveLogements();

            request.setAttribute("rnl", rnl);

            this.getServletContext().getRequestDispatcher("/WEB-INF/responsable/listereservelogement.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");

        if (action != null) {

            if (action.equals("addplace")) {

                String date = request.getParameter("date");
                String nature = request.getParameter("nature");
                String navette = request.getParameter("navette");
                int idNav = Integer.parseInt(navette);
                String ens = request.getParameter("ens");
                int idUser = Integer.parseInt(ens);
                Date dn = null;

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                try {
                    dn = sdf.parse(date);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }

                ReserveNavette rn = new ReserveNavette();

                rn.setUtilisateur(new Utilisateur(idUser));
                rn.setNavette(new Navette(idNav));
                rn.setNature(nature);
                rn.setDate(dn);

                if (sn.verifierDispo(idNav, nature, date)) {
                    String message = sn.ajouterReservation(rn);

                    List<ReserveNavette> rns = sn.listeReserveNavettes();

                    request.setAttribute("rns", rns);

                    request.setAttribute("message", message);

                    this.getServletContext().getRequestDispatcher("/WEB-INF/responsable/listereservenavette.jsp").forward(request, response);
                } else {
                    request.setAttribute("message", "La navette est pleine");
                    this.getServletContext().getRequestDispatcher("/WEB-INF/responsable/reservernavette.jsp").forward(request, response);
                }

            } else if (action.equals("meschambres")) {
                PrintWriter out = response.getWriter();
                response.setContentType("text/plain");

                String site = request.getParameter("site");

                int idSite = Integer.parseInt(site);

                List<Chambre> chambres = sc.listeSiteChambres(idSite);

                String texte = "<option></option>";

                for (Chambre c : chambres) {
                    texte += "<option value=\"" + c.getIdCh() + "\">" + c.getNumero() + "</option>";
                }

                out.print(texte);
            } else if (action.equals("addloge")) {
                String ens = request.getParameter("ens");
                int idUser = Integer.parseInt(ens);
                String chambre = request.getParameter("chambre");
                int idCha = Integer.parseInt(chambre);
                String entree = request.getParameter("entree");
                String sortie = request.getParameter("sortie");
                Date de = null;
                Date ds = null;

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                try {
                    de = sdf.parse(entree);
                    ds = sdf.parse(sortie);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }

                ReserveLogement rl = new ReserveLogement(new Utilisateur(idUser), new Chambre(idCha), de, ds);

                String message = sc.ajouterReservation(rl);

                List<ReserveLogement> rnl = sc.listeReserveLogements();

                request.setAttribute("rnl", rnl);

                request.setAttribute("message", message);

                this.getServletContext().getRequestDispatcher("/WEB-INF/responsable/listereservelogement.jsp").forward(request, response);

            }
        }

    }
}
