package GestionPersonnel.gestionBugetMAJ.controllers;


import GestionPersonnel.gestionBugetMAJ.entites.Budget;
import GestionPersonnel.gestionBugetMAJ.services.IBudgetService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/budget")
@AllArgsConstructor
public class BudgetController {

    private IBudgetService budgetService;

    @PostMapping("/definir")
    private String creer(@Valid @RequestBody Budget budget){
        return budgetService.creer(budget);
    }

    @GetMapping("/afficher")
    private List<Budget> afficher(){
        return budgetService.afficher();
    }

    @GetMapping("/lire")
    private Budget lire(@RequestParam Long idBudget){
        return budgetService.lire(idBudget);
    }

    @PutMapping("/modifier")
    private String modifier(@RequestBody Budget budget){
        budgetService.modifier(budget);
        return "Budget modifier";
    }

    @DeleteMapping("/supprimer")
    private String supprimer(@RequestParam Long idBudget){
        budgetService.supprimer(idBudget);
        return "Budget supprimer";
    }
}
