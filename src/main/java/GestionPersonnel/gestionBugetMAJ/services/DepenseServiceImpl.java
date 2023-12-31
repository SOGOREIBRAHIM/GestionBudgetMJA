package GestionPersonnel.gestionBugetMAJ.services;

import GestionPersonnel.gestionBugetMAJ.entites.Budget;
import GestionPersonnel.gestionBugetMAJ.entites.Depense;
import GestionPersonnel.gestionBugetMAJ.entites.TypeDepense;
import GestionPersonnel.gestionBugetMAJ.entites.Utilisateur;
import GestionPersonnel.gestionBugetMAJ.exception.DuplicateException;
import GestionPersonnel.gestionBugetMAJ.exception.NotFoundException;
import GestionPersonnel.gestionBugetMAJ.repository.RepositoryBudget;
import GestionPersonnel.gestionBugetMAJ.repository.RepositoryDepense;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
@AllArgsConstructor
public class DepenseServiceImpl implements IDepenseService{
    private RepositoryDepense repositoryDepense;
    private IBudgetService budgetService;

    @Override
    public Depense effetuer(Depense depense) {

        LocalDate dateDebutBudget = depense.getBudgetDepense().getDateDebut();
        LocalDate dateDepense = depense.getDate();
        Utilisateur utilisateur = depense.getUtilisateurDepense();
//        if (utilisateur==null)
//            throw new NotFoundException("Entrez un utilisateur");
        Budget budget = depense.getBudgetDepense();
        TypeDepense typeDepense = depense.getTypeDepense();

        Depense depenseVerif = null;

        depenseVerif = repositoryDepense.findByDescription(depense.getDescription());
        if (depenseVerif!=null)
            throw new NotFoundException("Depense existe deja !");

        if (dateDepense.isAfter(LocalDate.now()) || dateDepense.isBefore(dateDebutBudget))
           throw new NotFoundException("Verifier la date de votre systeme !");

        switch (typeDepense.getNomTypeDepense()){
            case "quotidient":
                depenseVerif = repositoryDepense.findByUtilisateurDepenseAndTypeDepenseAndBudgetDepenseAndDate(utilisateur,typeDepense,budget,dateDepense);
                if (depenseVerif!=null)
                    throw new NotFoundException("Vous avez deja effectue une depense aujourd'hui");
                budgetService.montantReduit(depense);
                break;

            case "hebdomadaire":
                depenseVerif = repositoryDepense.findFirstByUtilisateurDepenseAndTypeDepenseAndBudgetDepenseOrderByDateDesc(utilisateur,typeDepense,budget);
                if (depenseVerif!=null)
                       if (depenseVerif.getDate().plusDays(7).isAfter(LocalDate.now()))
                        throw new NotFoundException("Vous avez deja effectue une depense cette semaine");
                budgetService.montantReduit(depense);
                break;

            case "mensuel":
                depenseVerif = repositoryDepense.findFirstByUtilisateurDepenseAndTypeDepenseAndBudgetDepenseOrderByDateDesc(utilisateur,typeDepense,budget);
                if (depenseVerif!=null)
                    if (depenseVerif.getDate().plusDays(30).isAfter(LocalDate.now()))
                        throw new NotFoundException("Vous avez deja effectue une depense cet mois");
                budgetService.montantReduit(depense);
                break;

            default:
                throw new NotFoundException("Le type de votre depense n'existe pas");
        }
         repositoryDepense.save(depense);
        return repositoryDepense.findByIdDepense(depense.getIdDepense());
    }

    @Override
    public String modiffier(Depense depense) {
        Depense depense1 = repositoryDepense.findByDescription(depense.getDescription());
        if (depense1==null ){
            throw new DuplicateException("Depense existe deja");
        }
        budgetService.modifieMontantBudget(depense);
        repositoryDepense.save(depense);
        return "depense modifier";
    }

    @Override
    public List<Depense> afficher() {
        return repositoryDepense.findAll();
    }

    @Override
    public String supprimer(Long idDepense) {
        Depense depense =repositoryDepense.findByIdDepense(idDepense);
        if (depense!=null){
            budgetService.supprmerMontantBudget(depense);
            repositoryDepense.deleteById(idDepense);
            return "depense supprimer";
        } throw new DuplicateException("depense avec ID "+idDepense+" n'existe pas");
    }

    @Override
    public Depense lire(Long idDepense) {
        return repositoryDepense.findById(idDepense).orElseThrow(
                ()-> new DuplicateException("depense non trouver")
        );
    }
}
