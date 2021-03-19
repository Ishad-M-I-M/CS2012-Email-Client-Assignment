package assignment1;


import java.io.Serializable;

public class Email implements Serializable {

    private String to_or_from;
    private String subject;
    private String content;
    private String date;

    public Email(String to_or_from, String subject, String content, String date){
        this.to_or_from = to_or_from;
        this.subject = subject;
        this.content = content;
        this.date = date;

    }

    public void printDetailsSent(){
        System.out.println("Sent to :"+to_or_from+ ", Subject :"+subject +", Content :"+content + ", sent on :"+date );
    }

    public String getTo_or_from() {
        return to_or_from;
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

    public void send()  {
        EmailSender sender = new EmailSender();
        sender.send(this);
        System.out.println(" ------------------------------------------------------------------------------- Email sent to : "+ to_or_from);
    }

}
