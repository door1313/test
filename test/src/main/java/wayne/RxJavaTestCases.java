package wayne;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

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
	
	//Test Operators
	public static void rxTestCase4() {
		Observable<String> myObs = Observable.just("Operater Test").map(s -> s + " is testing");
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
	
	//Test String to Hash
	public static void rxTestCase5() {
		Observable.just("Operater Test").map(s -> s.hashCode())
				.subscribe(i -> System.out.println("Hash code is : " + Integer.toString(i)));
	}

	
	public static void rxTestCase6() {
		String[] test = {"data part 1","data part 2","data part 3"};
		Observable.from(test).subscribe(s-> System.out.println("test from() value is : " + s));
	}
	
	public static void rxTestCase7(){
		Observable.range(1, 20).subscribe(s-> System.out.println("test range() to emit data from 1 to 20, value is : "+s));
	}
	
	public static void rxTestCase8(){
		Observable.timer(10, TimeUnit.SECONDS).subscribe(s-> System.out.println("This data emit by dely 10 seconds : "+s));
		System.out.println("test case 8 still continue");
		try {
			//sleep 11 seconds.
			Thread.sleep(11000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
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



