package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modele.Chambre;
import modele.ReserveLogement;
import modele.Site;
import modele.Utilisateur;

/**
 *
 * @author tall
 */
public class ServiceChambreImpl implements ServiceChambre {

    private static final String SQL_ADD_CHAM = "INSERT INTO `chambre`( `ID_SITE`, `NUMERO`) VALUES (?, ?)";
    private static final String SQL_LIST_CHAM = "SELECT * FROM `chambre` c, `site` s WHERE c.`ID_SITE` = s.`ID_SITE`";
    private static final String SQL_LIST_SITE_CHAM = "SELECT * FROM `chambre` c, `site` s WHERE c.`ID_SITE` = s.`ID_SITE` AND s.`ID_SITE` = ?";
    private static final String SQL_MOD_CHAM = "UPDATE `chambre` SET `ID_SITE`=?,`NUMERO`=? WHERE `ID_CHAMBRE`=?";
    private static final String SQL_FIND_CHAM = "SELECT * FROM `chambre` c, `site` s WHERE c.`ID_SITE` = s.`ID_SITE` AND `ID_CHAMBRE` = ?";
    private static final String SQL_DEL_CHAM = "DELETE FROM `chambre` WHERE `ID_CHAMBRE`=?";

    // Reservation Logement
    private static final String SQL_ADD_RES = "INSERT INTO `loger`(`ID_USER`, `ID_CHAMBRE`, `DATE_ENTREE`, `DATE_SORTIE`) VALUES (?, ?, ?, ?)";
    private static final String SQL_RES_LOG = "SELECT * FROM `loger` l, `utilisateur` u, `chambre` c, `site` s  WHERE l.`ID_USER` = u.`ID_USER` AND l.`ID_CHAMBRE` = c.`ID_CHAMBRE`AND c.`ID_SITE` = s.`ID_SITE`";
    private static final String SQL_RES_LOG_ETAT = "SELECT * FROM `loger` l, `utilisateur` u, `ufr`, `chambre` c, `site` s WHERE l.`ID_USER` = u.`ID_USER` AND u.`ID_UFR` = ufr.`ID_UFR` AND l.`ID_CHAMBRE` = c.`ID_CHAMBRE`AND c.`ID_SITE` = s.`ID_SITE` AND ufr.`ID_UFR` = ?";
    private static final String SQL_RES_ANN = "DELETE FROM `loger` WHERE `ID_USER` = ? AND `ID_CHAMBRE` = ? AND `DATE_ENTREE` = ? AND `DATE_SORTIE` = ?";
    private static final String SQL_RES_VAL = "UPDATE `loger` SET `ETAT` = 1 WHERE `ID_USER` = ? AND `ID_CHAMBRE` = ? AND `DATE_ENTREE` = ? AND `DATE_SORTIE` = ?";

    @Override
    public String ajouter(Chambre c) {
        Connection db = null;
        PreparedStatement ps = null;
        String message = null;
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_ADD_CHAM);
            ps.setInt(1, c.getSite().getSit());
            ps.setString(2, c.getNumero());

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
    public List<Chambre> listeChambres() {
        Connection db = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Chambre> chambres = new ArrayList<>();
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_LIST_CHAM);
            rs = ps.executeQuery();
            while (rs.next()) {
                Chambre c = new Chambre();
                c.setIdCh(rs.getInt("ID_CHAMBRE"));
                c.setSite(new Site(rs.getInt("ID_SITE"), rs.getString("NOM_SITE")));
                c.setNumero(rs.getString("NUMERO"));

                chambres.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chambres;
    }

    @Override
    public String modifierChambre(Chambre cha) {
        Connection db = null;
        PreparedStatement ps = null;
        String message = null;
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_MOD_CHAM);
            ps.setInt(1, cha.getSite().getSit());
            ps.setString(2, cha.getNumero());
            ps.setInt(3, cha.getIdCh());

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
    public Chambre rechercher(int id) {
        Connection db = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Chambre c = null;
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_FIND_CHAM);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                c = new Chambre();
                c.setIdCh(rs.getInt("ID_CHAMBRE"));
                c.setSite(new Site(rs.getInt("ID_SITE"), rs.getString("NOM_SITE")));
                c.setNumero(rs.getString("NUMERO"));
            }

        } catch (SQLException e) {
        }
        return c;
    }

    @Override
    public String supprimer(int id) {

        Connection db = null;
        PreparedStatement ps = null;
        String message = null;
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_DEL_CHAM);
            ps.setInt(1, id);

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
    public List<Chambre> listeSiteChambres(int idSite) {
        Connection db = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Chambre> chambres = new ArrayList<>();
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_LIST_SITE_CHAM);
            ps.setInt(1, idSite);
            rs = ps.executeQuery();
            while (rs.next()) {
                Chambre c = new Chambre();
                c.setIdCh(rs.getInt("ID_CHAMBRE"));
                c.setSite(new Site(rs.getInt("ID_SITE"), rs.getString("NOM_SITE")));
                c.setNumero(rs.getString("NUMERO"));

                chambres.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chambres;
    }

    @Override
    public String ajouterReservation(ReserveLogement rl) {
        Connection db = null;
        PreparedStatement ps = null;
        String message = null;
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_ADD_RES);

            ps.setInt(1, rl.getUtilisateur().getIdUser());
            ps.setInt(2, rl.getChambre().getIdCh());
            ps.setDate(3, new java.sql.Date(rl.getEntree().getTime()));
            ps.setDate(4, new java.sql.Date(rl.getSortie().getTime()));

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
    public List<ReserveLogement> listeReserveLogements() {
        Connection db = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<ReserveLogement> reserveLogements = new ArrayList<>();
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_RES_LOG);

            rs = ps.executeQuery();
            while (rs.next()) {
                ReserveLogement rl = new ReserveLogement();

                rl.setUtilisateur(new Utilisateur(rs.getInt("ID_USER"), rs.getString("PRENOM"), rs.getString("NOM"), rs.getString("ADRESSE"), rs.getString("TELEPHONE"), rs.getString("LOGIN")));
                rl.setChambre(new Chambre(rs.getInt("ID_CHAMBRE"), new Site(rs.getInt("ID_SITE"), rs.getString("NOM_SITE")), rs.getString("NUMERO")));
                rl.setEntree(new java.util.Date(rs.getDate("DATE_ENTREE").getTime()));
                rl.setSortie(new java.util.Date(rs.getDate("DATE_SORTIE").getTime()));
                rl.setEtat(rs.getInt("ETAT"));

                reserveLogements.add(rl);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reserveLogements;
    }

    @Override
    public List<ReserveLogement> listeReserveLogementsUfr(int idUfr) {
        Connection db = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<ReserveLogement> reserveLogements = new ArrayList<>();
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_RES_LOG_ETAT);
            ps.setInt(1, idUfr);
            rs = ps.executeQuery();
            while (rs.next()) {
                ReserveLogement rl = new ReserveLogement();

                rl.setUtilisateur(new Utilisateur(rs.getInt("ID_USER"), rs.getString("PRENOM"), rs.getString("NOM"), rs.getString("ADRESSE"), rs.getString("TELEPHONE"), rs.getString("LOGIN")));
                rl.setChambre(new Chambre(rs.getInt("ID_CHAMBRE"), new Site(rs.getInt("ID_SITE"), rs.getString("NOM_SITE")), rs.getString("NUMERO")));
                rl.setEntree(new java.util.Date(rs.getDate("DATE_ENTREE").getTime()));
                rl.setSortie(new java.util.Date(rs.getDate("DATE_SORTIE").getTime()));
                rl.setEtat(rs.getInt("ETAT"));

                reserveLogements.add(rl);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reserveLogements;
    }

    @Override
    public String annulerReservation(int idUser, int idCh, String entree, String sortie) {
        Connection db = null;
        PreparedStatement ps = null;
        String message = null;
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_RES_ANN);
            ps.setInt(1, idUser);
            ps.setInt(2, idCh);
            ps.setString(3, entree);
            ps.setString(4, sortie);
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
    public String validerReservation(int idUser, int idCh, String entree, String sortie) {
        Connection db = null;
        PreparedStatement ps = null;
        String message = null;
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_RES_VAL);
            ps.setInt(1, idUser);
            ps.setInt(2, idCh);
            ps.setString(3, entree);
            ps.setString(4, sortie);
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
