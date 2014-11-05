package com.zhk.jdk.concurrent.map;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentMapTest {

	static final int threads = 1000;
	static final int NUMBER = 1000;

	public static void main(String[] args) throws Exception {
		Map<String, Integer> hashmapSync = Collections
				.synchronizedMap(new HashMap<String, Integer>());
		Map<String, Integer> concurrentHashMap = new ConcurrentHashMap<String, Integer>();
		Map<String, Integer> hashtable = new Hashtable<String, Integer>();
		long totalA = 0;
		long totalB = 0;
		long totalC = 0;
		for (int i = 0; i <= 10; i++) {
			totalA += testPut(hashmapSync);
			totalB += testPut(concurrentHashMap);
			totalC += testPut(hashtable);
		}
		System.out.println("Put time HashMapSync=" + totalA + "ms.");
		System.out.println("Put time ConcurrentHashMap=" + totalB + "ms.");
		System.out.println("Put time Hashtable=" + totalC + "ms.");
		totalA = 0;
		totalB = 0;
		totalC = 0;
		for (int i = 0; i <= 10; i++) {
			totalA += testGet(hashmapSync);
			totalB += testGet(concurrentHashMap);
			totalC += testGet(hashtable);
		}
		System.out.println("Get time HashMapSync=" + totalA + "ms.");
		System.out.println("Get time ConcurrentHashMap=" + totalB + "ms.");
		System.out.println("Get time Hashtable=" + totalC + "ms.");
	}
	
	static class MapPutThread extends Thread {
		static int counter = 0;
		Object lock = new Object();
		private Map<String, Integer> map;
		private String key = this.getId() + "";
	
		MapPutThread(Map<String, Integer> map) {
			synchronized (lock) {
				counter++;
			}
			this.map = map;
		}
	
		public void run() {
			for (int i = 1; i <= ConcurrentMapTest.NUMBER; i++) {
				map.put(key, i);
			}
			synchronized (lock) {
				counter--;
			}
		}
	}

	static class MapGetThread extends Thread {
		static int counter = 0;
		final Object lock = new Object();
		private Map<String, Integer> map;
		private String key = this.getId() + "";
	
		MapGetThread(Map<String, Integer> map) {
			synchronized (lock) {
				counter++;
			}
			this.map = map;
		}
	
		public void run() {
			for (int i = 1; i <= ConcurrentMapTest.NUMBER; i++) {
				map.get(key);
			}
			synchronized (lock) {
				counter--;
			}
		}
	}

	public static long testPut(Map<String, Integer> map) throws Exception {
		long start = System.currentTimeMillis();
		for (int i = 0; i < threads; i++) {
			new MapPutThread(map).start();
		}
		while (MapPutThread.counter > 0) {
			Thread.sleep(1);
		}
		return System.currentTimeMillis() - start;
	}

	public static long testGet(Map<String, Integer> map) throws Exception {
		long start = System.currentTimeMillis();
		for (int i = 0; i < threads; i++) {
			new MapGetThread(map).start();
		}
		while (MapGetThread.counter > 0) {
			Thread.sleep(1);
		}
		return System.currentTimeMillis() - start;
	}
}

