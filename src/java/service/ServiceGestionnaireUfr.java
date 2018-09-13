package service;

import java.util.List;
import modele.GestionnaireUfr;

/**
 *
 * @author tall
 */
public interface ServiceGestionnaireUfr {

    public String ajouterGes(GestionnaireUfr gess);

    public String modifierGes(GestionnaireUfr gess);

    public List<GestionnaireUfr> listeGestion();

    public GestionnaireUfr rechercherGestion(int Ges);

    public String supprimer(int id);
    
    public GestionnaireUfr connexion(String login, String password);

}
