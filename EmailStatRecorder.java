package assignment1;

import java.util.Date;

public class EmailStatRecorder implements Observer{
    @Override
    public void update(Date date) {
        System.out.println("an email is received at "+ NewDate.getTime(date));
    }
}
