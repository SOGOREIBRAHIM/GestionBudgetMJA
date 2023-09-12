package GestionPersonnel.gestionBugetMAJ.services;


import GestionPersonnel.gestionBugetMAJ.entites.Utilisateur;

import java.util.List;

public interface IUtilisateurService {

    //  Inscription unique de user
    String inscrire(Utilisateur utilisateur);

    String connexion(String email, String motDePasse);
    //  Afficher tous la liste des users
    List<Utilisateur> afficherAll();
    //  Lire un user avec son id
    Utilisateur lire(Long idUser);
    //  Modifier un user
    String modifier(Utilisateur utilisateur);
    //  Supprimer un user
    String supprimer(Long idUser);

}
