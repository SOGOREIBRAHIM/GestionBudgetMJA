package GestionPersonnel.gestionBugetMAJ.repository;



import GestionPersonnel.gestionBugetMAJ.entites.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryAlert extends JpaRepository<Alert,Long> {

    Long findByIdAlert(Long idAlert);
//    Alert findByUtilisateurAlertAndBudgetAlert(Long idUser,Long idBudget);
}
