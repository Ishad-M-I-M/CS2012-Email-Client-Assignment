package assignment1;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.*;
import javax.mail.search.FlagTerm;

public class EmailReceiver extends EmailServer{
    private static final String host = "imap.gmail.com";
    private boolean alive;
    private List<Observer> observers;
    private NewBlockingQueue blockingQueue;
    public EmailReceiver(NewBlockingQueue newBlockingQueue){
        this.alive = true;
        this.observers = new ArrayList<>();
        this.blockingQueue = newBlockingQueue;
    }

    public EmailReceiver(String gmail_username, String password, NewBlockingQueue newBlockingQueue) {
        super(gmail_username, password);
        this.alive = true;
        this.observers = new ArrayList<>();
        this.blockingQueue = newBlockingQueue;
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

    private Message[] recieveEmails() throws MessagingException {
        Store store = session.getStore("imap");
        store.connect(host,username,password);
        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_WRITE);

        return inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));

    }

    private void pushReceivedEmails() throws MessagingException {
        Message[] messages = recieveEmails();
        for(Message mes : messages){
            mes.setFlag(Flags.Flag.SEEN, true);
            try {
                Email email = new Email((mes.getFrom()[0]).toString(),
                        mes.getSubject(),
                        mes.getContent().toString(),
                        (new NewDate(mes.getReceivedDate())).toString());
                notifyAllObservers();
                blockingQueue.enqueue(email);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void kill(){
        this.alive = false;
        interrupt();
    }

    private void notifyAllObservers(){
        for( Observer observer: observers){
            observer.update();
        }
    }

    public void attach(Observer observer){
        observers.add(observer);
    }

    public void dettach(Observer observer){
        observers.remove(observer);
    }

    @Override
    public void run() {
        while (alive){
            try {
                pushReceivedEmails();
                sleep(500);
            } catch (MessagingException me) {
                me.printStackTrace();
            } catch (InterruptedException e) {
                System.out.println("stopped execution of email receiver");
            }
        }

    }
}