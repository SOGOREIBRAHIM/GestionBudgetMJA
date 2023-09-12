package GestionPersonnel.gestionBugetMAJ.repository;


import GestionPersonnel.gestionBugetMAJ.entites.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryCategorie extends JpaRepository<Categorie,Long> {
    Categorie findByNom(String nom);

}
