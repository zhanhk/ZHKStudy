package com.zhk.jdk.concurrent.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
	public volatile static int count = 0;
	
	public static AtomicInteger countAtomic = new AtomicInteger(0);  

	public synchronized static void synchFunctioninc() {
		// �����ӳ�5���룬ʹ�ý������
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
		}
		count++;
	}
	
	public static void synchObjectinc() {
		// �����ӳ�5���룬ʹ�ý������
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
		}
		synchronized(Counter.class) {
			count++;
		}
	}
	
	public static void atomicinc() {
		// �����ӳ�5���룬ʹ�ý������
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
		}
		countAtomic.getAndIncrement();  
	}

	public static void main(String[] args) throws InterruptedException {
		long start = System.currentTimeMillis();  
		final CountDownLatch latch = new CountDownLatch(1000);
		// ͬʱ����1000���̣߳�ȥ����i++���㣬����ʵ�ʽ��
		for (int i = 0; i < 1000; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					Counter.synchFunctioninc();
					latch.countDown();
				}
			}).start();
		}
		latch.await();
		// ����ÿ�����е�ֵ���п��ܲ�ͬ,����Ϊ1000
		long end = System.currentTimeMillis();  
		System.out.println("���н��:Counter.count=" + Counter.count+" synch function time elapse:"+(end -start));
		
		count = 0;
		start = System.currentTimeMillis();  
		final CountDownLatch latch1 = new CountDownLatch(1000);
		// ͬʱ����1000���̣߳�ȥ����i++���㣬����ʵ�ʽ��
		for (int i = 0; i < 1000; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					Counter.synchObjectinc();
					latch1.countDown();
				}
			}).start();
		}
		latch1.await();
		// ����ÿ�����е�ֵ���п��ܲ�ͬ,����Ϊ1000
		end = System.currentTimeMillis();  
		System.out.println("���н��:Counter.count=" + Counter.count+" synch object time elapse:"+(end -start));
		
		
		count = 0;
		start = System.currentTimeMillis();  
		final CountDownLatch latch2 = new CountDownLatch(1000);
		// ͬʱ����1000���̣߳�ȥ����i++���㣬����ʵ�ʽ��
		for (int i = 0; i < 1000; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					Counter.atomicinc();
					latch2.countDown();
				}
			}).start();
		}
		latch2.await();
		// ����ÿ�����е�ֵ���п��ܲ�ͬ,����Ϊ1000
		end = System.currentTimeMillis();  
		System.out.println("���н��:Counter.count=" + Counter.countAtomic+" atomic time elapse:"+(end -start));
	}
}
