package GestionPersonnel.gestionBugetMAJ.services;



import GestionPersonnel.gestionBugetMAJ.entites.Categorie;
import GestionPersonnel.gestionBugetMAJ.exception.NotFoundException;
import GestionPersonnel.gestionBugetMAJ.repository.RepositoryCategorie;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategorieServiceImpl implements ICategorieService {

    private RepositoryCategorie repositoryCategorie;


    @Override
    public String creer(Categorie categorie) {
        Categorie categorie1 = repositoryCategorie.findByNomAndUtilisateurCategorie(categorie.getNom(),categorie.getUtilisateurCategorie());
        if (categorie1==null){
            repositoryCategorie.save(categorie);
            return "Categorie enregistre";
        }
        throw new NotFoundException("Categorie existe deja");
    }

    @Override
    public String modifier(Categorie categorie) {
        Categorie categorie1 = repositoryCategorie.findByNomAndUtilisateurCategorie(categorie.getNom(),categorie.getUtilisateurCategorie());
        if (categorie1==null){
            repositoryCategorie.save(categorie);
            return "Categorie modifier";
        }
        throw new NotFoundException("Categorie existe deja un categorie");
    }

    @Override
    public List<Categorie> afficher() {
        return repositoryCategorie.findAll();
    }

    @Override
    public Categorie lire(Long idCategorie) {
        return repositoryCategorie.findById(idCategorie).orElseThrow(
                ()-> new NotFoundException("Identifiant n'existe pas")
        );
    }

    @Override
    public String supprimer(Long idCategorie) {
        Categorie categorie = repositoryCategorie.findByIdCategory(idCategorie);
        int test = 0;
        if (categorie!=null){
            repositoryCategorie.deleteById(idCategorie);
            return "Categorie supprimer";
        }
        throw new NotFoundException("Identifiant n'existe pas");
    }
}
