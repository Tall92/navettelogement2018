package service;

import java.util.List;
import modele.Site;

/**
 *
 * @author tall
 */
public interface ServiceSite {

    public String ajouterSite(Site sit);

    public List<Site> listeSite();

    public String modifierSite(Site site);

    public Site rechercherSite(int sit);
}
