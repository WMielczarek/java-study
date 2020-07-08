package Lab3;

import java.util.List;
import java.util.Map;
import java.util.Random;


public class StatThread implements Runnable{
	String name = "";
	Data data ;
	Random rand = new Random();
	int i = 0;
	
	
	
	StatThread(String name, Data data ){
		this.name = name;
		this.data = data;
		
	}
	@Override
	public void run() {
		 
			while(true) {
				 {
					 double w=this.avarageOfCollection(rand.nextInt(1000), data);
					  System.out.println("Thread: "+name +". average : "+w);
					 try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		
			
	}
		
		
	
	boolean checkMap(Integer ziarno, Map map) {
		if(map.containsKey(ziarno)) {
			System.out.println("This collection exist");
			return true;
		}
		else return false;
	}
	double avarage(List<Double> list) {
		Double result = 0.0;
		for(int i=0;i<list.size();i++) {
			result += list.get(i);
		}
		return result/list.size();
		
	}
	double max(List<Double> list) {
		Double result = 0.0;
		for(int i=0;i<list.size();i++) {
			if(list.get(i)>result)
			result = list.get(i);
		}
		return result;
	}
	 synchronized double maxofCollection(Integer seed,Data tx) {
		if(this.checkMap(seed,tx.map )) {
			List<Double> list = tx.map.get(seed);
			return max(list);
		
		}
		else {
			System.out.println(name+" New collection");
			tx.addCollection(seed, new Main(seed));
			List<Double> list = tx.map.get(seed);
			return max(list);
			
		}
		
	}
	double avarageOfCollection(Integer seed, Data tx) {
		
		if(this.checkMap(seed,tx.map )) {
			List<Double> list = tx.map.get(seed);
			return avarage(list);
		
		}
		else {
			System.out.println(name+" Making collection");
			tx.addCollection(seed, new Main(seed));
			List<Double> list = tx.map.get(seed);
			return avarage(list);
			
		}
		
	}

	void join() {
	    this.join();
    }
	
}
	

