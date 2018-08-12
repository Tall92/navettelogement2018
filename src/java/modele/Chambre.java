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
public class Chambre {

    private int idCh;
    private Site site;
    private String numero;

    public Chambre() {
    }

    public Chambre(Site site, String numero) {
        this.site = site;
        this.numero = numero;
    }

    public Chambre(int idch, Site site, String numero) {
        this.idCh = idch;
        this.site = site;
        this.numero = numero;
    }

    public Chambre(int idch) {
        this.idCh = idch;
    }
    
    

    public int getIdCh() {
        return idCh;
    }

    public void setIdCh(int idCh) {
        this.idCh = idCh;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    

}
