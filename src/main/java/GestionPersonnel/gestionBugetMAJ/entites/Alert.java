package GestionPersonnel.gestionBugetMAJ.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Alert {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAlert;

//    @Column(name = "montantAtteind")
//    @NotNull(message = "Remplissez les champs vides")
//    @Max(value = 100, message = "Montant trop eleve")
//    private int montantAtteind;

    @Column(name = "texte")
    @NotNull(message = "Remplissez les champs vides")
//    @Max(value = 100, message = "Montant trop eleve")
    private String texte;

//    @Column(name = "motDePasse")
//    private boolean etat;

    @ManyToOne
    private Utilisateur utilisateurAlert;

    @ManyToOne
    private Budget budgetAlert;
}
