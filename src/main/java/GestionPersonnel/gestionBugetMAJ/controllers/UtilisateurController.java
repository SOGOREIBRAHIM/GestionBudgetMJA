package GestionPersonnel.gestionBugetMAJ.controllers;



import GestionPersonnel.gestionBugetMAJ.entites.Utilisateur;
import GestionPersonnel.gestionBugetMAJ.services.IUtilisateurService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("utilisateur")
@AllArgsConstructor
public class UtilisateurController {

    private IUtilisateurService utilisateurService;

    @PostMapping("/inscrire")
    private String inscrire(@RequestBody Utilisateur utilisateur ){
        return utilisateurService.inscrire(utilisateur);
    }

    @GetMapping("/connexion")
    private String connexion(@RequestParam String email, @RequestParam String motDePasse){
        return utilisateurService.connexion(email,motDePasse);
    }

    @GetMapping("/afficher")
    private List<Utilisateur> afficher(){
        return utilisateurService.afficherAll();
    }

    @GetMapping("/lire")
    private Utilisateur lire(@RequestParam Long idUser){
        return utilisateurService.lire(idUser);
    }

    @PutMapping("/modifier")
    private String modifier(@RequestBody Utilisateur utilisateur){
       return utilisateurService.modifier(utilisateur);
    }

    @DeleteMapping("/supprimer")
    private String supprimer(@RequestParam Long idUser){
        return utilisateurService.supprimer(idUser);
    }

}
