package assignment1;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {
    private static final String username = "ishadijaz@gmail.com";
    private static final String password = "al2045818";

    private Properties properties;
    private final Session session;
    private static final String from = "ishadijaz@gmail.com";

    EmailSender(){

        setProperties();
        // intializing session for object
        session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(username, password);
            }
        });

    }

    private void setProperties(){
        // setting the properties for object
        properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
    }

    public void send(Email email){
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email.getTo()));
            message.setSubject(email.getSubject());
            message.setText(email.getContent());
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
