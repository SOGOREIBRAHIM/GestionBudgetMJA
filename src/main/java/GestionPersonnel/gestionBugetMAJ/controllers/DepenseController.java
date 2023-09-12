package GestionPersonnel.gestionBugetMAJ.controllers;

import GestionPersonnel.gestionBugetMAJ.entites.Depense;
import GestionPersonnel.gestionBugetMAJ.repository.RepositoryDepense;
import GestionPersonnel.gestionBugetMAJ.services.IDepenseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/depense")
@AllArgsConstructor
public class DepenseController {
    private IDepenseService depenseService;

    @PostMapping("/effectuer")
    private Depense creer(@Valid @RequestBody Depense depense){
        return depenseService.effetuer(depense);
    }

    @PutMapping("/modiffier")
    private String modifier(@Valid @RequestBody Depense depense){
        return depenseService.modiffier(depense);
    }
    @GetMapping("/liste")
    private List list(Depense depense){
        return depenseService.afficher();
    }
    @DeleteMapping("/supprimer/{idDepense}")
    private String supprimer(@PathVariable Long idDepense){
        return depenseService.supprimer(idDepense);
    }
    @GetMapping("/lire/{idDepense}")
    private Depense lire(@PathVariable Long idDepense){
        return  depenseService.lire(idDepense);
    }
}
