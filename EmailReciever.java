package assignment1;
import java.util.Arrays;
import java.util.Properties;

import javax.mail.*;
import javax.mail.search.FlagTerm;

public class EmailReciever extends EmailServer{
    private static final String host = "imap.gmail.com";

    public EmailReciever(){

    }

    public EmailReciever(String gmail_username, String password) {
        super(gmail_username, password);
    }

    public static void main(String[] args) {
        EmailReciever er = new EmailReciever();
        try {
            er.printRecievedEmails(er.recieveEmails());
        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }

    @Override
    void setProperties(Properties properties){
        properties.put("mail.imap.host", "imap.gmail.com");
        properties.put("mail.imap.port", "993");
        properties.put("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.imap.socketFactory.fallback","false");
        properties.setProperty("mail.imap.socketFactory.port", "993");
    }

    @Override
    Session startSession() {
        session = javax.mail.Session.getInstance(properties);
        return session;
    }

    public Message[] recieveEmails() throws MessagingException {
        Store store = session.getStore("imap");
        store.connect(host,username,password);
        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_WRITE);

        return inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));

    }

    public void printRecievedEmails(Message[] messages) throws MessagingException {
        for(Message mes : messages){
            mes.setFlag(Flags.Flag.SEEN, true);
            System.out.println(Arrays.toString(mes.getFrom()));
            System.out.println(mes.getSubject());
            System.out.println();
        }
    }

}