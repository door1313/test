package wayne;

public class ThreadTest {
	public static void main(String... str) throws InterruptedException {
		

/*
 * case 1		test isAlive()
 */
/////////////////////////////////////////////////////////////////////////////////////////////////////
//		System.out.println("test");
//		TestThread myT = new TestThread();
////		if (!myT.t.isAlive()) {
////			System.out.println("test end");
////
////		}
//		myT.t.join();
//		System.out.println("test end");
///////////////////////////////////////////////////////////////////////////////////////////////////
		
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
		
		TestCallNotSync callIn = new TestCallNotSync();
		TestNotSync t1 = new TestNotSync(callIn,"t1");
		TestNotSync t2 = new TestNotSync(callIn,"t2");
		
	
}
}

class TestThread implements Runnable {

	Thread t;

	TestThread() {
		t = new Thread(this, "test thread");
		System.out.println("This is test thread : " + t);
		t.start();
	}

	TestThread(String name) {
		t = new Thread(this, name);
		System.out.println("This is test thread : " + t);

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

