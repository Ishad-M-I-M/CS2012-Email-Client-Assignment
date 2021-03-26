package assignment1;

import java.io.*;
import java.util.ArrayList;

// class to handle functions of all Email recipients
public class EmailRecipientManager {

    private static String fileName = "clientList.txt";
    private EmailRecipientManager(){}

    public static String getFileName() {
        return fileName;
    }
    public static void setFileName(String fileName) {
        EmailRecipientManager.fileName = fileName;
    }


    protected static ArrayList<EmailRecipient> readEmailRecipients(){
        ArrayList<EmailRecipient> emailRecipients = new ArrayList<>();
        for( Object recipient: FileReaderWriter.read(fileName)){
            emailRecipients.add(createRecipientObject((String) recipient));
        }
        return emailRecipients;
    }

    protected static void storeEmailRecipient(String inString){
        FileReaderWriter.write(fileName, inString);
    }

    protected static EmailRecipient createRecipientObject(String inputString){
        String[] inputs = inputString.split(": ");
        String recipientType = inputs[0];
        String[] details = inputs[1].split(",");


        if (recipientType.equalsIgnoreCase("official")){
            return new OfficialEmailRecipient(details[0], details[1], details[2]);
        }
        else if (recipientType.equalsIgnoreCase("Office_friend")){
            return new OfficeFriendRecipient(details[0], details[1], details[2], new NewDate(details[3]));
        }
        else if (recipientType.equalsIgnoreCase("Personal")){
            return new PersonalEmailRecipient(details[0], details[1], details[2], new NewDate(details[3]));
        }
        else
            return null;
    }
}
