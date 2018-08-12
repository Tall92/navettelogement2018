package modele;

import java.io.Serializable;

/**
 *
 * @author tall
 */
public class Ufr implements Serializable{

    private int idUfr;
    private String nomUfr;

    public Ufr() {
    }

    public Ufr(int idUfr, String nomUfr) {
        this.idUfr = idUfr;
        this.nomUfr = nomUfr;
    }

    public int getIdUfr() {
        return idUfr;
    }

    public void setIdUfr(int idUfr) {
        this.idUfr = idUfr;
    }

    public String getNomUfr() {
        return nomUfr;
    }

    public void setNomUfr(String nomUfr) {
        this.nomUfr = nomUfr;
    }

}
