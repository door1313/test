package wayne;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class ThreadTest {
	public static void main(String... str) throws InterruptedException {
		
//    ThreadTest.testCase1();
//    ThreadTest.testCase2();
//		ThreadTest.testCase3();
		ThreadTest.testCase4();

/*
 * case 2	test join()
 */
//		Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
//		TestPriority t1 = new TestPriority(Thread.NORM_PRIORITY+2, "High thread");
//		TestPriority t2 = new TestPriority(Thread.NORM_PRIORITY-2, "Low thread");
//		t2.start();
//		t1.start();
//		Thread.sleep(10000);
//		t2.stop();
//		t1.stop();
//		
//		t1.t.join();
//		t2.t.join();
//		System.out.println("High thread : " + t1.click);
//		System.out.println("Low thread : " + t2.click);
//	}
		
		
		/*
		 * case3 test synchronized function
		 */
//		TestSyncCall callIn = new TestSyncCall();
//		TestSync t1 = new TestSync(callIn,"t1");
//		TestSync t2 = new TestSync(callIn,"t2");

		
		
		/*
		 * case 4 test synchronized block
		 */
//		
//		TestCallNotSync callIn = new TestCallNotSync();
//		TestNotSync t1 = new TestNotSync(callIn,"t1");
//		TestNotSync t2 = new TestNotSync(callIn,"t2");
		
	
//    int counter = 0;
//    counter ++;
//    System.out.println(counter);
    

    
}

/*
 * case 1		test isAlive()
 */
	public static void testCase1() throws InterruptedException{
		System.out.println("test");
		TestThread myT = new TestThread();

		//block the call thread until the thread represented by this instance terminates.
		myT.t.join();
		if (!myT.t.isAlive()) {
			System.out.println("thread end");

		}
		System.out.println("test finished");
	}
	/*
	 * case 2		test isAlive()
	 */
		public static void testCase2() throws InterruptedException{
			System.out.println("test");
			TestThread1 myT = new TestThread1();

			//block the call thread until the thread represented by this instance terminates.
			myT.t1.join();
			if (!myT.t1.isAlive()) {
				System.out.println("thread end");

			}
			System.out.println("test finished");
		}
		
		/*
		 * case3  test Callables and Future
		 */
		public static void testCase3(){
			
			TestCallable testC = new TestCallable();
			try {
				System.out.println(testC.future.get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/*
		 * case4 test completableFuture
		 */
		
	public static void testCase4() {

		CompletableFuture<String> future = CompletableFuture.supplyAsync(new mySupplier());

		System.out.println("Testing CompletableFuture");
//		
//		Function<String, Integer> fn = new Function<String, Integer>(){
//
//			@Override
//			public Integer apply(String t) {
//				// TODO Auto-generated method stub
//				return Integer.parseInt(t)-1;
//			}
//			
//		};
//		
//		CompletableFuture<Integer> futureInt = future.thenApplyAsync(fn);

		//Using lambda to simplify above codes.
		CompletableFuture<Integer> futureInt = future.thenApplyAsync(Integer::parseInt).thenApply(i -> i-1);
				
		
//		Consumer<Integer> action = new Consumer<Integer>(){
//
//			@Override
//			public void accept(Integer s) {
//				// TODO Auto-generated method stub
//				System.out.println("thenAccept value is : " + s);
//				
//			}
//			
//		};
//		futureInt.thenAcceptAsync(action);
		
		//Using lambda to simplify above codes.
		futureInt.thenAcceptAsync(s -> System.out.println("thenAccept value is : " + s));

		System.out.println("Doing some other jobs.");
		// wait for other threads
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}



class TestThread implements Runnable {

	Thread t;

	TestThread() {
		t = new Thread(this, "testCase1 thread implements Runnable");
		System.out.println("This is test thread implements Runnable: " + t);
		t.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 5; i++) {
			System.out.println("#####" + i + " --- " + t.getName());
			try {
				t.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

class TestCallable implements Callable<Integer>{

	Thread c;
	FutureTask<Integer> future;
	
	TestCallable(){
		future = new FutureTask<Integer>(this);
		
		c = new Thread(future);
		c.start();
		
	}
	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		return 123;
	}
	
}

class TestThread1 extends Thread{
	Thread t1;
	
	TestThread1(){
		t1 = new Thread(this, "testCase2 extends Thread");
		System.out.println("This is test thread extends Thread" +t1);
		t1.start();
	}
	
	public void run(){
		for (int i = 0; i < 5; i++) {
			System.out.println("#####" + i + " --- " + t1.getName());
			try {
				t1.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}
}

class mySupplier implements Supplier<String> {

	@Override
	public String get() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "100";
	}
	
}


class TestPriority implements Runnable{
	
	Long click =0L;
	Thread t;
	private volatile boolean running = true;
	
	TestPriority(int p, String name){
		t = new Thread(this, name);
		t.setPriority(p);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
	 while(running){
		 click++;
	 }
	}
	
	public void stop(){
		running = false;
	}
	
	public void start(){
		t.start();
	}
}
class TestSync implements Runnable{

	Thread t;
	TestSyncCall call;
	TestSync(TestSyncCall callIn,String name){
		t = new Thread(this,name);
		call = callIn;
		t.start();
	}
	@Override
	 public void run() {
		// TODO Auto-generated method stub
		
//		System.out.println("tttsss");

			call.call(t.getName());
		
		
	}
	
}
 class TestSyncCall {
	 synchronized void call(String msg)  {
		System.out.print("[" + msg);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("]");
	}
}

 
 class TestCallNotSync {
	  void call(String msg)  {
		System.out.print("[" + msg);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("]");
	}
}
 
 class TestNotSync implements Runnable{

		Thread t;
		TestCallNotSync call;
		TestNotSync(TestCallNotSync callIn,String name){
			t = new Thread(this,name);
			call = callIn;
			t.start();
		}
		@Override
		 public void run() {
			// TODO Auto-generated method stub
			// using synchronized block
			synchronized (call){
				call.call(t.getName());
			}
			
		}
		
	}

