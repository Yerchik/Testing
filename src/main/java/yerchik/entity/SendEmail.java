package yerchik.entity;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by Yerchik on 09.04.2017.
 */
public class SendEmail {
    public static void sendEmail(String email, String name, String login){
        final String username = "yerchik91@gmail.com";
        final String password = "BUBUBUBU";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject("Confirm your email");
            message.setText("Hi " + name + ". Please ,confirm your email: http://localhost:8080/" + login);

            Transport.send(message);



        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
