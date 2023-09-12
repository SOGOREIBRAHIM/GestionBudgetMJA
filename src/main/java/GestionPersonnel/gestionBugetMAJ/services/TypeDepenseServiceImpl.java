package GestionPersonnel.gestionBugetMAJ.services;

import GestionPersonnel.gestionBugetMAJ.entites.TypeDepense;
import GestionPersonnel.gestionBugetMAJ.exception.InvalideException;
import GestionPersonnel.gestionBugetMAJ.exception.NotFoundException;
import GestionPersonnel.gestionBugetMAJ.repository.ReposiryTypeDepense;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TypeDepenseServiceImpl implements ITypeDepenseService{

    private ReposiryTypeDepense reposiryTypeDepense;

    @Override
    public String creer(TypeDepense typeDepense) {
        TypeDepense typeDepense1= reposiryTypeDepense.findByNomTypeDepense(typeDepense.getNomTypeDepense());
        if (typeDepense1==null){
            reposiryTypeDepense.save(typeDepense);
            return "type de depense enregistrer";
        }
        throw new NotFoundException("Ce Type de depense existe deja");
    }

    @Override
    public String modifier(TypeDepense typeDepense) {
       TypeDepense typeDepense1 = reposiryTypeDepense.findByNomTypeDepense(typeDepense.getNomTypeDepense());
       if(typeDepense1==null){
           reposiryTypeDepense.save(typeDepense);
           return "Type depense modifier";
       }
       throw new NotFoundException("Type depense existe deja");
    }

    @Override
    public String supprimer(Long idTypeDepense) {
       TypeDepense typeDepense = reposiryTypeDepense.findByIdTypeDepense(idTypeDepense);
        if(typeDepense!= null){
            reposiryTypeDepense.deleteById(idTypeDepense);
            return "Type depense supprimer";
        }
        throw new InvalideException("Identifiant non trouver");
    }

    @Override
    public List<TypeDepense> afficher() {
        return reposiryTypeDepense.findAll();
    }

    @Override
    public TypeDepense lire(Long idTypeDepense) {
        return reposiryTypeDepense.findById(idTypeDepense).orElseThrow(
                () -> new InvalideException("Identifiant non trouver")
        );
    }
}
