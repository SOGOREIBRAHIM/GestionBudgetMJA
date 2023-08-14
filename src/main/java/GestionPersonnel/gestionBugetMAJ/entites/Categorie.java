package GestionPersonnel.gestionBugetMAJ.entites;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Categorie {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategory;

    @Column(name = "nom")
    @NotNull(message = "Remplisser les champs vides")
    @Size(max = 50, message = "Texte trop long")
    private String nom;

    @OneToMany(mappedBy = "categorieBudget")
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Budget> budgetCategorie;

    @ManyToOne
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Utilisateur utilisateurCategorie;


}
