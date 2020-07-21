
public class ProducerConsumer {

    public static void main(String[] args) {
        
        Buffer buffer = new Buffer(10);
        
        Producer producer = new Producer(buffer, 1000);
        producer.start();
        
        Consumer consumer = new Consumer(buffer, 1000);
        consumer.start();
        
        //TODO: semaforos/banderas para que el productor se duerma cuando ya este lleno el bufer
        //linkear con GUI
        //modificar condiciones al consumir/producir en caso de que tengan diferentes velocidades, consumidor truena porque intenta consumir cuando no hay nada
    }
    
}
