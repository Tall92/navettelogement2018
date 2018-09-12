package modele;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author tall
 */
public class Utilisateur implements Serializable {

    private int idUser;
    private Ufr ufr;
    private Departement dept;
    private String prenom;
    private String nom;
    private String adresse;
    private String tel;
    private String login;
    private String motPasse;
    private String profil;
    private int statut;
    private List<Formation> formations;

    public Utilisateur(){
        
    }
    public Utilisateur(int iduser) {
        this.idUser = iduser;
    }

    public Utilisateur(int iduser,String prenom, String nom, String adresse, String tel, String login) {
        this.idUser = iduser;
        this.prenom = prenom;
        this.nom = nom;
        this.adresse = adresse;
        this.tel = tel;
        this.login = login;
    }
    
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Ufr getUfr() {
        return ufr;
    }

    public void setUfr(Ufr ufr) {
        this.ufr = ufr;
    }

    public Departement getDept() {
        return dept;
    }

    public void setDept(Departement dept) {
        this.dept = dept;
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMotPasse() {
        return motPasse;
    }

    public void setMotPasse(String motPasse) {
        this.motPasse = motPasse;
    }

    public String getProfil() {
        return profil;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }
    
    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    public List<Formation> getFormations() {
        return formations;
    }

    public void setFormations(List<Formation> formations) {
        this.formations = formations;
    }

}
