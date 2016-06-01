package wayne;

import rx.Observable;
import rx.Subscriber;

public class RxJavaTestCases {
	public static void RxTestCase1() {
		Observable<String[]> testObs = Observable.create(new Observable.OnSubscribe<String[]>() {
			public void call(Subscriber<? super String[]> sub) {
				String[] aString = new String[] { "aaa", "bbb", "ccc" };
				sub.onNext(aString);
				sub.onCompleted();
			}
		});
		testObs.subscribe(s -> System.out.println("lambda : " + s[0]));
	}
}
