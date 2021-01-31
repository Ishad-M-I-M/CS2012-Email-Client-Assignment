package assignment1;

// your index number : - 190241X

//import libraries

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.Scanner;

public class Email_Client {
    private static ArrayList<EmailRecipient> emailRecipients;
    private static ArrayList<Email> emailsSent = readEmailsFromLog();
    public static void main(String[] args) {
        boolean cont = true;
        while (cont){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter option type: \n"
                + "1 - Adding a new recipient\n"
                + "2 - Sending an email\n"
                + "3 - Printing out all the recipients who have birthdays\n"
                + "4 - Printing out details of all the emails sent\n"
                + "5 - Printing out the number of recipient objects in the application\n"
                + "6 - Quite");

        int option = scanner.nextInt();
        scanner.reset();
        scanner.nextLine();
        String inputString;
        String[] inputs;
        switch(option){
            case 1:
                // input format - Official: nimal,nimal@gmail.com,ceo
                inputString = scanner.nextLine();
                inputs = inputString.split(": ");
                // Use a single input to get all the details of a recipient
                String recipientType = inputs[0];
                String[] details = inputs[1].split(",");
                // code to add a new recipient
                EmailRecipient recipient;
                if (recipientType.equalsIgnoreCase("official")){
                    recipient = new OfficialEmailRecipient(details[0], details[1], details[2]);
                }
                else if (recipientType.equalsIgnoreCase("Office_friend")){
                    recipient = new OfficeFriendRecipient(details[0], details[1], details[2], parseDate(details[3]));
                }
                else {//if (recipientType.equalsIgnoreCase("Personal")){
                    recipient = new PersonalEmailRecipient(details[0], details[1], details[2], parseDate(details[3]));
                }
                // store details in clientList.txt file
                try {
                    File file = new File("clientList.txt");
                    if (! file.exists()){
                        file.createNewFile();
                    }
                    BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
                    bw.append(inputString+"\n");
                    bw.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                // Hint: use methods for reading and writing files
                break;

            case 2:

                // input format - email, subject, content
                inputs = new String[3];
                String[] inputDetails = {"Email : ", "Subject : ", "Content : "};
                for (int i=0; i < 3; i++){
                    System.out.println(inputDetails [i]);
                    inputs[i] = scanner.nextLine();
                    scanner.reset();
                }
                // code to send an email
                Email email = new Email(inputs[0], inputs[1], inputs[2], new Date());
                email.send();
                storeEmail(email);                // storing the sent email object to hard disk by serializing
                System.out.println("Email sent to: " + inputs[0]);

                break;
            case 3:
                for (Email e: emailsSent){
                    e.printDetails();
                }
                // input format - yyyy/MM/dd (ex: 2018/09/17)
                // code to print recipients who have birthdays on the given date
                break;
            case 4:
                // input format - yyyy/MM/dd (ex: 2018/09/17)
                // code to print the details of all the emails sent on the input date
                break;
            case 5:
                // code to print the number of recipient objects in the application
                break;
            case 6:
                cont = false;
                break;
            default:
                System.out.println("Invalid Input");
        }
        }

        // start email client
        // code to create objects for each recipient in clientList.txt
        // use necessary variables, methods and classes


    }

    public static void storeEmail(Email email) {
        FileOutputStream fos = null;
        try {
            File file = new File("emailLog.ser");
            if (! file.exists()){
                file.createNewFile();
            }
            fos = new FileOutputStream(file, true);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(email);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static ArrayList<Email> readEmailsFromLog(){
        ArrayList<Email> emails = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try{
            emails = new ArrayList<>();
            fis = new FileInputStream("emailLog.ser");
            ois = new ObjectInputStream(fis);

            while (true)
                emails.add((Email) ois.readObject());

        } catch (EOFException e){

        } catch (ClassNotFoundException | IOException e) {
            return null;
        }

        if (ois != null ){
            try {
                ois.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return emails;

    }

    private static Date parseDate(String dateString){
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy/MM/dd").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}

// create more classes needed for the implementation (remove the  public access modifier from classes when you submit your code)






