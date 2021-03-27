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
        this.currSize = 0;
    }

    public synchronized void enqueue(Email email){
        while(currSize >= MAX_SIZE){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        emails.add(email);
        currSize++;
        notifyAll();

    }

    public synchronized Email dequeue() throws InterruptedException {
        while (currSize <= 0){
           wait();
        }
        Email email = emails.remove(0);
        currSize -- ;
        notifyAll();
        return email;
    }

    public boolean isEmpty(){
        return currSize == 0;
    }
}
