package GestionPersonnel.gestionBugetMAJ.emailConfig;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data @AllArgsConstructor @NoArgsConstructor
public class EmailDetails {

    // Class data members
    private String destinateur;
    private String msg;
    private String objet;
}
