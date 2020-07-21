import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Buffer {
    
    private ArrayList<Operacion> buffer;
    private int maxSize;
    
    Buffer(int maxSize) {
        this.buffer = new ArrayList<Operacion>();
        this.maxSize = maxSize;
    }
    
    synchronized Operacion consume() {
    	if (this.buffer.size() == 0) {
    		try {
    			wait(1000);
    		} catch (InterruptedException ex) {
    			Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
    		}
    	}
    	Operacion product = this.buffer.get(0);
    	this.buffer.remove(0);
    	notify();
    	return product;
    	/*
        char product = 0;
        
        if(this.buffer == 0) {
            try {
                wait(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        product = this.buffer;
        this.buffer = 0;
        notify();
        
        return product;
        */
    }
    
    synchronized void produce(Operacion operacion) {
        if(this.buffer.size() == this.maxSize) {
            try {
                wait(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.buffer.add(operacion);
        notify();
    }

	public ArrayList<Operacion> getBuffer() {
		return buffer;
	}



	static int count = 1;
    
    synchronized static void print(String string) {
        System.out.print(count++ + " ");
        System.out.println(string);
    }
    
}
