package GestionPersonnel.gestionBugetMAJ.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Budget {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBudget;

    @Column(name = "titre")
    @NotNull(message = "Remplisser les champs vides")
    @Size(max = 50, message = "Texte trop long")
    private String titre;

    @Column(name = "montant")
    @NotNull(message = "Remplissez les champs vides")
    @Min(value = 100, message = "Montant trop eleve")
    private int montant;

    @Column(name = "MontantRestant")
    @NotNull(message = "Remplissez les champs vides")
//    @Min(value = 100, message = "Montant trop eleve")
    private int MontantRestant;

    @Column(name = "MontantAlert")
    @NotNull(message = "Remplissez les champs vides")
//    @Min(value = 100, message = "Montant trop eleve")
    private int MontantAlert;

    @Column(name = "dateDebut")
    @NotNull(message = "Remplissez les champs vides")
    @Temporal(TemporalType.DATE)
    private LocalDate dateDebut;

    @Column(name = "dateFin")
    @NotNull(message = "Remplissez les champs vides")
    @Temporal(TemporalType.DATE)
    private LocalDate dateFin;

    @OneToMany(mappedBy = "budgetAlert")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Alert> alertBudget;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Utilisateur utilisateurBudget;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Categorie categorieBudget;

    @OneToMany(mappedBy = "budgetDepense")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Depense> depenseBudget;



}
