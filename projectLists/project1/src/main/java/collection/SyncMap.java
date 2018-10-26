package collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SyncMap {
	
	public static final int pool_size = 5;
	
	public static Map<String,String> hashTableMap = null;
	public static Map<String,String> synchronizedMap = null;
	public static Map<String,String> concurrentHashMap = null;
	
	public static void main(String[] args) throws InterruptedException {
		hashTableMap = new Hashtable<String,String>();
		show(hashTableMap);
		System.out.println(hashTableMap.size());
		synchronizedMap = Collections.synchronizedMap(new HashMap<String,String>());
		show(synchronizedMap);
		System.out.println(synchronizedMap.size());
		concurrentHashMap = new ConcurrentHashMap<String,String>();
		show(concurrentHashMap);
		System.out.println(concurrentHashMap.size());
	}
	
	
	public static void show(final Map<String,String> map) throws InterruptedException {
		final List<Integer> list = new ArrayList<>();
		System.out.println(map.getClass()+"start show......");
		long averageTime = 0;
		for(int i = 0; i < 5 ;i++) {
			
			long startTime = System.nanoTime();
			//创建一个线程池
			ExecutorService executor = Executors.newFixedThreadPool(pool_size);
			for(int j = 0; j<pool_size; j++) {
				executor.submit(new Runnable(){
					@Override
					public void run() {
						for(int i = 0;i<5;i++) {
							int number = (int)Math.ceil(Math.random()*100);
							//String value = map.get(String.valueOf(number));
							map.put(String.valueOf(number), String.valueOf(number));
							list.add(number);
						}
					}
				});
			}
			// Make sure executor stops
			executor.shutdown();
            // Blocks until all tasks have completed execution after a shutdown request
			executor.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
            long entTime = System.nanoTime();
            long totalTime = (entTime - startTime) / 1000000L;
            averageTime += totalTime;
            System.out.println("2500K entried added/retrieved in " + totalTime + " ms");
		}
		System.out.println(list.size());
		System.out.println("For " + map.getClass() + " the average time is " + averageTime / 5 + " ms\n");
	}

}
