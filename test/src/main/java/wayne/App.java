package wayne;



/**
 * Hello world!
 *
 */
public class App {
	public static void main(String... args) {
		// System.out.println( "Hello World!" );
		// hello("wayne","wang","test1","test2","test3");

//		RxJavaTestCases.rxTestCase1();
//		System.out.println("#####################################");
//		RxJavaTestCases.rxTestCase2();
//		RxJavaTestCases.rxTestCase3();
		RxJavaTestCases.rxTestCase6();
//		RxJavaTestCases.runnableCase1();
//		RxJavaTestCases.runnableCase2();
		
//		RxCoherenceTestCases.rxCoherenceCase1();
//		
//		RxCoherenceTestCases.rxCoherenceCase2();
//		
//		RxCoherenceTestCases.rxCoherenceCase3();
		
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






