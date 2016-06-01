package wayne;

import java.util.Arrays;
import java.util.Comparator;

import rx.Observable;
import rx.Subscriber;

public class RxJavaTestCases {
	public static void rxTestCase1() {
		Observable<String[]> testObs = Observable.create(new Observable.OnSubscribe<String[]>() {
			public void call(Subscriber<? super String[]> sub) {
				String[] aString = new String[] { "aaa", "bbb", "ccc" };
				sub.onNext(aString);
				sub.onCompleted();
			}
		});
		testObs.subscribe(s -> System.out.println("lambda : " + s[0]));
	}
	
	
	//Test Operators
	public static void rxTestCase2() {
		Observable<String> myObs = Observable.just("Operater Test").map(s -> s + " is testing");
		myObs.subscribe(new Subscriber<String>(){
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

	}
	
	//Test String to Hash
	public static void rxTestCase3() {
		Observable.just("Operater Test").map(s -> s.hashCode())
				.subscribe(i -> System.out.println("Hash code is : " + Integer.toString(i)));
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



