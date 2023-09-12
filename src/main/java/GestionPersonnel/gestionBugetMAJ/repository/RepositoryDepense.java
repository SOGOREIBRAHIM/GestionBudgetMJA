package GestionPersonnel.gestionBugetMAJ.repository;

import GestionPersonnel.gestionBugetMAJ.entites.Budget;
import GestionPersonnel.gestionBugetMAJ.entites.Depense;
import GestionPersonnel.gestionBugetMAJ.entites.TypeDepense;
import GestionPersonnel.gestionBugetMAJ.entites.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;

public interface RepositoryDepense extends JpaRepository<Depense,Long > {

    Depense findByDescription(String description);
    Depense findByIdDepense(Long idDepense);

    Depense findByUtilisateurDepenseAndTypeDepenseAndBudgetDepenseAndDate(Utilisateur utilisateur, TypeDepense typeDepense, Budget budget, LocalDate date);
    Depense findFirstByUtilisateurDepenseAndTypeDepenseAndBudgetDepenseOrderByDateDesc(Utilisateur utilisateur, TypeDepense typeDepense, Budget budget);

}
