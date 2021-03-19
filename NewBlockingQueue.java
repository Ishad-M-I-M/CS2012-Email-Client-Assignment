package assignment1;

import java.util.LinkedList;
import java.util.List;

public class NewBlockingQueue {
    private List<Email> emails;
    private int MAX_SIZE;
    private int currSize;
    public NewBlockingQueue(int MAX_SIZE){
        emails = new LinkedList<>();
        this.MAX_SIZE = MAX_SIZE;
    }

    public synchronized void recieveEmail(Email email){
        while(currSize >= MAX_SIZE){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        emails.add(email);
        currSize++;
    }

    public synchronized void storeEmail(){
        while (currSize <= 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
