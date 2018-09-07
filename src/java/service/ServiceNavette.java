package service;

import java.util.List;
import modele.Navette;

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
}
