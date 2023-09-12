package GestionPersonnel.gestionBugetMAJ.controllers;


import GestionPersonnel.gestionBugetMAJ.entites.Categorie;
import GestionPersonnel.gestionBugetMAJ.services.ICategorieService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("categorie")
@AllArgsConstructor
public class CategorieController {

    private ICategorieService categorieService;

    @PostMapping("/creer")
    private String creer(@RequestBody Categorie categorie){
        return categorieService.creer(categorie);
    }

    @PutMapping("/modifier")
    private String modifier(@RequestBody Categorie categorie){
        return categorieService.modifier(categorie);
    }
    @GetMapping("/afficher")
    private List<Categorie> categoriesList(){
        return categorieService.afficher();
    }

//    @GetMapping("/lires/{idCategorie}")
//    private Categorie lireParId(@PathVariable Long idCategorie){
//        return categorieService.lire(idCategorie);
//    }

    @GetMapping("/lire")
    private Categorie lire(@RequestParam Long idCategorie){
        return categorieService.lire(idCategorie);
    }

    @DeleteMapping("/supprimer")
    private String supprimer(@RequestParam Long idCategorie){
        return categorieService.supprimer(idCategorie);
    }

    @DeleteMapping("/delete/{idCategorie}")
    private String supprimerId(@PathVariable Long idCategorie){
        return categorieService.supprimer(idCategorie);
    }


}
