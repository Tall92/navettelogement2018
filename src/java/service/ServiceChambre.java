package service;

import java.util.List;
import modele.Chambre;
import modele.ReserveLogement;

/**
 *
 * @author tall
 */
public interface ServiceChambre {

    public String ajouter(Chambre c);

    public List<Chambre> listeChambres();

    public String modifierChambre(Chambre cha);
    
    public Chambre rechercher(int id);
    
    public String supprimer(int id);
    
    public List<Chambre> listeSiteChambres(int idSite);
    
    public String ajouterReservation(ReserveLogement rl);
    
    public List<ReserveLogement> listeReserveLogements();
    
    public List<ReserveLogement> listeReserveLogementsUfr(int idUfr);
    
    public String annulerReservation(int idUser, int idCh, String entree, String sortie);
    
    public String validerReservation(int idUser, int idCh, String entree, String sortie);

}
