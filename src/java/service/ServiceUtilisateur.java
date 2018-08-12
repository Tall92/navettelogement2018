package service;

import java.util.List;
import modele.Utilisateur;

/**
 *
 * @author tall
 */
public interface ServiceUtilisateur {

    public String ajouterUsers(Utilisateur user);

    public List<Utilisateur> listeUtilisateur();
      public String modifierUtilisateur(Utilisateur user);
      
           public Utilisateur rechercher(int id);

}
