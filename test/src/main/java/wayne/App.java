package wayne;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

import com.oracle.coherence.rx.ObservableMapListener;
import com.oracle.coherence.rx.RxNamedCache;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.util.MapEvent;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String... args) {
		// System.out.println( "Hello World!" );
		// hello("wayne","wang","test1","test2","test3");

		RxJavaTestCases.rxTestCase1();
		System.out.println("#####################################");
		RxJavaTestCases.rxTestCase2();
		RxJavaTestCases.rxTestCase3();
		RxJavaTestCases.runnableCase1();
		RxJavaTestCases.runnableCase2();
		
//		Observable<String> testOb = Observable.just("abc just");
//		 Subscriber<String> mySub = new Subscriber<String>(){
//		 public void onNext(String s){
//		 System.out.println("Test finish : " + s);
//		 }
//		
//		 public void onCompleted() {
//		 // TODO Auto-generated method stub
//		
//		 }
//		
//		 public void onError(Throwable arg0) {
//		 // TODO Auto-generated method stub
//		
//		 }
//		 };

		
//		String[] bString = new String[] { "aa", "bb", "cc" };
//		int i = 0;
//		for (String s : bString) {
//			i++;
//			System.out.println("Loop " + Integer.toString(i) + " " + s);
//		}


		
	

		

		String message = "Test effective final";

		Runnable r3 = () -> System.out.println(message);

		r3.run();

		StringBuilder messageB = new StringBuilder("aa");
		messageB.append("aa");
		Runnable r4 = () -> System.out.println(messageB);
		r4.run();
		messageB.append("AA");


		
		NamedCache<Long,String> cache = CacheFactory.getCache("st");
		cache.put(3L, "3");
		cache.put(2L, "2");
		cache.put(1L, "1");
		cache.put(4L, "4");
		cache.put(5L, "5");
		RxNamedCache<Long,String> rxcache = RxNamedCache.rx(cache);
//		rxcache.values().buffer(2).subscribe(productList -> System.out.println("List :" + productList));
		rxcache.values().subscribe(product -> System.out.println("rxCache : " + product));
		
		ObservableMapListener<Long,String> listener = ObservableMapListener.create();
		listener.filter(evt -> evt.getId() == MapEvent.ENTRY_INSERTED).map(MapEvent::getNewValue)
		.subscribe(new Subscriber<String>(){
			public void onNext(String s){
				System.out.println("xxx" + s);
			}
			@Override
			public void onCompleted() {
				System.out.println("completed");
				
			}
			public void onError(Throwable e){
				System.out.println("aaaaahhhh");
			}
			
		});
//		listener.filter(evt -> evt.getId() == MapEvent.ENTRY_INSERTED).map(MapEvent::getNewValue).buffer(2,TimeUnit.SECONDS)
//		.subscribe(ppp -> System.out.println("Trades placed in the last 10 seconds: " + ppp));
		cache.addMapListener(listener);
		cache.remove(3L);
		
		cache.put(6L, "test6");
		cache.put(7L, "test7");
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	public static void hello(String... names) {
//		Observable.from(names).subscribe(new Action1<String>() {
//
//			public void call(String s) {
//				System.out.println("Hellow " + s + "!");
//			}
//		});
//	}
    




}






