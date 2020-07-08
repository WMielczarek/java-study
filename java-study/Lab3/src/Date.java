package Lab3;

import java.lang.ref.ReferenceQueue;
import static java.util.concurrent.TimeUnit.*;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

class Main {
		private static int counter = 0;
		static List<Double> list = new ArrayList();
		
		Main(int  li){
			this.licznik = li;
			this.generate(li);
		}
		private double licznik = 0;
		
		void generate(int seed) {
			for (int i =0;i<seed;i++) {
				licznik=i+20*141/40;
				list.add(licznik);
			}
				
		}
		 synchronized void increment() {
			counter++;
		}
		
		
		@Override
		protected void finalize() throws Throwable {
			// TODO Auto-generated method stub
			System.out.println("Deleting number: "+counter);
			increment();
			super.finalize();
		}



		public static void main(String[] args) throws InterruptedException {
			Data data = new Data();
			


			ScheduledExecutorService exe = Executors.newScheduledThreadPool(5);
			synchronized (exe) {

				exe.scheduleAtFixedRate(new StatThread("First", data), 0, 5000, MILLISECONDS);
				exe.scheduleAtFixedRate(new StatThread("Second", data), 0, 5000, MILLISECONDS);
				exe.scheduleAtFixedRate(new StatThread("Third", data), 0, 5000, MILLISECONDS);
				exe.scheduleAtFixedRate(new StatThread("Fourth", data), 0, 4000, MILLISECONDS);
				exe.scheduleAtFixedRate(new StatThread("Fifth", data), 0, 4000, MILLISECONDS);

				exe.scheduleAtFixedRate(new Runnable() {
					public void run() {
						for (int i = 0; i < 100; i++) {

							data.addCollection(i + 500, new Main(i + 500));
						}
					}
				}, 0, 500, MILLISECONDS);
				for (int i = 0; i < 100; i++) {

					data.addCollection(i + 500, new Main(i + 500));

				}
			}

			
			if(!exe.isShutdown())
			exe.shutdown();
			
		}
		
	}

class Data {
	
	ReferenceQueue qr = new ReferenceQueue();
	Map<Integer,List<Double>> map = new ConcurrentHashMap<Integer, List<Double>>();
	Data(){
		
	}
	void addCollection(Integer i, Main ex) {
		map.put(i, ex.list);
		SoftReference<Main> sr = new SoftReference<Main>(ex,qr);
		//System.out.println(qr);
	}
}
	


