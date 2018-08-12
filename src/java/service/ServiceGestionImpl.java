package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modele.Gestionnaire;

/**
 *
 * @author tall
 */
public class ServiceGestionImpl implements ServiceGestionnaire {

    private static final String SQL_ADD_GES = "INSERT INTO `gestionnaire`( `PRENOM`, `NOM`, `ADRESSE`, `TELEPHONE`) VALUES (?, ?, ?, ?)";
    private static final String SQL_MOD_GES = "UPDATE `gestionnaire` SET `PRENOM`=?,`NOM`=?,`ADRESSE`? WHERE `ID_GEST`=?";
    private static final String SQL_LIST_GES = "SELECT * FROM `gestionnaire` ";
    private static final String SQL_FIND_GES = "SELECT * FROM `gestionnaire` WHERE `ID_GEST` = ?";
    Gestionnaire g = new Gestionnaire();

    @Override
    public String ajouterGes(Gestionnaire gess) {
        Connection db = null;
        PreparedStatement ps = null;
        String message = null;
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_ADD_GES);
            ps.setString(1, gess.getPrenom());
            ps.setString(2, gess.getNom());
            ps.setString(4, gess.getAdr());
            ps.setString(3, gess.getTel());

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
    public String modifierGes(Gestionnaire gess) {
        Connection db = null;
        PreparedStatement ps = null;
        String message = null;
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_MOD_GES);
            ps.setString(1, gess.getPrenom());
            ps.setString(2, gess.getNom());
            ps.setString(3, gess.getTel());
            ps.setString(4, gess.getAdr());
            ps.setInt(5, gess.getGes());

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
    public List<Gestionnaire> listeGestion() {

        Connection db = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Gestionnaire> gestion = new ArrayList<>();
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_LIST_GES);
            rs = ps.executeQuery();
            while (rs.next()) {
                Gestionnaire gest = new Gestionnaire();
                gest.setGes(rs.getInt("ID_GEST"));
                gest.setPrenom(rs.getString("PRENOM"));
                gest.setNom(rs.getString("NOM"));
                gest.setAdr(rs.getString("ADRESSE"));
                gest.setTel(rs.getString("TELEPHONE"));

                gestion.add(gest);
            }

        } catch (SQLException e) {
        }
        return gestion;
    }

    @Override
    public Gestionnaire rechercherGestion(int ges) {
        Connection db = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Gestionnaire gestion = null;
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_FIND_GES);
            ps.setInt(1, ges);
            // excution de la requete
            rs = ps.executeQuery();
            if (rs.next()) {
                gestion = new Gestionnaire();
                gestion.setGes(rs.getInt("ID_GEST"));
                gestion.setPrenom(rs.getString("PRENOM"));
                gestion.setNom(rs.getString("NOM"));
                gestion.setAdr(rs.getString("ADRESSE"));
                gestion.setTel(rs.getString("TELEPHONE"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gestion;
    }

}


