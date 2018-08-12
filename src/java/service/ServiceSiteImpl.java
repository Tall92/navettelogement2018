package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modele.Gestionnaire;
import modele.Site;

/**
 *
 * @author tall
 */
public class ServiceSiteImpl implements ServiceSite {

    private static final String SQL_ADD_SITE = "INSERT INTO `site`( `ID_GEST`, `NOM_SITE`) VALUES (?,?)";
    private static final String SQL_LIST_SITE = "SELECT * FROM `site` s, `gestionnaire` g WHERE s.`ID_GEST` = g.`ID_GEST`";
    private static final String SQL_MOD_SITE = "UPDATE `site` SET `ID_GEST`=?,`NOM_SITE`=? WHERE `ID_SITE`=?";
    private static final String SQL_FIND_SITE = "SELECT * FROM `site` s, `gestionnaire` g WHERE s.`ID_GEST` = g.`ID_GEST` AND s.`ID_SITE`=?";

    @Override
    public String ajouterSite(Site sit) {
        Connection db = null;
        PreparedStatement ps = null;
        String message = null;
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_ADD_SITE);
            ps.setInt(1, sit.getGes().getGes());
            ps.setString(2, sit.getNomSite());

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
    public List<Site> listeSite() {
        Connection db = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Site> depts = new ArrayList<>();
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_LIST_SITE);
            rs = ps.executeQuery();
            while (rs.next()) {
                Site s = new Site();
                s.setSit(rs.getInt("ID_SITE"));
                s.setGes(new Gestionnaire(rs.getInt("ID_GEST"), rs.getString("PRENOM"), rs.getString("NOM"), rs.getString("TELEPHONE"), rs.getString("ADRESSE")));
                s.setNomSite(rs.getString("NOM_SITE"));
                depts.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return depts;
    }

    @Override
    public String modifierSite(Site site) {
        Connection db = null;
        PreparedStatement ps = null;
        String message = null;
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_MOD_SITE);
            ps.setInt(1, site.getGes().getGes());
            ps.setString(2, site.getNomSite());
            ps.setInt(3, site.getSit());

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
    public Site rechercherSite(int sit) {
        Connection db = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Site site = null;
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_FIND_SITE);
            ps.setInt(1, sit);
            rs = ps.executeQuery();
            if (rs.next()) {
                site = new Site();
                site.setSit(rs.getInt("ID_SITE"));
                site.setNomSite(rs.getString("NOM_SITE"));
                site.setGes(new Gestionnaire(rs.getInt("ID_GEST"), rs.getString("PRENOM"), rs.getString("NOM"), rs.getString("TELEPHONE"), rs.getString("ADRESSE")));

            }

        } catch (SQLException e) {
        }
        return site;

    }

}
