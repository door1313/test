package wayne;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tennis {
	
	public static boolean isZero = false; 
	public static void main(String... args) {
       Random r = new Random();
       int size = 27;
       List<Integer> test= inital(size);
//        for(Integer myInt : test){
//        	System.out.println(myInt.toString());
//        }
//       System.out.println(r.nextInt(10));
       while(test.size()>0)
       getRandom(test,r);
	}
	
	public static List<Integer> inital(int size){
		List<Integer> all = new ArrayList<Integer>();
		for(int j=0; j<5; j++){
			all.add(0);
		}
		for(int i=1; i<=size; i++){
			all.add(new Integer(i));
		}
		System.out.println("Player:27, bye: 5");
		System.out.println("-------------------");
		return all;
		
	}
	
	public static List<String> initalBoys(){
		List<String> all = new ArrayList<String>();
		all.add("ali");
		all.add("jiawang");
		all.add("wenyao");
		all.add("thomas");
		all.add("jin");
		all.add("stanley");
		all.add("allen");
		all.add("wayne");
		all.add("zhe");
		all.add("jingdong");
		all.add("dylan");
		all.add("tim");
	
		return all;
		
	}
	
	public static List<String> initalGirls(){
		List<String> all = new ArrayList<String>();
		all.add("kitty");
		all.add("yuanhong");
		all.add("molly");
		all.add("emma");
		all.add("hui");
		all.add("vivienne");
	
	
		return all;
		
	}
	
	public static void getRandomOrg(List<String> boyList, List<String> girlList ){
		Random r = new Random();
		
	}
	
	public static List<Integer> getRandom(List<Integer> orgList, Random r){
		if(orgList.size()%2==0){
			isZero=false;
		}
		int index = r.nextInt(orgList.size());
		if(isZero){
			while(orgList.get(index)==0){
				index = r.nextInt(orgList.size());
			}
		}
		if(orgList.get(index)==0){
			isZero=true;
		}
		if(orgList.size()%2==1){
			System.out.println("VS.");
			System.out.println(orgList.get(index));
			System.out.println("-------------------");
		}else{
			System.out.println(orgList.get(index));
		}
		
		orgList.remove(index);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orgList;
		
	}
}
