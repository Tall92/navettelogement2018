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
import modele.Navette;

/**
 *
 * @author tall
 */
public class ServiceNavetteImpl implements ServiceNavette {
    
    private static final String SQL_ADD_NAV = "INSERT INTO `navette`(`MATRICULE`, `NB_PLACE`) VALUES (?, ?)";
    private static final String SQL_SEL = "SELECT * FROM `navette`";

    @Override
    public String ajouterNav(Navette n) {
        Connection db = null;
        PreparedStatement ps = null;
        String message = null;
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_ADD_NAV);
            ps.setString(1, n.getMatricule());
            ps.setInt(2, n.getNbPlace());
            
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
    public List<Navette> listeNavettes() {
        Connection db = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Navette> navettes = new ArrayList<>();
        try {
            db = Connexion.getConnection();
            ps = db.prepareStatement(SQL_SEL);
            rs = ps.executeQuery();
            while (rs.next()) {
                Navette n = new Navette();
                n.setIdNav(rs.getInt("ID_NAVETTE"));
                n.setMatricule(rs.getString("MATRICULE"));
                n.setNbPlace(rs.getInt("NB_PLACE"));
                
                navettes.add(n);
            }

        } catch (SQLException e) {
        }
        return navettes;
    }
    
}
