package assignment1;

import java.io.*;
import java.util.ArrayList;

public final class EmailIO {
    private EmailIO(){

    }

    public static void storeEmails(ArrayList<Email> emailsSent) {

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("emailLog.ser"));
            for (Email e: emailsSent){
                oos.writeObject(e);
            }
            oos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void storeEmail(Email email) throws IOException {
        File file = new File("emailLog.ser");
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



    public static ArrayList<Email> readEmailsFromLog(String fileName) throws IOException {
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

    public static ArrayList<Email> readEmailsFromLog(){
        File file = new File("emailLog.ser");
        if (file.exists()){
            try{
                return readEmailsFromLog("emailLog.ser");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return new ArrayList<>();
    }
}
