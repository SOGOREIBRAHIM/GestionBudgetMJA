package GestionPersonnel.gestionBugetMAJ.emailConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("email")
public class EmailController {

        @Autowired
        private IEmailService iemailService;

        // Sending a simple Email
        @PostMapping("/envoiEmail")
        public String sendMail(@RequestBody EmailDetails details) {
            String status = iemailService.sendSimpleMail(details);
            return status;
        }

}
