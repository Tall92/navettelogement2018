/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author tall
 */
public class Navette {
    private int idNav;
    private String matricule;
    private int nbPlace;

    public Navette() {
    }

    public Navette(String matricule, int nbPlace) {
        this.matricule = matricule;
        this.nbPlace = nbPlace;
    }
    
    public int getIdNav() {
        return idNav;
    }

    public void setIdNav(int idNav) {
        this.idNav = idNav;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public int getNbPlace() {
        return nbPlace;
    }

    public void setNbPlace(int nbPlace) {
        this.nbPlace = nbPlace;
    }
    
    
}
