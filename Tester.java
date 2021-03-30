package assignment1;

import java.util.Scanner;

public class Tester {
    public static void main(String[] args) {
        NewBlockingQueue newBlockingQueue = new NewBlockingQueue(5);
        EmailReceiver er = new EmailReceiver(newBlockingQueue);
        ReceivedEmailSerializer es = new ReceivedEmailSerializer(newBlockingQueue);
        er.start();
        es.start();
        boolean con = true;
        while (con){
            Scanner s = new Scanner(System.in);
            System.out.println("Are you need to exit");
            if (s.nextInt() == 1){
                er.kill();
                es.kill();
                con = false;
            }
        }
    }
}
