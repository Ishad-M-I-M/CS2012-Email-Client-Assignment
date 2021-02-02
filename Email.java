package assignment1;


import java.io.Serializable;

import java.util.Date;


public class Email implements Serializable {

    private String to;
    private String subject;
    private String content;
    private String date;

    public Email(String to, String subject, String content, String date){
        this.to = to;
        this.subject = subject;
        this.content = content;
        this.date = date;

    }

    public void printDetails(){
        System.out.println("Sent to :"+to+ ", Subject :"+subject +", Content :"+content + ", sent on :"+date );
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

    public String getDate() {
        return date;
    }

    public void send(){
//        SendEmail se = new SendEmail();
//        se.send(this);
        System.out.println("Email sent to : "+ to);
    }

}
