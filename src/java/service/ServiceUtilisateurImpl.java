package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modele.Departement;
import modele.Ufr;
import modele.Utilisateur;

/**
 *
 * @author tall
 */
public class ServiceUtilisateurImpl implements ServiceUtilisateur {

    private static final String SQL_ADD_USER = "INSERT INTO `utilisateur`( `ID_UFR`, `ID_DEPT`, `PRENOM`, `NOM`, `ADRESSE`, `TELEPHONE`, `LOGIN`, `MOT_DE_PASSE`, `PROFIL`, `STATUT`) VALUES (?,?,?,?,?,?,?,'navette2018',?,1)";
    private static final String SQL_LIST_USER = "SELECT * FROM `utilisateur` us, `ufr` u, `departement` d WHERE us.ID_UFR = u.ID_UFR AND us.ID_DEPT= d.ID_DEPT";
    private static final String SQL_MOD_USER = "UPDATE `utilisateur` SET `ID_UFR`=?,`ID_DEPT`=?,`PRENOM`=?,`NOM`=?,`ADRESSE`=?,`TELEPHONE`=?,`LOGIN`=?,`MOT_DE_PASSE`='navette2018',`PROFIL`=? WHERE `ID_USER`=?";
    private static final String SQL_FIND_USER = "SELECT * FROM `utilisateur` us, `ufr` u, `departement` d WHERE us.ID_UFR = u.ID_UFR AND us.ID_DEPT= d.ID_DEPT AND `ID_USER`=?";
    private static final String SQL_ACT = "UPDATE `utilisateur` SET `STATUT`=1 WHERE `ID_USER`=?";
    private static final String SQL_DESACT = "UPDATE `utilisateur` SET `STATUT`=0 WHERE `ID_USER`=?";
    private static final String SQL_CON = "SELECT * FROM `utilisateur` us, `ufr` u, `departement` d WHERE us.ID_UFR = u.ID_UFR AND us.ID_DEPT= d.ID_DEPT AND `LOGIN` = ? AND `MOT_DE_PASSE` = ? AND `STATUT` = 1";

    @Override
    public String ajouterUsers(Utilisateur user) {
        Connection db = null;
        PreparedStatement ps = null;
        String message = null;
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_ADD_USER);

            ps.setInt(1, user.getUfr().getIdUfr());
            ps.setInt(2, user.getDept().getIdDept());
            ps.setString(3, user.getPrenom());
            ps.setString(4, user.getNom());
            ps.setString(5, user.getAdresse());
            ps.setString(6, user.getTel());
            ps.setString(7, user.getLogin());
            ps.setString(8, user.getProfil());

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
    public List<Utilisateur> listeUtilisateur() {
        Connection db = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Utilisateur> users = new ArrayList<>();
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_LIST_USER);
            rs = ps.executeQuery();
            while (rs.next()) {
                Utilisateur a = new Utilisateur();
                a.setIdUser(rs.getInt("ID_USER"));
                a.setUfr(new Ufr(rs.getInt("ID_UFR"), rs.getString("NOM_UFR")));
                a.setDept(new Departement(rs.getInt("ID_DEPT"), rs.getString("NOM_DEPT")));
                a.setPrenom(rs.getString("PRENOM"));
                a.setNom(rs.getString("NOM"));
                a.setAdresse(rs.getString("ADRESSE"));
                a.setTel(rs.getString("TELEPHONE"));
                a.setLogin(rs.getString("LOGIN"));
                a.setProfil(rs.getString("PROFIL"));
                a.setStatut(rs.getInt("STATUT"));
                users.add(a);
            }

        } catch (SQLException e) {
        }
        return users;
    }

    @Override
    public String modifierUtilisateur(Utilisateur user) {
        Connection db = null;
        PreparedStatement ps = null;
        String message = null;
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_MOD_USER);
            ps.setInt(1, user.getUfr().getIdUfr());
            ps.setInt(2, user.getDept().getIdDept());
            ps.setString(3, user.getPrenom());
            ps.setString(4, user.getNom());
            ps.setString(5, user.getAdresse());
            ps.setString(6, user.getTel());
            ps.setString(7, user.getLogin());
            ps.setString(8, user.getProfil());
            ps.setInt(9, user.getIdUser());

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
    public Utilisateur rechercher(int id) {
        Connection db = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Utilisateur user = null;
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_FIND_USER);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new Utilisateur();
                user.setIdUser(rs.getInt("ID_USER"));
                user.setUfr(new Ufr(rs.getInt("ID_UFR"), rs.getString("NOM_UFR")));
                user.setDept(new Departement(rs.getInt("ID_DEPT"), rs.getString("NOM_DEPT")));
                user.setPrenom(rs.getString("PRENOM"));
                user.setNom(rs.getString("NOM"));
                user.setAdresse(rs.getString("ADRESSE"));
                user.setTel(rs.getString("TELEPHONE"));
                user.setLogin(rs.getString("LOGIN"));
                user.setProfil(rs.getString("PROFIL"));
                user.setStatut(rs.getInt("STATUT"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public String activer(int id) {
        Connection db = null;
        PreparedStatement ps = null;
        String message = null;
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_ACT);

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
    public String desactiver(int id) {
        Connection db = null;
        PreparedStatement ps = null;
        String message = null;
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_DESACT);

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
    public Utilisateur connexion(String login, String password) {
        Connection db = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Utilisateur u = null;
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_CON);
            ps.setString(1, login);
            ps.setString(2, password);
            // excution de la requete
            rs = ps.executeQuery();

            if (rs.next()) {
                u = new Utilisateur();
                u.setIdUser(rs.getInt("ID_USER"));
                u.setUfr(new Ufr(rs.getInt("ID_UFR"), rs.getString("NOM_UFR")));
                u.setDept(new Departement(rs.getInt("ID_DEPT"), rs.getString("NOM_DEPT")));
                u.setPrenom(rs.getString("PRENOM"));
                u.setNom(rs.getString("NOM"));
                u.setAdresse(rs.getString("ADRESSE"));
                u.setTel(rs.getString("LOGIN"));
                u.setProfil(rs.getString("PROFIL"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }
}
