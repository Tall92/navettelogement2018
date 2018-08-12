/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modele.Chambre;
import modele.Site;

/**
 *
 * @author tall
 */
public class ServiceChambreImpl implements ServiceChambre {

    private static final String SQL_ADD_CHAM = "INSERT INTO `chambre`( `ID_SITE`, `NUMERO`) VALUES (?, ?)";
    private static final String SQL_LIST_CHAM = "SELECT * FROM `chambre` c, `site` s WHERE c.`ID_SITE` = s.`ID_SITE`";

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

}
