package GestionPersonnel.gestionBugetMAJ.repository;


import GestionPersonnel.gestionBugetMAJ.entites.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryBudget extends JpaRepository<Budget,Long> {

//    chercher budget par titre unique
    Budget findByTitre(String titre);

//    chercher un budget par date de Debut
    Budget findByIdBudget(Long idBudget);



}
