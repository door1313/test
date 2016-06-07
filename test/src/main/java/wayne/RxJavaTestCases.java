package wayne;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func2;

public class RxJavaTestCases {
	
	
	
	public static void rxTestCase1() {
		
		// Create a new Obeservable 被观察者or事件源 by using create()
		Observable<String[]> testObs = Observable.create(new Observable.OnSubscribe<String[]>() {
			public void call(Subscriber<? super String[]> sub) {
				String[] aString = new String[] { "aaa", "bbb", "ccc" };
				sub.onNext(aString);
				sub.onCompleted();
			}
		});
		
	
		 Subscriber<String[]> mySubscriber = new Subscriber<String[]>() {
		 
		 @Override 
		 public void onNext(String[] s) { System.out.println("case1 :" + s[0]); }
		 
		 @Override 
		 public void onCompleted() { }
		 
		 @Override 
		 public void onError(Throwable e) { }
 
		 };
		 
		 testObs.subscribe(mySubscriber);
		
	}
	
public static void rxTestCase2() {
		
		// Create a new Obeservable 被观察者or事件源 by using create()
		Observable<String[]> testObs = Observable.create(new Observable.OnSubscribe<String[]>() {
			public void call(Subscriber<? super String[]> sub) {
				String[] aString = new String[] { "aaa", "bbb", "ccc" };
				sub.onNext(aString);
				sub.onCompleted();
			}
		});
		

		/*
		 * Using Action1 to simplify subscriber  观察者
		 * 
		 */
		
		testObs.subscribe(new Action1<String[]>(){

			@Override
			public void call(String[] s) {
				System.out.println("case2 : " + s[0]);
				
			}
			
		});
	}


public static void rxTestCase3() {
	
	// Create a new Obeservable 被观察者or事件源 by using create()
	Observable<String[]> testObs = Observable.create(new Observable.OnSubscribe<String[]>() {
		public void call(Subscriber<? super String[]> sub) {
			String[] aString = new String[] { "aaa", "bbb", "ccc" };
			sub.onNext(aString);
			sub.onCompleted();
		}
	});
	
	/*
	 * Using jdk 1.8 lambada simplified following code
	 * 
	 */
	testObs.flatMap(strings -> Observable.from(strings)).subscribe(s -> System.out.println("case3: simplified by lambda : " + s));
}
	
	//Test create Observable by using just()
	public static void rxTestCase4() {
		Observable<String> myObs = Observable.just(" Test create Observable by using just() ");
		myObs.subscribe(new Subscriber<String>(){
			public void onNext(String s){
				System.out.println("on next is :" + s);
			}
			@Override
			public void onCompleted() {
				System.out.println("completed");
				
			}
			public void onError(Throwable e){
				System.out.println("error happened");
			}
		});

	}
	
	//Test String to Hash by using operator map()
	public static void rxTestCase5() {
		Observable.just("Operater Test").map(s -> s.hashCode())
				.subscribe(i -> System.out.println("Hash code is : " + Integer.toString(i)));
	}

	//Create Observable by using from(), from will separate String array to several Observables.
	public static void rxTestCase6() {
		String[] test = {"data part 1","data part 2","data part 3"};
		Observable.from(test).subscribe(s-> System.out.println("test from() value is : " + s));
	}
	
	
	//Using range() to create a Observable to send a range of number 
	public static void rxTestCase7(){
		Observable.range(1, 20).subscribe(s-> System.out.println("test range() to emit data from 1 to 20, value is : "+s));
	}
	
	
	// Using timer() to send 0L after delay 10 seconds.
	public static void rxTestCase8(){
		Observable<Long> obs = Observable.timer(10, TimeUnit.SECONDS);
		obs.map(value -> "Delay 10 seconds test").subscribe(s -> {
			System.out.println("This data emit by dely 10 seconds : " + s);
			System.out.println("test case 8 done");
		});
		System.out.println("test case 8 still continue");
		try {
			// sleep 11 seconds.
			Thread.sleep(11000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Test using .error() to send a exception to subscriber.
	public static void rxTestCase9() {
		Observable<String> myObs = Observable.error(new Exception());
		myObs.subscribe(new Subscriber<String>(){
			public void onNext(String s){
				System.out.println("on next is " + s);
			}
			@Override
			public void onCompleted() {
				System.out.println("completed");
				
			}
			public void onError(Throwable e){
				System.out.println("error happened");
			}
		});

	}

	// test skip and take operators
	public static void rxTestCase10() {
		String[] test = {"1","2","3","4","5","6","7"};
		Observable.from(test).skip(2).take(3).subscribe(s->System.out.println("Test skip and take, value is : " +s));
		
	}
	
	public static void rxTestCase11() {
		Observable<String> obs1 = Observable.just("obs1 for zip test");
		Observable<String> obs2 = Observable.just("obs2 for zip test");
//		Observable.zip(obs1,obs2, new Func2<String,String,String>(){
//
//			@Override
//			public String call(String t1, String t2) {
//				// TODO Auto-generated method stub
//				return t1+" ==zip== " +t2;
//			}
//			
//		}).subscribe(s -> System.out.println("Test operator zip() : " +s));
		
		//Using lambada to simplify code.
		Observable.zip(obs1, obs2, (t1, t2) -> {
			return t1 + " ==zip== " + t2;
		}).subscribe(s -> System.out.println("Test operator zip() : " + s));
		;

	}
	
	// test buffer operators, periodically gather items emitted by an Observable
		public static void rxTestCase12() {
			String[] test = {"1","2","3","4","5","6","7"};
			Observable.from(test).buffer(4).subscribe(s->System.out.println("Test skip and take, value is : " +s));
			
		}
	
	
	
	public static void runnableCase1(){
		Runnable r = () -> System.out.println("test runnable lambda");
		r.run();
	}
	
	public static void runnableCase2(){
		Hello h = new Hello();
		h.r.run();
		h.r2.run();
	}
	
	public static void java8LambadaCase1(){
		//Java 1.8 lambada case
		Comparator<String> c = (a, b) -> a.compareTo(b);
		System.out.println(Integer.toString(c.compare("AAA", "AAA")));
	}
	
	public static void java8LamabadaCase2(){
		Person[] people = new Person[] { new Person("ted", "new", 41), new Person("wayne", "wang", 18),
				new Person("adm", "zcho", 22) };
		Arrays.sort(people, Person.compareFirstName);
		Arrays.sort(people, Person::compareLastName);
		for( Person p : people)
			System.out.println(p);
	}
	public CompletableFuture<String> getYY(){
//		return  CompletableFuture.supplyAsync(new Supplier<String>(){
//
//			@Override
//			public String get() {
//				// TODO Auto-generated method stub
//				return "wayne";
//			}
//			
//		});
		
		
		return CompletableFuture.supplyAsync(()->"wayne");
		
	}
	public void addYY(){
//		return  CompletableFuture.supplyAsync(new Supplier<String>(){
//
//			@Override
//			public String get() {
//				// TODO Auto-generated method stub
//				return "wayne";
//			}
//			
//		});
		
		
		
		
	}
	
}
class Hello {

	public Runnable r = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println(this);
			System.out.println(this.toString());
		}

	};
	public Runnable r2 = () -> {
		// TODO Auto-generated method stub
		System.out.println(this);
		System.out.println(toString());
	};

	public String toString() {
		return "Hello's custom";
	}
}

class Person {
	public String firstname;
	public String lastname;
	public int age;

	public Person(String name, String name2, int age1) {
		firstname = name;
		lastname = name2;
		age = age1;

	}

	public final static Comparator<Person> compareFirstName = (lhs, rhs) -> lhs.firstname.compareTo(rhs.firstname);
	
	public static int compareLastName(Person lhs, Person rhs){
		return lhs.lastname.compareTo(rhs.lastname);
	}
	
	
	public String toString(){
		return "[Person: firstName:" +firstname+" lastname: " +lastname +" age: " +age+"]";
	}
	
	
	
}



