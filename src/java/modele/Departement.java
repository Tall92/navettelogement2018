
package modele;

import java.io.Serializable;

/**
 *
 * @author tall
 */
public class Departement implements Serializable {

    private int idDept;
    private Ufr ufr;
    private String nomDept;
    
    public Departement() {
    }
    public Departement(int idDept, String nomDept) {
        this.idDept = idDept;
        this.nomDept = nomDept;
    }

    public int getIdDept() {
        return idDept;
    }

    public void setIdDept(int idDept) {
        this.idDept = idDept;
    }

    public Ufr getUfr() {
        return ufr;
    }

    public void setUfr(Ufr ufr) {
        this.ufr = ufr;
    }

    public String getNomDept() {
        return nomDept;
    }

    public void setNomDept(String nomDept) {
        this.nomDept = nomDept;
    }

}
