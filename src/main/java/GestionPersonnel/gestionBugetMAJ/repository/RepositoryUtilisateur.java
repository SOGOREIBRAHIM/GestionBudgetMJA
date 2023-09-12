package GestionPersonnel.gestionBugetMAJ.repository;


import GestionPersonnel.gestionBugetMAJ.entites.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryUtilisateur extends JpaRepository<Utilisateur,Long> {

    //  chercher un user avec son email
    Utilisateur findByEmail(String email);

    //  chercher un user avec son id
    Utilisateur findByIdUser(Long idUser);

    //  chercher un user avec son email et password
    Utilisateur findByEmailAndMotDePasse(String email, String password);
}
