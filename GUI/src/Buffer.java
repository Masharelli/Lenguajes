import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Buffer {
    
    private ArrayList<Operacion> buffer;
    private int maxSize;
    private GUIHandler handler;
    
    Buffer(int maxSize, GUIHandler handler) {
        this.buffer = new ArrayList<Operacion>();
        this.maxSize = maxSize;
        this.handler = handler;
    }
    
    synchronized Operacion consume(String id) {
    	while (this.buffer.isEmpty()) {  //mejor while que if para que entren en orden
    		try {
    			wait();
    		} catch (InterruptedException ex) {
    			Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
    		}
    	}
    	Operacion product = this.buffer.get(0); //si mas de un consumidor entra a esta linea al mismo tiempo
    	//con 1 solo producto en canasta, uno tendra una exepcion y el otro consumira correctamente
    	this.buffer.remove(0);
    	this.handler.consumeUpdate(id, product.getOperacion(), product.resolver() + "", this.maxSize, this.buffer.size());
    	notify(); //notificar al primer productor que se consumio un producto  (notifyAll)
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
    
    synchronized void produce(Operacion operacion, String id) {
        while (this.buffer.size() == this.maxSize) {  //tambien cambiar a while
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.buffer.add(operacion);
        this.handler.produceUpdate(id, operacion.getOperacion(), this.maxSize, this.buffer.size());
        notifyAll();  //probar el notifyAll ya usando el while
    }

	public ArrayList<Operacion> getBuffer() {
		return buffer;
	}
	
	public int getMaxSize() {
		return this.maxSize;
	}



	static int count = 1;
    
    synchronized static void print(String string) {
        System.out.print(count++ + " ");
        System.out.println(string);
    }
    
}
