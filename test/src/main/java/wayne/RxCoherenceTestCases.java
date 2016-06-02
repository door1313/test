package wayne;

import com.oracle.coherence.rx.ObservableMapListener;
import com.oracle.coherence.rx.RxNamedCache;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.util.MapEvent;

import rx.Subscriber;

public class RxCoherenceTestCases {
  public static void rxCoherenceCase1(){
	  NamedCache<Long,String> cache = CacheFactory.getCache("st");
		cache.put(3L, "3");
		cache.put(2L, "2");
		cache.put(1L, "1");
		cache.put(4L, "4");
		cache.put(5L, "5");
		RxNamedCache<Long,String> rxcache = RxNamedCache.rx(cache);
//		rxcache.values().buffer(2).subscribe(productList -> System.out.println("List :" + productList));
		rxcache.values().subscribe(product -> System.out.println("rxCache : " + product));
  }
  
  public static void rxCoherenceLisenerCase1(){
	  NamedCache<Long,String> cache = CacheFactory.getCache("st");
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
}
