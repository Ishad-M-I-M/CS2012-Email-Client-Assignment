package assignment1;

public class EmailStatPrinter implements Observer{
    private static String fileName = "recievedLog.txt";
    @Override
    public void update() {
        FileReaderWriter.write(fileName, "an email is received at "+ NewDate.getCurrentTime());
    }
}
