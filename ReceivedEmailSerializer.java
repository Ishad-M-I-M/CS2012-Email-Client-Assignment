package assignment1;

import java.io.IOException;

public class ReceivedEmailSerializer extends Thread{
    private NewBlockingQueue newBlockingQueue;
    private boolean alive;
    ReceivedEmailSerializer(NewBlockingQueue newBlockingQueue){
        this.newBlockingQueue = newBlockingQueue;
        this.alive = true;
    }

    public void kill(){
        while (! newBlockingQueue.isEmpty()){
            System.out.println("waiting till finish serializing received emails");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.alive = false;
        interrupt();
    }
    @Override
    public void run() {
        try {
            while (alive) {
                Email email = newBlockingQueue.dequeue();
                boolean result = EmailIO.storeRecievedEmail(email);
                if(result){
                    System.out.println(" ------------------------------------------------------------------------------- stored Received Email");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (InterruptedException ie){
            System.out.println("stopped execution of email serializer");
        }
    }

}
