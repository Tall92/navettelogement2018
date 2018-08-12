package modele;

import java.io.Serializable;

/**
 *
 * @author tall
 */
public class Formation implements Serializable {

    private int idForm;
    private String nomForm;
    private Departement dept;
    

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
    

    

}
