package service;

import java.util.List;
import modele.Gestionnaire;

/**
 *
 * @author tall
 */
public interface ServiceGestionnaire {

    public String ajouterGes(Gestionnaire gess);

    public String modifierGes(Gestionnaire gess);

    public List<Gestionnaire> listeGestion();

    public Gestionnaire rechercherGestion(int Ges);

}
