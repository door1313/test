package wayne;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class LambdaTest {
	/*
	 * In lambda block 'this' means its outer class, in this case is class LambdaTest.
	 */
	Runnable r1 = () -> System.out.println(this);
//	Runnable r1 = System.out::println;
	Runnable r2 = () -> {System.out.println(toString());};
	
	/*
	 * In internal class, 'this' means internal class itself, and if want to using LambdaTest, then
	 * using Lambdatest.this
	 */
	Runnable r3 = new Runnable(){
		public void run(){
		  System.out.println(this);	
		}
		public String toString(){
			return "internal class";
		}
	};
	Runnable r4 = new Runnable(){
		public void run(){
		  System.out.println(this.toString());	
		  System.out.println(LambdaTest.this.toString());
		}
	};
	
	
	public String toString(){
		return "Hello Lambda toString";
	}

	
	public static void main(String... args){
		new LambdaTest().r1.run();
		new LambdaTest().r2.run();
		new LambdaTest().r3.run();
		new LambdaTest().r4.run();
		
	
		
//		List<Integer> intList = Arrays.asList(1,2,3);
	}
}
