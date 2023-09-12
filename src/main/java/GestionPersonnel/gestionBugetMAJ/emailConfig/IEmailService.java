package GestionPersonnel.gestionBugetMAJ.emailConfig;

public interface IEmailService {

    // envoyer un simple email
    String sendSimpleMail(EmailDetails details);
}
