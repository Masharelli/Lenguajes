
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumer extends Thread {
    Buffer buffer;
    int timer;
    boolean stop;
    int consumerID;
    
    Consumer(Buffer buffer, int timer, int id) {
        this.buffer = buffer;
        this.timer = timer;
        this.stop = false;
        this.consumerID = id;
    }
    
    public void stopConsuming() {
    	this.stop = true;
    }
    
    @Override
    public void run() {
        System.out.println("Running Consumer...");
        while (!this.stop) {
        	Operacion operation = buffer.consume(this.consumerID + "");
        	double resultado = operation.resolver();
        	Buffer.print("Consumer " + this.consumerID + " consumed: " + operation.getOperacion() + " with result: " + resultado);
        	try {
        		Thread.sleep(this.timer);
        	} catch(InterruptedException ex) {
        		Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
        	}
        }
        
        /*for(int i=0 ; i<5 ; i++) {
            product = this.buffer.consume();
            //System.out.println("Consumer consumed: " + product);
            Buffer.print("Consumer consumed: " + product);
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        */
    }
}
