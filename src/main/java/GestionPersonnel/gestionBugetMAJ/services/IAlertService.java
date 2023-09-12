package GestionPersonnel.gestionBugetMAJ.services;


import GestionPersonnel.gestionBugetMAJ.entites.Alert;
import GestionPersonnel.gestionBugetMAJ.entites.Budget;

public interface IAlertService {

    Alert save(Alert alert);

    void envoiAlert(Budget budget);

}
