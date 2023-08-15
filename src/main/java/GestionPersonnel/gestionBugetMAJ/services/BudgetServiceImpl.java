package GestionPersonnel.gestionBugetMAJ.services;



import GestionPersonnel.gestionBugetMAJ.entites.Budget;
import GestionPersonnel.gestionBugetMAJ.entites.Depense;
import GestionPersonnel.gestionBugetMAJ.exception.DuplicateException;
import GestionPersonnel.gestionBugetMAJ.exception.InvalideException;
import GestionPersonnel.gestionBugetMAJ.exception.NotFoundException;
import GestionPersonnel.gestionBugetMAJ.repository.RepositoryBudget;
import GestionPersonnel.gestionBugetMAJ.repository.RepositoryDepense;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
@AllArgsConstructor
public class BudgetServiceImpl implements IBudgetService {

    private RepositoryBudget repositoryBudget;      //  injection
    private RepositoryDepense repositoryDepense;
    private IAlertService alertService;

    @Override
    public String creer(Budget budget) {
        Budget titreVerif = repositoryBudget.findByTitre(budget.getTitre());
        if (titreVerif!=null){
            throw new DuplicateException("Le buget existe");
        }
        LocalDate dateDebutVerif = budget.getDateDebut();
        if (dateDebutVerif.isAfter(LocalDate.now()) || dateDebutVerif.isBefore(LocalDate.now())){
            throw new NotFoundException("Entre une date valide !");
        }
        budget.setDateFin(dateDebutVerif.with(TemporalAdjusters.lastDayOfMonth()));
        repositoryBudget.save(budget);
        return "Budget creer";

    }

    @Override
    public List<Budget> afficher() {
        return repositoryBudget.findAll();
    }

    @Override
    public Budget lire(Long idBudget) {
        return  repositoryBudget.findById(idBudget).orElseThrow(
                ()-> new InvalideException("Identifiant n'existe pas !")
        );
    }

    @Override
    public String modifier(Budget budget) {
        Budget budget1 = repositoryBudget.findByTitre(budget.getTitre());
        if (budget1==null){
            repositoryBudget.save(budget);
            return "Budget modifie";
        }else {
            throw new NotFoundException("Budget existe deja !");
        }
    }

    @Override
    public String supprimer(Long idBudget) {
        Budget budget = repositoryBudget.findByIdBudget(idBudget);
        if (budget==null){
            throw new InvalideException("Identifiant n'existe pas !");
        }else {
            repositoryBudget.deleteById(idBudget);
            return "Identifiant supprimer";
        }
    }

    @Override
    public void montantReduit(Depense depense) {
        int montantDepense = depense.getMontant();
        Budget budget = repositoryBudget.findByIdBudget(depense.getBudgetDepense().getIdBudget());
        int montantBudgetRestant = budget.getMontantRestant();
        if (montantDepense > montantBudgetRestant){
            throw new NotFoundException("Desole votre solde est insuffisant pour ce depense");
        }
        int reste = montantBudgetRestant - montantDepense;
        budget.setMontantRestant(reste);
//        Declanchement du message d'alert
        if (budget.getMontantRestant() <= montantBudgetRestant){
            alertService.envoiAlert(budget);
        }
        repositoryBudget.save(budget);
    }

    @Override
    public void modifieMontantBudget(Depense depense) {
        Budget depenseBudget = repositoryBudget.findByIdBudget(depense.getBudgetDepense().getIdBudget());
        Depense depenseVerif = repositoryDepense.findByIdDepense(depense.getIdDepense());
        int depenseMontant = depense.getMontant();
        int depenseVerifMontant = depenseVerif.getMontant();
        if (depenseMontant != depenseVerifMontant){
            int reste = depenseMontant - depenseVerifMontant;
            depenseBudget.setMontantRestant(depenseBudget.getMontantRestant()-reste);
            repositoryBudget.save(depenseBudget);
        }
    }

    @Override
    public void supprmerMontantBudget(Depense depense) {
        Budget depenseBudget = repositoryBudget.findByIdBudget(depense.getBudgetDepense().getIdBudget());
    }

}
