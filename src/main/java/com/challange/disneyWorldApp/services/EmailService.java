

package com.challange.disneyWorldApp.services;

import static com.challange.disneyWorldApp.constants.SendGridConstants.SENDGRID_API_KEY;



import java.io.IOException;
import org.springframework.stereotype.Service;
import com.sendgrid.*;
 
@Service
public class EmailService {

        public void SendMail (String destinatario)throws Exception{
            Email from = new Email("pablo.frankenn@gmail.com");
            String subject = "DisneyWorld Register";
            Email to = new Email(destinatario);
            Content content = new Content("text/plain", "Te registraste exitosamente");
            Mail mail = new Mail(from, subject, to, content);

            SendGrid sg = new SendGrid(SENDGRID_API_KEY);
            Request request = new Request();
            try {
                request.setMethod(Method.POST);
                request.setEndpoint("mail/send");
                request.setBody(mail.build());
                Response response = sg.api(request);
                System.out.println(response.getStatusCode());
                System.out.println(response.getBody());
                System.out.println(response.getHeaders());
             } catch (IOException ex) {
            throw ex;
            }
        }
}


