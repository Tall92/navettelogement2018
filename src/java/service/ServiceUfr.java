
package service;

import java.util.List;
import modele.Ufr;

/**
 *
 * @author tall
 */
public interface ServiceUfr {
    
      public String ajouterUfr(Ufr ufr);
      
      public String modifierUfr(Ufr ufr);
      
      public List<Ufr> listeUfr();
      
      public Ufr rechercherUfr(int idufr);
      
      public String supprimer(int id);
    
}