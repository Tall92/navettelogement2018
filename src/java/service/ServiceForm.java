package service;

import java.util.List;
import modele.Formation;

/**
 *
 * @author tall
 */
public interface ServiceForm {

    public String ajouterFormation(Formation formation);

    public List<Formation> listesFormation();

    public String modifierFormation(Formation forms);
    
    public Formation rechercher(int id);

}
