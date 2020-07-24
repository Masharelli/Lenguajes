import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer extends Thread {
    Buffer buffer;
    int timer;
    boolean stop;
    int producerID;
    int numMin;
    int numMax;
    
    
    Producer(Buffer buffer, int timer, int id, int numMin, int numMax) {
        this.buffer = buffer;
        this.timer = timer;
        this.stop = false;
        this.producerID = id;
        this.numMin = numMin;
        this.numMax = numMax;
    }
    
    public void stopProducing() {
    	this.stop = true;
    }
    
    @Override
    public void run() {
        System.out.println("Running Producer...");
        int[] numeros = new int[numMax - numMin + 1];
        int current = numMin;
        for (int i = 0; i <= (numMax-numMin); i++) {
        	numeros[i] = current;
        	current++;
        }
        
        while (!stop) {
        	Operacion product = new Operacion(numeros);
            buffer.produce(product, this.producerID + "");
            System.out.println("Producer " + this.producerID + " produced: " + product.getOperacion() + ". Current products in stock: " + buffer.getBuffer().size());
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
