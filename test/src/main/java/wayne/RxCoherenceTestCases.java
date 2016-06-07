package wayne;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.oracle.coherence.rx.ObservableMapListener;
import com.oracle.coherence.rx.RxNamedCache;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.util.MapEvent;
import com.tangosol.util.UUID;

import rx.Observable;
import rx.Subscriber;
import rx.observables.MathObservable;

import static com.oracle.coherence.rx.RxNamedCache.rx;
import static com.tangosol.util.Filters.equal;
import static com.tangosol.util.Filters.less;
import static com.tangosol.net.cache.TypeAssertion.withTypes;

public class RxCoherenceTestCases {

	// Using rxJava subscribe to print all value of cache data)
	public static void rxCoherenceCase1() {
		System.setProperty("coherence.distributed.localstorage", "true");
		NamedCache<Long, String> cache = CacheFactory.getTypedCache("st", withTypes(Long.class, String.class));
		cache.put(1L, "1");
		cache.put(2L, "2");
		cache.put(3L, "3");
		cache.put(4L, "4");
		cache.put(5L, "5");
		RxNamedCache<Long, String> rxcache = RxNamedCache.rx(cache);
		// rxcache.values().buffer(2).subscribe(productList ->
		// System.out.println("List :" + productList));
		rxcache.values().subscribe(products -> System.out.println("rxCache productID : " + products));

	}

	// Using map() operator to add "rxCache" to every value
	public static void rxCoherenceCase2() {
		System.setProperty("coherence.distributed.localstorage", "true");
		NamedCache<Long, String> cache = CacheFactory.getTypedCache("st", withTypes(Long.class, String.class));
		cache.put(1L, "1");
		cache.put(2L, "2");
		cache.put(3L, "3");
		cache.put(4L, "4");
		cache.put(5L, "5");
		RxNamedCache<Long, String> rxcache = RxNamedCache.rx(cache);
		// rxcache.values().buffer(2).subscribe(productList ->
		// System.out.println("List :" + productList));
		rxcache.values().map(products -> "Add \"rxCache\" using map oprator, productID :" + products)
				.subscribe(products -> System.out.println(products));

	}

	// Using flatMap() operator to call getProductName to get product name of
	// each product id
	public static void rxCoherenceCase3() {
		System.setProperty("coherence.distributed.localstorage", "true");
		NamedCache<Long, String> cache = CacheFactory.getTypedCache("st", withTypes(Long.class, String.class));
		cache.put(1L, "1");
		cache.put(2L, "2");
		cache.put(3L, "3");
		cache.put(4L, "4");
		cache.put(5L, "5");
		RxNamedCache<Long, String> rxcache = RxNamedCache.rx(cache);
		// rxcache.values().buffer(2).subscribe(productList ->
		// System.out.println("List :" + productList));
		rxcache.values().flatMap(id -> getProductName(id)).subscribe(product -> System.out.println(product));

	}

	// test zip operator
	public static void rxCoherenceCase4() {
		System.setProperty("coherence.distributed.localstorage", "true");
		NamedCache<Long, String> cache = CacheFactory.getTypedCache("st", withTypes(Long.class, String.class));
		NamedCache<Long, String> cache1 = CacheFactory.getTypedCache("st1", withTypes(Long.class, String.class));
		cache.put(1L, "1");
		cache.put(2L, "2");
		cache.put(3L, "3");
		cache.put(4L, "4");
		cache.put(5L, "5");
		cache1.put(11L, "11");
		cache1.put(12L, "12");
		cache1.put(13L, "13");
		cache1.put(14L, "14");
		cache1.put(15L, "15");

		RxNamedCache<Long, String> rxcache = RxNamedCache.rx(cache);
		RxNamedCache<Long, String> rxcache1 = RxNamedCache.rx(cache1);
		Observable<String> ca = rxcache.get(3L);
		Observable<String> ca1 = rxcache1.get(11L);

		Observable.zip(ca, ca1, (value1, value2) -> {
			return value1 + " zip with " + value2;
		}).subscribe(s -> System.out.println("Zip two values from cache and cahce1 respectively, result is : " + s));

	}

	// test skip
	public static void rxCoherenceCase5() {
		System.setProperty("coherence.distributed.localstorage", "true");
		NamedCache<Long, String> cache = CacheFactory.getTypedCache("st", withTypes(Long.class, String.class));
		cache.put(1L, "1");
		cache.put(2L, "2");
		cache.put(3L, "3");
		cache.put(4L, "4");
		cache.put(5L, "5");
		RxNamedCache<Long, String> rxcache = RxNamedCache.rx(cache);
		// rxcache.values().buffer(2).subscribe(productList ->
		// System.out.println("List :" + productList));
		rxcache.values().skip(2).subscribe(productList -> System.out.println("List: " + productList));

	}

	public static void rxCoherenceCase6() {
		System.setProperty("coherence.distributed.localstorage", "true");

		NamedCache<UUID, Trade> cache = CacheFactory.getTypedCache("trades", withTypes(UUID.class, Trade.class));

		cache.addIndex(Trade::getSymbol, true, null);
		cache.addIndex(Trade::getPrice, true, null);

		RxNamedCache<UUID, Trade> rxCache = rx(cache);

		cache.clear();
		createPositions(cache, 100);

		// get count of total positions
		rxCache.size().toBlocking().subscribe(size -> System.out.println("Size is " + size));

		// display all the trades
		rxCache.entrySet().map(Map.Entry::getValue).toBlocking().subscribe(System.out::println);

		// display only trades from ORCL using filter
		rxCache.entrySet(equal(Trade::getSymbol, "ORCL")).map(Map.Entry::getValue).toBlocking()
				.subscribe(trade -> System.out.println("ORCL trade: " + trade));

		// get total value of ORCL trades using Coherence filter in values()
		// call
		MathObservable
				.averageDouble(rxCache.values(equal(Trade::getSymbol, "ORCL")).map(trade -> trade.getPurchaseValue()))
				.toBlocking()
				.subscribe(total -> System.out.printf("Average Purchase Value of ORCL trades: $%10.2f\n", total));

		// get number trades with purchase price < $30
		rxCache.keySet(less(Trade::getPrice, 30d)).count().toBlocking()
				.subscribe(result -> System.out.println("Number of trades purchased below $30.00 is " + result));

		System.exit(0);
	}

	public static void rxCoherenceLisenerCase1() {
		NamedCache<Long, String> cache = CacheFactory.getTypedCache("st", withTypes(Long.class, String.class));
		ObservableMapListener<Long, String> listener = ObservableMapListener.create();
		listener.filter(evt -> evt.getId() == MapEvent.ENTRY_INSERTED).map(MapEvent::getNewValue)
				.subscribe(new Subscriber<String>() {
					public void onNext(String s) {
						System.out.println("xxx" + s);
					}

					@Override
					public void onCompleted() {
						System.out.println("completed");

					}

					public void onError(Throwable e) {
						System.out.println("aaaaahhhh");
					}

				});
		// listener.filter(evt -> evt.getId() ==
		// MapEvent.ENTRY_INSERTED).map(MapEvent::getNewValue).buffer(2,TimeUnit.SECONDS)
		// .subscribe(ppp -> System.out.println("Trades placed in the last 10
		// seconds: " + ppp));

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

	public static Observable<String> getProductName(String id) {
		return Observable.just("name+" + id);
	}

	public static void createPositions(NamedCache<UUID, Trade> tradesCache, int nCount) {
		Random random = new Random();
		HashMap<UUID, Trade> mapTrades = new HashMap<>();

		for (int i = 0; i < nCount; i++) {
			String sSymbol = SYMBOLS[random.nextInt(SYMBOLS.length)];
			int nAmount = random.nextInt(1000) + 1;
			double nPrice = random.nextFloat() * ((MAX_FACTOR - MIN_FACTOR) + MIN_FACTOR) * INITIAL_PRICE;

			Trade trade = new Trade(sSymbol, nAmount, nPrice);

			mapTrades.put(trade.getId(), trade);
		}

		tradesCache.putAll(mapTrades);
	}

	// ---- constants -------------------------------------------------------

	/**
	 * List of symbols to randomly select from.
	 */
	private final static String[] SYMBOLS = { "ORCL", "MSFT", "GOOG", "AAPL", "YHOO", "EMC" };

	/**
	 * Minimum factor for random price.
	 */
	private final static float MIN_FACTOR = 0.65f;

	/**
	 * Maximum factor for random price.
	 */
	private final static float MAX_FACTOR = 1.35f;

	/**
	 * Initial price.
	 */
	private final static double INITIAL_PRICE = 50;
}
