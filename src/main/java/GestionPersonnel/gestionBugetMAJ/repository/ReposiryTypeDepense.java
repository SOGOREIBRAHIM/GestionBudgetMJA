package GestionPersonnel.gestionBugetMAJ.repository;

import GestionPersonnel.gestionBugetMAJ.entites.TypeDepense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReposiryTypeDepense extends JpaRepository<TypeDepense,Long> {
    TypeDepense findByNomTypeDepense(String nomTypeDepense);

    TypeDepense findByIdTypeDepense(Long idTypeDepense);
}
