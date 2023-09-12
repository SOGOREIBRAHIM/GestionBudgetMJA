package GestionPersonnel.gestionBugetMAJ.services;


import GestionPersonnel.gestionBugetMAJ.entites.Utilisateur;
import GestionPersonnel.gestionBugetMAJ.exception.InvalideException;
import GestionPersonnel.gestionBugetMAJ.exception.NotFoundException;
import GestionPersonnel.gestionBugetMAJ.repository.RepositoryUtilisateur;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UtilisateurServiceImpl implements IUtilisateurService {

    private RepositoryUtilisateur repositoryUtilisateur;    //  injection

    @Override
    public String inscrire(Utilisateur utilisateur) {
        Utilisateur utilisateur1 = repositoryUtilisateur.findByEmail(utilisateur.getEmail());
        if (utilisateur1==null){
            repositoryUtilisateur.save(utilisateur);
            return "Utilisateur inscrire !";
        }else {
            throw new NotFoundException("User existe deja !");
        }
    }

    @Override
    public String connexion(String email, String motDePasse) {
        Utilisateur utilisateur = repositoryUtilisateur.findByEmailAndMotDePasse(email,motDePasse);
        if (utilisateur!=null){
            return "Utilisateur connecter !";
        }else {
            throw new NotFoundException("connexion echoue !");
        }
    }

    @Override
    public List<Utilisateur> afficherAll() {
        return repositoryUtilisateur.findAll();
    }

    @Override
    public Utilisateur lire(Long idUser) {
        return  repositoryUtilisateur.findById(idUser).orElseThrow(
                ()-> new InvalideException("Utilisateur n'existe pas")
        );
    }

    @Override
    public String modifier(Utilisateur utilisateur) {
        Utilisateur utilisateur1 = repositoryUtilisateur.findByEmail(utilisateur.getEmail());
        if (utilisateur1!=null){
            repositoryUtilisateur.save(utilisateur);
            return "Utilisateur modifier !";
        }else {
            throw new NotFoundException("User existe deja !");
        }

    }

    @Override
    public String supprimer(Long idUser) {
        Utilisateur utilisateur = repositoryUtilisateur.findByIdUser(idUser);
        if (utilisateur==null){
            throw new InvalideException("User existe deja !");
        }
        repositoryUtilisateur.deleteById(idUser);
        return "Utilisateur supprimer!";
    }
}
