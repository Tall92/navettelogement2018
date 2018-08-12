package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modele.Departement;
import modele.Ufr;

/**
 *
 * @author tall
 */
public class ServiceDeptImpl implements ServiceDept {

    private static final String SQL_ADD_DEPT = "INSERT INTO `departement`( `ID_UFR`, `NOM_DEPT`) VALUES (?,?)";
    private static final String SQL_LIST_DEPT = "SELECT * FROM `departement` d, `ufr` u WHERE d.ID_UFR = u.ID_UFR";
    private static final String SQL_MOD_DEP = "UPDATE `departement` SET `ID_UFR`=?,`NOM_DEPT`=? WHERE `ID_DEPT`=?";
    private static final String SQL_FIND_DEP = "SELECT * FROM `departement` d, `ufr` u WHERE d.ID_UFR = u.ID_UFR AND `ID_DEPT`=?";

    @Override
    public String ajouterDep(Departement dept) {
        Connection db = null;
        PreparedStatement ps = null;
        String message = null;
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_ADD_DEPT);
            ps.setInt(1, dept.getUfr().getIdUfr());
            ps.setString(2, dept.getNomDept());

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
    public List<Departement> listeDep() {
        Connection db = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Departement> depts = new ArrayList<>();
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_LIST_DEPT);
            rs = ps.executeQuery();
            while (rs.next()) {
                Departement a = new Departement();
                a.setIdDept(rs.getInt("ID_DEPT"));
                a.setUfr(new Ufr(rs.getInt("ID_UFR"), rs.getString("NOM_UFR")));
                a.setNomDept(rs.getString("NOM_DEPT"));
                depts.add(a);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return depts;

    }

    @Override
    public String modifierDep(Departement dept) {
        Connection db = null;
        PreparedStatement ps = null;
        String message = null;
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_MOD_DEP);
            ps.setInt(1, dept.getUfr().getIdUfr());
            ps.setString(2, dept.getNomDept());
            ps.setInt(3, dept.getIdDept());
            
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
    public Departement rechercherDept(int iddept) {
        Connection db = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Departement dept = null;
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_FIND_DEP);
            ps.setInt(1, iddept);
            rs = ps.executeQuery();
            if (rs.next()) {
                dept = new Departement();
                dept.setIdDept(rs.getInt("ID_DEPT"));
                dept.setNomDept(rs.getString("NOM_DEPT"));
                dept.setUfr(new Ufr(rs.getInt("ID_UFR"), rs.getString("NOM_UFR")));

            }

        } catch (SQLException e) {
        }
        return dept;

    }

}
