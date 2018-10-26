package demo;

import java.time.Instant;

public class InstantDemo {
	public static void main(String[] args) {
		Instant instant = Instant.now();
		System.out.println(instant);
		
		long start = System.nanoTime();
		long end = System.nanoTime();
		System.out.println(end-start);
		
		System.out.println(System.currentTimeMillis());
		System.out.println(System.nanoTime());
		
	}

}
