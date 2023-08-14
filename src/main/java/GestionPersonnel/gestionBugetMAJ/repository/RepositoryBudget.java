package GestionPersonnel.gestionBugetMAJ.repository;


import GestionPersonnel.gestionBugetMAJ.entites.Budget;
import GestionPersonnel.gestionBugetMAJ.entites.Categorie;
import GestionPersonnel.gestionBugetMAJ.entites.Utilisateur;
import GestionPersonnel.gestionBugetMAJ.exception.DuplicateException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface RepositoryBudget extends JpaRepository<Budget,Long> {

//    chercher budget par titre unique
    Budget findByDateDebutAndDateFin(LocalDate dateDebut,LocalDate dateFin);
    Budget findByTitre(String titre);

//    chercher un budget par date de Debut
    Budget findByIdBudget(Long idBudget);

    Budget findByUtilisateurBudgetAndCategorieBudgetAndDateFin(Utilisateur utilisateur, Categorie categorie, LocalDate dateFin);



}
