package service;

import java.util.List;
import modele.Departement;

public interface ServiceDept {

    public String ajouterDep(Departement dept);

    public List<Departement> listeDep();

    public String modifierDep(Departement dept);

    public Departement rechercherDept(int iddept);

}
