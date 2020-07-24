import java.util.ArrayList;

public class ProducerConsumer {
	
	private ArrayList<Producer> producers;
	private ArrayList<Consumer> consumers;
	private int bufferSize, lowerRange, maxRange, sleepTimeProducer, sleepTimeConsumer, nProducers, nConsumers;
	private Buffer buffer;
	private GUIHandler handler;
	
	public ProducerConsumer(int bufferSize, int lowerRange, int maxRange, int nProducers, int nConsumers, int sleepTimeProducer, int sleepTimeConsumer) {
		this.producers = new ArrayList<Producer>();
		this.consumers = new ArrayList<Consumer>();
		this.bufferSize = bufferSize;
		this.lowerRange = lowerRange;
		this.maxRange = maxRange;
		this.nProducers = nProducers;
		this.nConsumers = nConsumers;
		this.sleepTimeProducer = sleepTimeProducer;
		this.sleepTimeConsumer = sleepTimeConsumer;
	}
	
	public void setHandler(GUIHandler handler) {
		this.handler = handler;
	}
	
	public void initializeWork() {
		this.buffer = new Buffer(this.bufferSize, this.handler);
		for (int i = 1; i <= this.nProducers; i++) {
			Producer producer = new Producer(this.buffer, this.sleepTimeProducer, i, lowerRange, maxRange);
			this.producers.add(producer);
			producer.start();
		}
		
		for (int i = 1; i <= this.nConsumers; i++) {
			Consumer consumer = new Consumer(this.buffer, this.sleepTimeConsumer, i);
			this.consumers.add(consumer);
			consumer.start();
		}
	}
	
	public void stopAllAction() {
		for (int i = 0; i < this.producers.size(); i++) {
			this.producers.get(i).stopProducing();
		}
		for (int i = 0; i < this.consumers.size(); i++) {
			this.consumers.get(i).stopConsuming();
		}
	}
}
