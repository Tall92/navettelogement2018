package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modele.Administrateur;

/**
 *
 * @author tall
 */
public class ServiceAdminisImpl implements ServiceAdminis {

    private static final String SQL_CON = "SELECT * FROM `administrateur` WHERE `LOGIN` = ? AND `MOT_DE_PASSE` = ? AND `STATUT` = 1";
    private static final String SQL_ADD = "INSERT INTO `administrateur`(`PRENOM`, `NOM`, `TELEPHONE`, `LOGIN`, `MOT_DE_PASSE`, `STATUT`) VALUES (?, ?, ?, ?, 'navette', 1)";
    private static final String SQL_LIST = "SELECT * FROM `administrateur`";
    private static final String SQL_FIND = "SELECT * FROM `administrateur` WHERE id_user = ?";
    private static final String SQL_MOD = "UPDATE `administrateur` SET `PRENOM`=?,`NOM`=?,`TELEPHONE`=?,`LOGIN`=? WHERE `ID_USER`=?";

    @Override
    public Administrateur connexion(String login, String password) {

        Connection db = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Administrateur a = null;
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_CON);
            ps.setString(1, login);
            ps.setString(2, password);
            // excution de la requete
            rs = ps.executeQuery();
            if (rs.next()) {
                a = new Administrateur();
                a.setIdUser(rs.getInt("id_user"));
                a.setPrenom(rs.getString("prenom"));
                a.setNom(rs.getString("nom"));
                a.setTelephone(rs.getString("telephone"));
                a.setLogin(rs.getString("login"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;

    }

    @Override
    public String ajouterAdmin(Administrateur a) {
        Connection db = null;
        PreparedStatement ps = null;
        String message = null;
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_ADD);
            ps.setString(1, a.getPrenom());
            ps.setString(2, a.getNom());
            ps.setString(3, a.getTelephone());
            ps.setString(4, a.getLogin());

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
    public List<Administrateur> listeAdministrateur() {

        Connection db = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Administrateur> adminis = new ArrayList<>();
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_LIST);
            rs = ps.executeQuery();
            while (rs.next()) {
                Administrateur a = new Administrateur();
                a.setIdUser(rs.getInt("id_user"));
                a.setPrenom(rs.getString("prenom"));
                a.setNom(rs.getString("nom"));
                a.setTelephone(rs.getString("telephone"));
                a.setLogin(rs.getString("login"));
                adminis.add(a);
            }

        } catch (SQLException e) {
        }
        return adminis;
    }

    @Override
    public Administrateur rechercher(int user) {
        Connection db = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Administrateur a = null;
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_FIND);
            ps.setInt(1, user);
            // excution de la requete
            rs = ps.executeQuery();
            if (rs.next()) {
                a = new Administrateur();
                a.setIdUser(rs.getInt("id_user"));
                a.setPrenom(rs.getString("prenom"));
                a.setNom(rs.getString("nom"));
                a.setTelephone(rs.getString("telephone"));
                a.setLogin(rs.getString("login"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }

    @Override
    public String modifier(Administrateur admin) {
        Connection db = null;
        PreparedStatement ps = null;
        String message = null;
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_MOD);
            ps.setString(1, admin.getPrenom());
            ps.setString(2, admin.getNom());
            ps.setString(3, admin.getTelephone());
            ps.setString(4, admin.getLogin());
            ps.setInt(5, admin.getIdUser());

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
