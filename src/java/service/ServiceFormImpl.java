package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modele.Departement;
import modele.Formation;

/**
 *
 * @author tall
 */
public class ServiceFormImpl implements ServiceForm {

    private static final String SQL_ADD_FORM = "INSERT INTO `formation`(`ID_DEPT`, `NOM_FORM`) VALUES (?,?)";
    private static final String SQL_LIST_FORM = "SELECT * FROM `formation` f, `departement` d WHERE f.ID_DEPT = d.ID_DEPT";
    private static final String SQL_MOD_FORM = "UPDATE `formation` SET `ID_DEPT`=?,`NOM_FORM`= ? WHERE `ID_FORM`= ?";
    private static final String SQL_FIND_FORM = "SELECT * FROM `formation` WHERE `ID_FORM` = ?";
    

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
    
    
}
