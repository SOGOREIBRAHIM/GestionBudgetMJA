package GestionPersonnel.gestionBugetMAJ.services;

import GestionPersonnel.gestionBugetMAJ.entites.*;
import GestionPersonnel.gestionBugetMAJ.exception.NotFoundException;
import GestionPersonnel.gestionBugetMAJ.repository.RepositoryAlert;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AlertServiceImpl implements IAlertService{

    private RepositoryAlert repositoryAlert;
    private JavaMailSender javaMailSender;


    @Override
    public Alert save(Alert alert) {
        return null;
    }

    @Override
    public void envoiAlert(Budget budget) {

        int montantAlert = budget.getMontantAlert();
        Utilisateur utilisateur = budget.getUtilisateurBudget();
        Categorie categorie = budget.getCategorieBudget();
        Alert alert = new Alert();
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        String sender = "sogoreibrahimodk@gmail.com";
        String message = null;

        if (montantAlert==0)
           message = "Mr/Mme "+utilisateur.getNom()+", votre budget de "+budget.getMontant()+" " +
                   ",de la categorie "+categorie.getNom()+" est de 0 franc";
        else {
            message = "Mr/Mme "+utilisateur.getNom()+", votre budget "+budget.getMontant()+" " +
                    ",de la categorie "+categorie.getNom()+" atteint le montant d'alerte de"+budget.getMontantAlert();
        }
        alert.setTexte(message);

        try {

            mailMessage.setFrom(sender);
            mailMessage.setTo(utilisateur.getEmail());
            mailMessage.setText(alert.getTexte());
            mailMessage.setSubject("Alert: Gestion de budget personnel !");
            javaMailSender.send(mailMessage);

            alert.setTexte(message);
            repositoryAlert.save(alert);

        }catch (Exception e){
            throw new NotFoundException(e.getMessage());
        }
    }
}
