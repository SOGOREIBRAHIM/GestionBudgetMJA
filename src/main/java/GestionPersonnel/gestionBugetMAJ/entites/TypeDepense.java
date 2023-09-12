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
public class TypeDepense {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTypeDepense;

    @Column(name = "nomTypeDepense")
    @NotNull(message = "Remplissez les champs vides")
    @Size(max = 50, message = "Texte trop long")
    private String nomTypeDepense;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Utilisateur utilisateurType;

    @OneToMany(mappedBy = "typeDepense")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Depense> depensesType;

}
