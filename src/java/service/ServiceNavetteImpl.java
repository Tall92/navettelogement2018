
package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modele.Navette;

/**
 *
 * @author tall
 */
public class ServiceNavetteImpl implements ServiceNavette {
    
    private static final String SQL_ADD_NAV = "INSERT INTO `navette`(`MATRICULE`, `NB_PLACE`) VALUES (?, ?)";
    private static final String SQL_SEL = "SELECT * FROM `navette`";
    private static final String SQL_MOD_NAV = "UPDATE `navette` SET `MATRICULE`=? WHERE `ID_NAVETTE`=?";
     private static final String SQL_FIND_NAV = "SELECT * FROM `navette` WHERE `ID_NAVETTE`=?";

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

            }

        } catch (SQLException e) {
        }
        return nav;
    }
    
}
