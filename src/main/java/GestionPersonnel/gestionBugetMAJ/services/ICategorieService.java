package GestionPersonnel.gestionBugetMAJ.services;


import GestionPersonnel.gestionBugetMAJ.entites.Categorie;

import java.util.List;

public interface ICategorieService {

    String creer(Categorie categorie);

    String modifier(Categorie categorie);

    List<Categorie> afficher();

    Categorie lire(Long idCategorie);

    String supprimer(Long idCategorie);
}
