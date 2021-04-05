package assignment1;

import java.util.Date;

public class EmailStatPrinter implements Observer{
    private static String fileName = "recievedLog.txt";
    @Override
    public void update(Date date) {
        FileReaderWriter.write(fileName, "an email is received at "+ NewDate.getTime(date));
    }
}
