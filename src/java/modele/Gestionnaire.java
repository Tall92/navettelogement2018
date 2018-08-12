package modele;

import java.io.Serializable;

/**
 *
 * @author tall
 */
public class Gestionnaire implements Serializable {

    private int ges;
    private String prenom;
    private String nom;
    private String adr;
    private String tel;
    
    public Gestionnaire(){
        
    }

    public Gestionnaire(int ges) {
        this.ges = ges;
    }
    
    

    public Gestionnaire(int ges ,String prenom, String nom, String adr, String tel) {
        this.ges = ges;
        this.prenom = prenom;
        this.nom = nom;
        this.adr = adr;
        this.tel = tel;
    }
    

    public int getGes() {
        return ges;
    }

    public void setGes(int ges) {
        this.ges = ges;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdr() {
        return adr;
    }

    public void setAdr(String adr) {
        this.adr = adr;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

}
