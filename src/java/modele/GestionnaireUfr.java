package modele;

/**
 *
 * @author kane
 */
public class GestionnaireUfr {
    private int idGesUfr;
    private Ufr ufr;
    private String prenom;
    private String nom;
    private String telephone;
    private String login;
    private String motDePasse;

    public int getIdGesUfr() {
        return idGesUfr;
    }

    public void setIdGesUfr(int idGesUfr) {
        this.idGesUfr = idGesUfr;
    }

    public Ufr getUfr() {
        return ufr;
    }

    public void setUfr(Ufr ufr) {
        this.ufr = ufr;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
    
    
}
