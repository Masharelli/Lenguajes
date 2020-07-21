
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumer extends Thread {
    Buffer buffer;
    int timer;
    
    Consumer(Buffer buffer, int timer) {
        this.buffer = buffer;
        this.timer = timer;
    }
    
    @Override
    public void run() {
        System.out.println("Running Consumer...");
        while (true) {
        	Operacion operation = buffer.consume();
        	double resultado = operation.resolver();
        	Buffer.print("Consumer consumed: " + operation.getOperacion() + " with result: " + resultado);
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
