package GestionPersonnel.gestionBugetMAJ.controllers;

import GestionPersonnel.gestionBugetMAJ.entites.TypeDepense;
import GestionPersonnel.gestionBugetMAJ.services.ITypeDepenseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/typeDepense")
public class TypeDepenseController {
   private ITypeDepenseService typeDepenseService;

   @PostMapping("/creer")
   private String creer(@RequestBody TypeDepense typeDepense){
       return typeDepenseService.creer(typeDepense);
   }

   @PutMapping("/modifier")
   private String modifier(@RequestBody TypeDepense typeDepense){
       return typeDepenseService.modifier(typeDepense);
   }

   @GetMapping("/afficher")
   private List<TypeDepense> typeDepenseslist(){
       return typeDepenseService.afficher();
    }

    @GetMapping("/lire")
    private TypeDepense lireParID(@RequestParam Long idTypeDepense){
       return typeDepenseService.lire(idTypeDepense);
    }

    @DeleteMapping("/delete")
    private String delete(@RequestParam Long idTypeDepense){
       return typeDepenseService.supprimer(idTypeDepense);
    }
}
