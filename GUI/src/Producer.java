import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer extends Thread {
    Buffer buffer;
    int timer;
    
    Producer(Buffer buffer, int timer) {
        this.buffer = buffer;
        this.timer = timer;
    }
    
    @Override
    public void run() {
        System.out.println("Running Producer...");
        while (true) {
        	Operacion product = new Operacion();
            buffer.produce(product);
            System.out.println("Producer produced: " + product.getOperacion() + ". Current products in stock: " + buffer.getBuffer().size());
            try {
            	Thread.sleep(timer);
            } catch (InterruptedException ex) {
            	Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        /*
        String products = "AEIOU";
        Random r = new Random(System.currentTimeMillis());
        char product;
        
        for(int i=0 ; i<5 ; i++) {
            product = products.charAt(r.nextInt(5));
            this.buffer.produce(product);
            //System.out.println("Producer produced: " + product);
            Buffer.print("Producer produced: " + product);
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        */
    }
    
}
