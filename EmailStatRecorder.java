package assignment1;

public class EmailStatRecorder implements Observer{
    @Override
    public void update() {
        System.out.println("an email is received at "+ NewDate.getCurrentTime());
    }
}
