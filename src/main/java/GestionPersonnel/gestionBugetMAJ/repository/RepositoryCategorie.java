package GestionPersonnel.gestionBugetMAJ.repository;


import GestionPersonnel.gestionBugetMAJ.entites.Categorie;
import GestionPersonnel.gestionBugetMAJ.entites.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryCategorie extends JpaRepository<Categorie,Long> {
    Categorie findByNomAndUtilisateurCategorie(String nom, Utilisateur utilisateur);
    Categorie findByIdCategory(Long idCategory);


}
