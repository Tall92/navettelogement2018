package service;

import java.util.List;
import modele.Formation;
import modele.Utilisateur;

/**
 *
 * @author tall
 */
public interface ServiceForm {

    public String ajouterFormation(Formation formation);

    public List<Formation> listesFormation();

    public String modifierFormation(Formation forms);
    
    public Formation rechercher(int id);
    
    public String supprimer(int id);
    
    public List<Utilisateur> listeEnseignants(int idform);
    
    public List<Utilisateur> listeNotInForm(int idform);
    
    public String addToForm(int iduser, int idform);
    
    public String attribuer(int iduser, int idform);
    
    public List<Formation> mesFormations(int iduser);

    public List<Utilisateur> listeEnseignantsForm(int idform);
}
