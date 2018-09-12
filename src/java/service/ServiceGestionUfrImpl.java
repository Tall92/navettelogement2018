package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modele.GestionnaireUfr;
import modele.Ufr;

/**
 *
 * @author tall
 */
public class ServiceGestionUfrImpl implements ServiceGestionnaireUfr {

    private static final String SQL_ADD_GES = "INSERT INTO `gestionnaireufr`( `ID_UFR`,`PRENOM`, `NOM`, `TELEPHONE`, `LOGIN`, `MOT_DE_PASSE`, `STATUT`) VALUES (?, ?, ?, ?, ?, 'logement' , 1)";
    private static final String SQL_MOD_GES = "UPDATE `gestionnaireufr` SET `ID_UFR`=?, `PRENOM`=?,`NOM`=?,`TELEPHONE`=?, `LOGIN`= ? WHERE `ID_GES_UFR`=?";
    private static final String SQL_LIST_GES = "SELECT * FROM `gestionnaireufr` gr, `ufr` u WHERE gr.`ID_UFR` = u.`ID_UFR`";
    private static final String SQL_FIND_GES = "SELECT * FROM `gestionnaireufr` gr, `ufr` u WHERE gr.`ID_UFR` = u.`ID_UFR` AND `ID_GES_UFR` = ?";
    private static final String SQL_DEL_GES = "DELETE FROM `gestionnaireufr` WHERE `ID_GES_UFR` = ?";

    @Override
    public String ajouterGes(GestionnaireUfr gess) {
        Connection db = null;
        PreparedStatement ps = null;
        String message = null;
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_ADD_GES);
            ps.setInt(1, gess.getUfr().getIdUfr());
            ps.setString(2, gess.getPrenom());
            ps.setString(3, gess.getNom());
            ps.setString(4, gess.getTelephone());
            ps.setString(5, gess.getLogin());
            
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
    public String modifierGes(GestionnaireUfr gess) {
        Connection db = null;
        PreparedStatement ps = null;
        String message = null;
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_MOD_GES);
            ps.setInt(1, gess.getUfr().getIdUfr());
            ps.setString(2, gess.getPrenom());
            ps.setString(3, gess.getNom());
            ps.setString(4, gess.getTelephone());
            ps.setString(5, gess.getLogin());
            ps.setInt(6, gess.getIdGesUfr());

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
    public List<GestionnaireUfr> listeGestion() {

        Connection db = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<GestionnaireUfr> gestion = new ArrayList<>();
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_LIST_GES);
            rs = ps.executeQuery();
            while (rs.next()) {
                GestionnaireUfr gest = new GestionnaireUfr();
                gest.setIdGesUfr(rs.getInt("ID_GES_UFR"));
                gest.setUfr(new Ufr(rs.getInt("ID_UFR"), rs.getString("NOM_UFR")));
                gest.setPrenom(rs.getString("PRENOM"));
                gest.setNom(rs.getString("NOM"));
                gest.setTelephone(rs.getString("TELEPHONE"));
                gest.setLogin(rs.getString("LOGIN"));

                gestion.add(gest);
            }

        } catch (SQLException e) {
        }
        return gestion;
    }

    @Override
    public GestionnaireUfr rechercherGestion(int ges) {
        Connection db = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        GestionnaireUfr gestion = null;
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_FIND_GES);
            ps.setInt(1, ges);
            // excution de la requete
            rs = ps.executeQuery();
            if (rs.next()) {
                gestion = new GestionnaireUfr();
                gestion.setIdGesUfr(rs.getInt("ID_GES_UFR"));
                gestion.setUfr(new Ufr(rs.getInt("ID_UFR"), rs.getString("NOM_UFR")));
                gestion.setPrenom(rs.getString("PRENOM"));
                gestion.setNom(rs.getString("NOM"));
                gestion.setTelephone(rs.getString("TELEPHONE"));
                gestion.setLogin(rs.getString("LOGIN"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gestion;
    }

    @Override
    public String supprimer(int id) {
        Connection db = null;
        PreparedStatement ps = null;
        String message = null;
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_DEL_GES);
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
