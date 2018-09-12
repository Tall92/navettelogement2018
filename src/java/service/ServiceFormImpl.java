package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modele.Departement;
import modele.Formation;
import modele.Ufr;
import modele.Utilisateur;

/**
 *
 * @author tall
 */
public class ServiceFormImpl implements ServiceForm {

    private static final String SQL_ADD_FORM = "INSERT INTO `formation`(`ID_DEPT`, `NOM_FORM`) VALUES (?,?)";
    private static final String SQL_LIST_FORM = "SELECT * FROM `formation` f, `departement` d WHERE f.ID_DEPT = d.ID_DEPT";
    private static final String SQL_MOD_FORM = "UPDATE `formation` SET `ID_DEPT`=?,`NOM_FORM`= ? WHERE `ID_FORM`= ?";
    private static final String SQL_FIND_FORM = "SELECT * FROM `formation` f, `departement` d WHERE f.ID_DEPT = d.ID_DEPT AND `ID_FORM` = ?";
    private static final String SQL_DEL_FORM = "DELETE FROM `formation` WHERE `ID_FORM` = ?";
    private static final String SQL_INT_FORM = "SELECT * FROM `utilisateur` u INNER JOIN ufr ON u.ID_UFR = ufr.ID_UFR INNER JOIN `departement` d ON u.ID_DEPT= d.ID_DEPT INNER JOIN `intervenir` i ON u.`ID_USER` = i.`ID_USER` INNER JOIN `formation` f ON i.`ID_FORM` = f.`ID_FORM` AND f.`ID_FORM` = ? AND u.`PROFIL` IN ('Missionnaire', 'Vacataire')";
    private static final String SQL_INT = "SELECT * FROM `utilisateur` u INNER JOIN ufr ON u.ID_UFR = ufr.ID_UFR INNER JOIN `departement` d ON u.ID_DEPT= d.ID_DEPT INNER JOIN `intervenir` i ON u.`ID_USER` = i.`ID_USER` INNER JOIN `formation` f ON i.`ID_FORM` = f.`ID_FORM` AND f.`ID_FORM` = ?";
    private static final String SQL_NOT = "SELECT * FROM `utilisateur` u WHERE u.`ID_USER` NOT IN (SELECT `ID_USER` FROM `intervenir` WHERE `ID_FORM` = ?)";
    private static final String SQL_ADD_TO_FORM = "INSERT INTO `intervenir`(`ID_USER`, `ID_FORM`) VALUES (?,?)";
    private static final String SQL_ATTRIBUER = "UPDATE `intervenir` SET `ROLE` = 1 WHERE `ID_USER` = ? AND `ID_FORM` = ?";

    @Override
    public String ajouterFormation(Formation formation) {
        Connection db = null;
        PreparedStatement ps = null;
        String message = null;
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_ADD_FORM);
            ps.setInt(1, formation.getDept().getIdDept());
            ps.setString(2, formation.getNomForm());

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
    public List<Formation> listesFormation() {
        Connection db = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Formation> forms = new ArrayList<>();
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_LIST_FORM);
            rs = ps.executeQuery();
            while (rs.next()) {
                Formation a = new Formation();
                a.setIdForm(rs.getInt("ID_FORM"));
                a.setDept(new Departement(rs.getInt("ID_DEPT"), rs.getString("NOM_DEPT")));
                a.setNomForm(rs.getString("NOM_FORM"));
                forms.add(a);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return forms;

    }

    @Override
    public String modifierFormation(Formation forms) {
        Connection db = null;
        PreparedStatement ps = null;
        String message = null;
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_MOD_FORM);
            ps.setInt(1, forms.getDept().getIdDept());
            ps.setString(2, forms.getNomForm());
            ps.setInt(3, forms.getIdForm());
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
    public Formation rechercher(int id) {
        Connection db = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Formation form = null;
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_FIND_FORM);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                form = new Formation();
                form.setIdForm(rs.getInt("ID_FORM"));
                form.setNomForm(rs.getString("NOM_FORM"));
                form.setDept(new Departement(rs.getInt("ID_DEPT"), rs.getString("NOM_DEPT")));
                
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return form;
    }

    @Override
    public String supprimer(int id) {
        Connection db = null;
        PreparedStatement ps = null;
        String message = null;
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_DEL_FORM);
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
    public List<Utilisateur> listeEnseignants(int idform) {
        Connection db = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Utilisateur> utilisateurs = new ArrayList<>();
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_INT);
            ps.setInt(1, idform);
            rs = ps.executeQuery();
            while (rs.next()) {
                Utilisateur u = new Utilisateur();
                
                u.setIdUser(rs.getInt("ID_USER"));
                u.setUfr(new Ufr(rs.getInt("ID_UFR"), rs.getString("NOM_UFR")));
                u.setDept(new Departement(rs.getInt("ID_DEPT"), rs.getString("NOM_DEPT")));
                u.setPrenom(rs.getString("PRENOM"));
                u.setNom(rs.getString("NOM"));
                u.setAdresse(rs.getString("ADRESSE"));
                u.setTel(rs.getString("LOGIN"));
                u.setProfil(rs.getString("PROFIL"));
                
                utilisateurs.add(u);
                
//                f.setUtilisateurs(utilisateurs);
//                
//                forms.add(a);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateurs;
    }
    
    @Override
    public List<Utilisateur> listeEnseignantsForm(int idform) {
        Connection db = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Utilisateur> utilisateurs = new ArrayList<>();
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_INT_FORM);
            ps.setInt(1, idform);
            rs = ps.executeQuery();
            while (rs.next()) {
                Utilisateur u = new Utilisateur();
                
                u.setIdUser(rs.getInt("ID_USER"));
                u.setUfr(new Ufr(rs.getInt("ID_UFR"), rs.getString("NOM_UFR")));
                u.setDept(new Departement(rs.getInt("ID_DEPT"), rs.getString("NOM_DEPT")));
                u.setPrenom(rs.getString("PRENOM"));
                u.setNom(rs.getString("NOM"));
                u.setAdresse(rs.getString("ADRESSE"));
                u.setTel(rs.getString("LOGIN"));
                u.setProfil(rs.getString("PROFIL"));
                
                utilisateurs.add(u);
                
//                f.setUtilisateurs(utilisateurs);
//                
//                forms.add(a);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateurs;
    }

    @Override
    public List<Utilisateur> listeNotInForm(int idform) {
        Connection db = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Utilisateur> utilisateurs = new ArrayList<>();
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_NOT);
            ps.setInt(1, idform);
            rs = ps.executeQuery();
            while (rs.next()) {
                Utilisateur u = new Utilisateur();
                
                u.setIdUser(rs.getInt("ID_USER"));
                u.setPrenom(rs.getString("PRENOM"));
                u.setNom(rs.getString("NOM"));
                
                utilisateurs.add(u);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateurs;
    }

    @Override
    public String addToForm(int iduser, int idform) {
        Connection db = null;
        PreparedStatement ps = null;
        String message = null;
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_ADD_TO_FORM);
            ps.setInt(1, iduser);
            ps.setInt(2, idform);

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
    public String attribuer(int iduser, int idform) {
        Connection db = null;
        PreparedStatement ps = null;
        String message = null;
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_ATTRIBUER);
            ps.setInt(1, iduser);
            ps.setInt(2, idform);

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
    public List<Formation> mesFormations(int iduser) {
        Connection db = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Formation> forms = new ArrayList<>();
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement("SELECT * FROM  `formation` f, `intervenir` i, `utilisateur` u WHERE f.`ID_FORM` = i.`ID_FORM` AND i.`ID_USER` = u.`ID_USER` AND u.`ID_USER` = ?");
            ps.setInt(1, iduser);
            rs = ps.executeQuery();
            while (rs.next()) {
                Formation form = new Formation();
                form.setIdForm(rs.getInt("ID_FORM"));
                form.setNomForm(rs.getString("NOM_FORM"));
                forms.add(form);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return forms;
    }
   
    
}
