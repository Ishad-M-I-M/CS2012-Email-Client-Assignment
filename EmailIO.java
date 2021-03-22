package assignment1;

import java.io.*;
import java.util.ArrayList;

// class to handle the functionalities regarding Email object serialization and deserialization
public class EmailIO {
    private static String emailLog_sent =  "sentEmailLog.ser";
    private static String emailLog_recieved = "receivedEmailLog.ser";
    // made constructor private for not to allow to create a object from outside
    private EmailIO(){}

    private static void storeEmail(Email email, String fileName) throws IOException {
        File file = new File(fileName);
        if (file.exists()){
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file, true)){
                protected void writeStreamHeader() throws IOException {
                    reset();
                }
            };
            oos.writeObject(email);
        }
        else{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(email);
        }
    }


    private static ArrayList<Email> readEmailsFromLog(String fileName) throws IOException {
        ArrayList<Email> emails = new ArrayList<>();
        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);
        while (true){
            try {
                emails.add((Email) ois.readObject());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (EOFException e){
                ois.close();
                break;
            }
        }
        return emails;
    }

    private static ArrayList<Email> readEmailsFromFile(String fileName){
        File file = new File(fileName);
        if (file.exists()){
            try{
                return readEmailsFromLog(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return new ArrayList<>();
    }

    public static ArrayList<Email> readSentEmailsFromLog(){
        return readEmailsFromFile(emailLog_sent);
    }

    public static ArrayList<Email> readRecievedEmailsFromLog(){
        return readEmailsFromFile(emailLog_recieved);
    }


    public static void storeSentEmail(Email email) throws IOException {
        storeEmail(email, emailLog_sent );
    }

    public static boolean storeRecievedEmail(Email email) throws IOException {
        storeEmail(email, emailLog_recieved );
        return true;
    }
}
