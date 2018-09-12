package service;

import java.util.List;
import modele.Navette;
import modele.ReserveNavette;

/**
 *
 * @author tall
 */
public interface ServiceNavette {

    public String ajouterNav(Navette n);
    
    public List<Navette> listeNavettes();

    public String modifierNavette(Navette n);

    public Navette rechercherNavette(int idNav);

    public String supprimer(int id);
    
    public String ajouterReservation(ReserveNavette rn);
    
    public boolean verifierDispo(int idNav, String nature, String date);
    
    public List<ReserveNavette> listeReserveNavettes();
    
    public String annulerReservation(int idUser, int idNav, String nature, String date);

}
