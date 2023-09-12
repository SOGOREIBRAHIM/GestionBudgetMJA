package GestionPersonnel.gestionBugetMAJ.services;



import GestionPersonnel.gestionBugetMAJ.emailConfig.EmailDetails;
import GestionPersonnel.gestionBugetMAJ.entites.Budget;
import GestionPersonnel.gestionBugetMAJ.entites.Depense;

import java.util.List;

public interface IBudgetService {

//    Definir un budget
    String creer(Budget budget);

    List<Budget> afficher();

    Budget lire(Long idBudget);

    String modifier(Budget budget);

    String supprimer(Long idBudget);

    void montantReduit(Depense depense);

    void modifieMontantBudget(Depense depense);

    void supprmerMontantBudget(Depense depense);

}
