package GestionPersonnel.gestionBugetMAJ.services;

import GestionPersonnel.gestionBugetMAJ.entites.TypeDepense;

import java.util.List;

public interface ITypeDepenseService {

    String creer(TypeDepense typeDepense);
    String modifier(TypeDepense typeDepense);
    String supprimer(Long idTypeDepense);
    List<TypeDepense> afficher();
    TypeDepense lire(Long idTypeDepense);
}
