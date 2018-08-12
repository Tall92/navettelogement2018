package modele;

import java.io.Serializable;

/**
 *
 * @author tall
 */
public class Site implements Serializable {

    private int sit;
    private Gestionnaire ges;
    private String nomSite;

    public Site() {

    }

    public Site(int sit) {
        this.sit = sit;
    }

    public Site(int sit, String nomSite) {
        this.sit = sit;
        this.nomSite = nomSite;
    }

    public int getSit() {
        return sit;
    }

    public void setSit(int sit) {
        this.sit = sit;
    }

    public Gestionnaire getGes() {
        return ges;
    }

    public void setGes(Gestionnaire ges) {
        this.ges = ges;
    }

    public String getNomSite() {
        return nomSite;
    }

    public void setNomSite(String nomSite) {
        this.nomSite = nomSite;
    }

}
