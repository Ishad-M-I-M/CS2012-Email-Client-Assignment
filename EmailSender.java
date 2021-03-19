package assignment1;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender extends EmailServer{
    private static final String from = "ishadijaz@gmail.com";

    public EmailSender(){

    }

    public EmailSender(String gmail_username, String password) {
        super(gmail_username, password);
    }

    @Override
    void setProperties(Properties properties) {
        // setting the properties for object
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
    }

    @Override
    Session startSession() {
        // intializing session for object
        session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(username, password);
            }
        });
        return session;
    }

    public void send(Email email){
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email.getTo_or_from()));
            message.setSubject(email.getSubject());
            message.setText(email.getContent());
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
