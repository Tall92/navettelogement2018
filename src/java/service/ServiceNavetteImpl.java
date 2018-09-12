package service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modele.Navette;
import modele.ReserveNavette;
import modele.Utilisateur;

/**
 *
 * @author tall
 */
public class ServiceNavetteImpl implements ServiceNavette {

    private static final String SQL_ADD_NAV = "INSERT INTO `navette`(`MATRICULE`, `NB_PLACE`) VALUES (?, ?)";
    private static final String SQL_SEL = "SELECT * FROM `navette`";
    private static final String SQL_MOD_NAV = "UPDATE `navette` SET `MATRICULE`=?, `NB_PLACE` =? WHERE `ID_NAVETTE`=?";
    private static final String SQL_FIND_NAV = "SELECT * FROM `navette` WHERE `ID_NAVETTE`=?";
    private static final String SQL_DEL = "DELETE FROM `navette` WHERE `ID_NAVETTE` = ?";

    // Réservation Navette
    private static final String SQL_ADD_RES = "INSERT INTO `participer`(`ID_USER`, `ID_NAVETTE`, `NATURE`, `DATE`) VALUES (?, ?, ?, ?)";
    private static final String SQL_RES_NAV = "SELECT * FROM `participer` p INNER JOIN `utilisateur` u ON p.`ID_USER` = u.`ID_USER` INNER JOIN `navette` n ON p.`ID_NAVETTE`= n.`ID_NAVETTE`";

    @Override
    public String ajouterNav(Navette n) {
        Connection db = null;
        PreparedStatement ps = null;
        String message = null;
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_ADD_NAV);
            ps.setString(1, n.getMatricule());
            ps.setInt(2, n.getNbPlace());

            int statut = ps.executeUpdate();

            if (statut == 1) {
                message = "reussi";
            } else {
                message = "echec";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return message;
    }

    @Override
    public List<Navette> listeNavettes() {
        Connection db = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Navette> navettes = new ArrayList<>();
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_SEL);
            rs = ps.executeQuery();
            while (rs.next()) {
                Navette n = new Navette();
                n.setIdNav(rs.getInt("ID_NAVETTE"));
                n.setMatricule(rs.getString("MATRICULE"));
                n.setNbPlace(rs.getInt("NB_PLACE"));

                navettes.add(n);
            }

        } catch (SQLException e) {
        }
        return navettes;
    }

    @Override
    public String modifierNavette(Navette n) {
        Connection db = null;
        PreparedStatement ps = null;
        String message = null;
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_MOD_NAV);
            ps.setString(1, n.getMatricule());
            ps.setInt(2, n.getNbPlace());
            ps.setInt(3, n.getIdNav());
            int statut = ps.executeUpdate();

            if (statut == 1) {
                message = "réussi";
            } else {
                message = "echec";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return message;
    }

    @Override
    public Navette rechercherNavette(int idNav) {
        Connection db = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Navette nav = null;
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_FIND_NAV);
            ps.setInt(1, idNav);
            rs = ps.executeQuery();
            if (rs.next()) {
                nav = new Navette();
                nav.setIdNav(rs.getInt("ID_NAVETTE"));
                nav.setMatricule(rs.getString("MATRICULE"));
                nav.setNbPlace(rs.getInt("NB_PLACE"));
            }

        } catch (SQLException e) {
        }
        return nav;
    }

    @Override
    public String supprimer(int id) {
        Connection db = null;
        PreparedStatement ps = null;
        String message = null;
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_DEL);
            ps.setInt(1, id);

            int statut = ps.executeUpdate();

            if (statut == 1) {
                message = "reussi";
            } else {
                message = "echec";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return message;
    }

    @Override
    public String ajouterReservation(ReserveNavette rn) {
        Connection db = null;
        PreparedStatement ps = null;
        String message = null;
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_ADD_RES);
            ps.setInt(1, rn.getUtilisateur().getIdUser());
            ps.setInt(2, rn.getNavette().getIdNav());
            ps.setString(3, rn.getNature());
            ps.setDate(4, new java.sql.Date(rn.getDate().getTime()));

            if (!dejaFait(rn.getUtilisateur().getIdUser(), rn.getNavette().getIdNav(), rn.getNature(), new java.sql.Date(rn.getDate().getTime()))) {
                int statut = ps.executeUpdate();

                if (statut == 1) {
                    message = "reussi";
                } else {
                    message = "echec";
                }
            } else {
                message = "Réservation déja effectuée";
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return message;
    }

    @Override
    public boolean verifierDispo(int idNav, String nature, String date) {
        Navette n = this.rechercherNavette(idNav);

        int nbre = this.nombreRes(idNav, nature, date);

        if (nbre < n.getNbPlace()) {
            return true;
        } else {
            return false;
        }

    }

    private int nombreRes(int idNav, String nature, String date) {
        Connection db = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int nbre = 0;
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement("SELECT * FROM `participer` WHERE `ID_NAVETTE` = ? AND `NATURE` = ? AND `DATE` = ?");
            ps.setInt(1, idNav);
            ps.setString(2, nature);
            ps.setString(3, date);
            rs = ps.executeQuery();

            while (rs.next()) {
                nbre++;
            }

        } catch (SQLException e) {
        }
        return nbre;
    }

    private boolean dejaFait(int idUser, int idNav, String nature, Date date) {
        Connection db = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        boolean rep = false;
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement("SELECT * FROM `participer` WHERE `ID_USER` = ? AND `ID_NAVETTE` = ? AND `NATURE` = ? AND `DATE` = ?");
            ps.setInt(1, idUser);
            ps.setInt(2, idNav);
            ps.setString(3, nature);
            ps.setDate(4, date);
            rs = ps.executeQuery();

            if (rs.next()) {
                rep = true;
            }

        } catch (SQLException e) {
        }
        return rep;
    }
    
    @Override
    public List<ReserveNavette> listeReserveNavettes() {
        Connection db = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ReserveNavette> rns = new ArrayList<>();
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_RES_NAV);
            rs = ps.executeQuery();
            while (rs.next()) {
                ReserveNavette rn = new ReserveNavette();
                
                rn.setUtilisateur(new Utilisateur(rs.getInt("ID_USER"), rs.getString("PRENOM"), rs.getString("NOM"), rs.getString("ADRESSE"), rs.getString("TELEPHONE"), rs.getString("LOGIN")));
                rn.setNavette(new Navette(rs.getInt("ID_NAVETTE"), rs.getString("MATRICULE"), rs.getInt("NB_PLACE")));
                rn.setNature(rs.getString("NATURE"));
                rn.setDate(new java.util.Date(rs.getDate("DATE").getTime()));
                rns.add(rn);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rns;
    }

    @Override
    public String annulerReservation(int idUser, int idNav, String nature, String date) {
        Connection db = null;
        PreparedStatement ps = null;
        String message = null;
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement("DELETE FROM `participer` WHERE `ID_USER` = ? AND `ID_NAVETTE` = ? AND `NATURE` = ? AND `DATE` = ?");
            ps.setInt(1, idUser);
            ps.setInt(2, idNav);
            ps.setString(3, nature);
            ps.setString(4, date);
            int statut = ps.executeUpdate();

            if (statut == 1) {
                message = "reussi";
            } else {
                message = "echec";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return message;
    }

    
}
