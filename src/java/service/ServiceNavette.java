/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import modele.Navette;

/**
 *
 * @author tall
 */
public interface ServiceNavette {
    
    public String ajouterNav(Navette n);
    
    public List<Navette> listeNavettes();
}
