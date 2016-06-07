package wayne;



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
		System.out.println("#####################################");
		RxJavaTestCases.rxTestCase3();
		System.out.println("#####################################");
		RxJavaTestCases.rxTestCase4();
		System.out.println("#####################################");
		RxJavaTestCases.rxTestCase5();
		System.out.println("#####################################");
		RxJavaTestCases.rxTestCase6();
		System.out.println("#####################################");
		RxJavaTestCases.rxTestCase7();
		System.out.println("#####################################");
		RxJavaTestCases.rxTestCase8();
		System.out.println("#####################################");
		RxJavaTestCases.rxTestCase9();
		System.out.println("#####################################");
		RxJavaTestCases.rxTestCase10();
		System.out.println("#####################################");
		RxJavaTestCases.rxTestCase11();
		System.out.println("#####################################");
		RxJavaTestCases.rxTestCase12();

		
		RxCoherenceTestCases.rxCoherenceCase1();
		System.out.println("#####################################");
		RxCoherenceTestCases.rxCoherenceCase2();
		System.out.println("#####################################");
		RxCoherenceTestCases.rxCoherenceCase3();
		System.out.println("#####################################");
		RxCoherenceTestCases.rxCoherenceCase4();
		System.out.println("#####################################");
		RxCoherenceTestCases.rxCoherenceCase5();
		System.out.println("#####################################");
		RxCoherenceTestCases.rxCoherenceLisenerCase1();
		
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


		
	

		

//		String message = "Test effective final";
//
//		Runnable r3 = () -> System.out.println(message);
//
//		r3.run();
//
//		StringBuilder messageB = new StringBuilder("aa");
//		messageB.append("aa");
//		Runnable r4 = () -> System.out.println(messageB);
//		r4.run();
//		messageB.append("AA");


		
		
		
		
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






