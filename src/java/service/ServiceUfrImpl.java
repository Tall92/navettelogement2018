package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modele.Ufr;

/**
 *
 * @author tall
 */
public class ServiceUfrImpl implements ServiceUfr {

    private static final String SQL_UFR = "SELECT * FROM `ufr`";
    private static final String SQL_ADDUFR = "INSERT INTO `ufr`(`NOM_UFR`) VALUES (?)";
    private static final String SQL_FIND_UFR = "SELECT * FROM `ufr` WHERE `ID_UFR`=?";
    private static final String SQL_MOD_UFR = "UPDATE `ufr` SET `NOM_UFR`=? WHERE `ID_UFR`=?";
    private static final String SQL_DEL = "DELETE FROM `ufr` WHERE `ID_UFR` = ?";

    @Override
    public String ajouterUfr(Ufr ufr) {
        Connection db = null;
        PreparedStatement ps = null;
        String message = null;
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_ADDUFR);
            ps.setString(1, ufr.getNomUfr());

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
    public List<Ufr> listeUfr() {
        Connection db = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Ufr> ufrs = new ArrayList<>();
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_UFR);
            rs = ps.executeQuery();
            while (rs.next()) {
                Ufr a = new Ufr();
                a.setIdUfr(rs.getInt("ID_UFR"));
                a.setNomUfr(rs.getString("NOM_UFR"));
                ufrs.add(a);
            }

        } catch (SQLException e) {
        }
        return ufrs;
    }

    @Override
    public Ufr rechercherUfr(int idufr) {
        Connection db = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Ufr ufr = null;
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_FIND_UFR);
            ps.setInt(1, idufr);
            rs = ps.executeQuery();
            if (rs.next()) {
                ufr = new Ufr();
                ufr.setIdUfr(rs.getInt("ID_UFR"));
                ufr.setNomUfr(rs.getString("NOM_UFR"));

            }

        } catch (SQLException e) {
        }
        return ufr;

    }

    @Override
    public String modifierUfr(Ufr ufr) {
        Connection db = null;
        PreparedStatement ps = null;
        String message = null;
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_MOD_UFR);
            ps.setString(1, ufr.getNomUfr());
            ps.setInt(2, ufr.getIdUfr());
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
    
    

}
