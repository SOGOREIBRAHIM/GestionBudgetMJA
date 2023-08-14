package GestionPersonnel.gestionBugetMAJ.services;



import GestionPersonnel.gestionBugetMAJ.entites.Budget;
import GestionPersonnel.gestionBugetMAJ.entites.Categorie;
import GestionPersonnel.gestionBugetMAJ.entites.Depense;
import GestionPersonnel.gestionBugetMAJ.entites.Utilisateur;
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
        Categorie categorie = budget.getCategorieBudget();
        Utilisateur utilisateur = budget.getUtilisateurBudget();
        LocalDate dateDebut = budget.getDateDebut();
        LocalDate dateDeFin = dateDebut.with(TemporalAdjusters.lastDayOfMonth());
        budget.setDateFin(dateDeFin);
        LocalDate dateToDate = LocalDate.now();


        Budget budgetVerif = null;

        budgetVerif = repositoryBudget.findByUtilisateurBudgetAndCategorieBudgetAndDateFin(utilisateur,categorie,dateDeFin);
        if (budgetVerif!=null)
            throw new NotFoundException("Desole, cet budget existe déjà");

        int montantAlert =budget.getMontantAlert();
        int montantPrincipal = budget.getMontant();
        int montantRestant = budget.getMontantRestant();

        if (montantPrincipal < montantAlert)
            throw new NotFoundException("Desole, montant alert est superieur au montant principal");

//        if (montantRestant!=montantPrincipal)
//            throw new NotFoundException("Desole, montant restant est different du montant principal");

//        if (dateDebut.isBefore(dateToDate)||dateDebut.getYear()!=dateToDate.getYear())
//            throw new NotFoundException("Veuillez entrer une date valide  !!!");

        if (dateDebut.isBefore(dateToDate))
            throw new NotFoundException("Veuillez entrer la date d'aujourd'hui !!!");

        if (dateDebut.getYear()!=dateToDate.getYear())
            throw new NotFoundException("Vous ne pouvez pas prevoir un budget au dela de un an");

        LocalDate dateBudgetPrecedent = dateDeFin.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
        Budget budgetPrecedent = repositoryBudget.findByUtilisateurBudgetAndCategorieBudgetAndDateFin(utilisateur,categorie,dateBudgetPrecedent);
        if (budgetPrecedent!=null){
            if (dateBudgetPrecedent.isBefore(dateToDate)){
                int montantRestantPrecedentBudget = budgetPrecedent.getMontantRestant();
                if (montantRestantPrecedentBudget>0){
                    int montantActuelBudget = budget.getMontant() + montantRestantPrecedentBudget;
                    int montantRestantActuelBudget = budget.getMontantRestant() + montantRestantPrecedentBudget;
                    budget.setMontant(montantActuelBudget);
                    budget.setMontantRestant(montantRestantActuelBudget);
                }
            }
        }
        budget.setMontantRestant(montantPrincipal);
        repositoryBudget.save(budget);
        return "Budget defini !!";
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
            Budget dateVerif = repositoryBudget.findByDateDebutAndDateFin(budget.getDateDebut(),budget.getDateFin());
        if (dateVerif==null){
            repositoryBudget.save(budget);
            return "Budget modifie";
        } else {
            throw new NotFoundException("Budget existe deja !");
        }
    }

    @Override
    public String supprimer(Long idBudget) {
        Budget budget = repositoryBudget.findByIdBudget(idBudget);
        if (budget!=null){
            List<Depense> depenseBudget = repositoryDepense.findByBudgetDepenseIdBudget(idBudget);
            if (depenseBudget!=null){
                throw new InvalideException("Une depense est associe a cet budget !");
            }
        }
        repositoryBudget.deleteById(idBudget);
        return "Identifiant supprimer";
//        Budget budget = repositoryBudget.findByIdBudget(idBudget);
//        if (budget==null){
//            throw new InvalideException("Identifiant n'existe pas !");
//        }else {
//            repositoryBudget.deleteById(idBudget);
//            return "Identifiant supprimer";
//        }
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
        Budget budget = depense.getBudgetDepense();
        budget.setMontantRestant(budget.getMontantRestant() + depense.getMontant());
    }

}
