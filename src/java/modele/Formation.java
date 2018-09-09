package modele;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author tall
 */
public class Formation implements Serializable {

    private int idForm;
    private String nomForm;
    private Departement dept;
    private List<Utilisateur> utilisateurs;
    

    public Formation() {
    }
    

    public int getIdForm() {
        return idForm;
    }

    public void setIdForm(int idForm) {
        this.idForm = idForm;
    }

    public String getNomForm() {
        return nomForm;
    }

    public void setNomForm(String nomForm) {
        this.nomForm = nomForm;
    }

    public Departement getDept() {
        return dept;
    }

    public void setDept(Departement dept) {
        this.dept = dept;
    }

    public List<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(List<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }
    
}
