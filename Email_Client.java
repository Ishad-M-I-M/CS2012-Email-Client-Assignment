package assignment1;

// your index number : - 190241X

//import libraries
import java.util.ArrayList;
import java.util.Scanner;

public class Email_Client {
    private static ArrayList<EmailRecipient> emailRecipients = EmailRecipientManager.readEmailRecipients();
    private static ArrayList<Email> emailsSent = EmailIO.readEmailsFromLog();
    private static int numberOfRecipients = emailRecipients.size();
    private static ArrayList<Greetable> greetableRecipients = filterGreetableRecipients();

    public static void main(String[] args) {
        emailBirthdayWishes();

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
        scanner.nextLine();     // to skip the newline character stored in buffer after reading next int
        String inputString;
        switch(option){

            case 1:
                // input format - Official: nimal,nimal@gmail.com,ceo
                // Use a single input to get all the details of a recipient
                // code to add a new recipient
                // store details in clientList.txt file

                inputString = scanner.nextLine();
                emailRecipients.add(EmailRecipientManager.createRecipientObject(inputString));    //  creating and adding recipient to the ArrayListvvvvvvvvvvvvvvvvvvvvvvvv
                EmailRecipientManager.storeEmailRecipient(inputString);
                numberOfRecipients++ ;
                // Hint: use methods for reading and writing files
                break;

            case 2:
                // input format - email, subject, content
                String[] inputs = new String[3];
                String[] inputDetails = {"Email : ", "Subject : ", "Content : "};
                for (int i=0; i < 3; i++){
                    System.out.println(inputDetails [i]);
                    inputs[i] = scanner.nextLine();
                    scanner.reset();
                }
                // code to send an email

                Email email = new Email(inputs[0], inputs[1], inputs[2], (new NewDate()).toString());
                send(email);

                break;

            case 3:
                // input format - yyyy/MM/dd (ex: 2018/09/17)
                // code to print recipients who have birthdays on the given date
                inputString = scanner.nextLine();
                NewDate date = new NewDate(inputString);
                for (EmailRecipient e: emailRecipients){
                    if (e instanceof Greetable){
                        NewDate birthday = ((Greetable) e).getBirthday();
                        if (date.checkForBirthday(birthday)){
                            System.out.println(e.getName());
                        }
                    }
                }
                break;

            case 4:
                // input format - yyyy/MM/dd (ex: 2018/09/17)
                // code to print the details of all the emails sent on the input date
                inputString = scanner.nextLine().trim();
                for (Email sentEmail: emailsSent ) {
                    //if (sentEmail.getDate().equals(inputString)){
                        sentEmail.printDetails();
                    //}
                }
                break;
            case 5:
                // code to print the number of recipient objects in the application
                System.out.println("Number of email recipients in the application : "+numberOfRecipients);
                break;
            case 6:
                EmailIO.storeEmails(emailsSent);
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

    private static ArrayList<Greetable> filterGreetableRecipients(){
        ArrayList<Greetable> greetableRecipients = new ArrayList<>();
        for(EmailRecipient e: emailRecipients){
            if (e instanceof Greetable){
                greetableRecipients.add((Greetable) e);
            }
        }
        return greetableRecipients;
    }
    private static void emailBirthdayWishes(){
        for( Greetable greetable: greetableRecipients){
            if(greetable.getBirthday().checkForBirthday()){
               Email birthdayWish =  new Email(((EmailRecipient) greetable).getEmail(),
                       "Birthday Wish",
                       greetable.greetForBirthday("Ishad"),
                       (new NewDate()).toString());
               send(birthdayWish);

            }
        }
    }

    private static void send(Email email){
        email.send();
        emailsSent.add(email);
    }


}

// create more classes needed for the implementation (remove the  public access modifier from classes when you submit your code)






