package service;

import java.util.List;
import modele.Administrateur;

/**
 *
 * @author tall
 */
public interface ServiceAdminis {

    public Administrateur connexion(String login, String password);

    public String ajouterAdmin(Administrateur a);
    
    public Administrateur rechercher(int user);
    
    public String modifier(Administrateur admin);

    public List<Administrateur> listeAdministrateur();

}
