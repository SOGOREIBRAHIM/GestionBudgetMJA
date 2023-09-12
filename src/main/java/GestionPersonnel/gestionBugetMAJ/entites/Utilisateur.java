package GestionPersonnel.gestionBugetMAJ.entites;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Table(name = "utilisateur")
public class Utilisateur {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @Column(name = "nom")
    @NotNull(message = "Remplissez les champs vides")
    @Size(max = 50, message = "Texte trop long")
    private String nom;

    @Column(name = "prenom")
    @NotNull(message = "Remplisser les champs vides")
    @Size(max = 50, message = "Texte trop long")
    private String prenom;

    @Column(name = "email")
    @NotNull(message = "Remplissez les champs vides")
    @Size(max = 50, message = "Texte trop long")
    @Email(message = "Entrer un email valide")
    private String email;

    @Column(name = "motDePasse")
    @NotNull(message = "Remplissez les champs vides")
    @Size(max = 20, message = "Texte trop long")
    private String motDePasse;

    @OneToMany(mappedBy = "utilisateurDepense")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Depense> depensesUser;

    @OneToMany(mappedBy = "utilisateurAlert")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Alert> alertUser;

    @OneToMany(mappedBy = "utilisateurCategorie")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)      // Json pour gerer l'affichage
    private List<Categorie> CategorieUser;

    @OneToMany(mappedBy = "utilisateurBudget")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Budget> BudgetUser;

    @OneToMany(mappedBy = "utilisateurType")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<TypeDepense> typeDepenses;

}
