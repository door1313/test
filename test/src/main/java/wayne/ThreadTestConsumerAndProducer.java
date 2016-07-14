package wayne;

public class ThreadTestConsumerAndProducer {
	
	public static void main(String... args){
		
		/*
		 * case1 Producer and Consumer could not wait Products.
		 */
		Product p = new Product();
		new Producer(p);
		new Consumer(p);
		
		/*
		 * case2 Producer and Consumer could wait Products.
		 */

		ProductWait pWait = new ProductWait();
		new ProducerWait(pWait);
		new ConsumerWait(pWait);
		
	}
}


class Product{
	int n;
	synchronized int get(){
		System.out.println("Consume: " + n );
		return n;
	}
	
	synchronized void put(int n){
		this.n = n ;
		System.out.println("Produce: " + n);
	}
}

class Producer implements Runnable{

	Product p;
	Producer (Product p){
		this.p = p;
		new Thread(this, "Producer").start();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int i = 0;
		while(i<20){
			i++;
			p.put(i);
		}
	}
	
}

class Consumer implements Runnable{
	Product p;
	Consumer(Product p){
		this.p = p;
		new Thread(this, "Consumer").start();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int i = 0;
		while(i<20){
			p.get();
			i++;
		}
	}
	
}




class ProductWait{
	int n;
	boolean valueSet = false;
	
	synchronized int get(){
		if(!valueSet)
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		System.out.println("Consume could wait: " + n );
		valueSet = false;
		notify();
		return n;
	}
	
	synchronized void put(int n){
		if(valueSet)
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		this.n = n ;
		valueSet = true;
		System.out.println("Produce coud wait: " + n);
		notify();
	}
}




class ProducerWait implements Runnable{

	ProductWait p;
	ProducerWait (ProductWait p){
		this.p = p;
		new Thread(this, "Producer").start();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int i = 0;
		while(i<20){
			i++;
			p.put(i);
		}
	}
	
}

class ConsumerWait implements Runnable{
	ProductWait p;
	ConsumerWait (ProductWait p){
		this.p = p;
		new Thread(this, "Consumer").start();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int i = 0;
		while(i<20){
			p.get();
			i++;
		}
	}
	
}