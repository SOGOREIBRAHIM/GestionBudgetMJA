package GestionPersonnel.gestionBugetMAJ.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Depense {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDepense;

    @Column(name = "montant")
    @NotNull(message = "Remplissez les champs vides")
   // @Max(value = 100, message = "Montant trop eleve")
    private int montant;

    @Column(name = "description")
    @NotNull(message = "Remplissez les champs vides")
    @Size(max = 100, message = "Texte trop long")
    private String description;

    @Column(name = "date")
    @NotNull(message = "Remplissez les champs vides")
    private LocalDate date;

    @ManyToOne
    private Utilisateur utilisateurDepense;

    @ManyToOne
    private TypeDepense typeDepense;

    @ManyToOne
    private Budget budgetDepense;



}
