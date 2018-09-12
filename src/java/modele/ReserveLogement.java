package modele;

import java.util.Date;

/**
 *
 * @author kane
 */
public class ReserveLogement {
    private Utilisateur utilisateur;
    private Chambre chambre;
    private Date entree;
    private Date sortie;
    private int etat;

    public ReserveLogement() {
    }

    public ReserveLogement(Utilisateur utilisateur, Chambre chambre, Date entree, Date sortie) {
        this.utilisateur = utilisateur;
        this.chambre = chambre;
        this.entree = entree;
        this.sortie = sortie;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Chambre getChambre() {
        return chambre;
    }

    public void setChambre(Chambre chambre) {
        this.chambre = chambre;
    }

    public Date getEntree() {
        return entree;
    }

    public void setEntree(Date entree) {
        this.entree = entree;
    }

    public Date getSortie() {
        return sortie;
    }

    public void setSortie(Date sortie) {
        this.sortie = sortie;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
    
    
}
