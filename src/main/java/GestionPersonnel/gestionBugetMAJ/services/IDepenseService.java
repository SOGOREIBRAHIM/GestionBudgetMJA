package GestionPersonnel.gestionBugetMAJ.services;

import GestionPersonnel.gestionBugetMAJ.entites.Depense;

import java.util.List;

public interface IDepenseService {

    Depense effetuer(Depense depense);

    String modiffier(Depense depense);

    List<Depense> afficher();

    String supprimer(Long idDepense);

    Depense lire(Long idDepense);
}
