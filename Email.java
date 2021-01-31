package assignment1;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.Serializable;

import java.util.Date;
import java.util.Properties;

class Email implements Serializable {

    private String to;
    private String subject;
    private String content;
    private Date date;

    Email(String to, String subject, String content, Date date){
        this.to = to;
        this.subject = subject;
        this.content = content;
        this.date = date;

    }

    public void printDetails(){
        System.out.println("Sent to :"+to+ ", Subject :"+subject +", Content :"+content );
    }

    public String getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

}
