package assignment1;

import javax.mail.Session;
import java.util.Properties;

public abstract class EmailServer extends Thread{
    String username ;
    String password ;

    Properties properties;
    Session session;
    public EmailServer(String gmail_username, String password){
        this.username = gmail_username;
        this.password = password;
        this.properties = new Properties();
        setProperties(properties);
        this.session = startSession();
    }

    public EmailServer(){
        this.username = "ishadijaz@gmail.com";
        this.password = "al2045818";
        this.properties = new Properties();
        setProperties(properties);
        this.session = startSession();
    }

    abstract void setProperties(Properties properties);
    abstract Session startSession();


}
