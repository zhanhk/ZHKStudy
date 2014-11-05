package com.zhk.jdk.concurrent.exector;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadWaitNotify {
	
	private final static int THREAD_COUNT = 100;
	
	private final static int QUERY_MAX_LENGTH = 2;
	
	private final static AtomicInteger NOW_CALL_COUNT = new AtomicInteger(0);

	public static void main(String []args) throws InterruptedException {
		Thread []threads = new Thread[THREAD_COUNT];
		for(int i = 0 ; i < THREAD_COUNT ; i++) {
			threads[i] = new Thread(String.valueOf(i)) {
				synchronized public void run() {
					int nowValue = NOW_CALL_COUNT.get();
					while(true) {
						if(nowValue < QUERY_MAX_LENGTH && NOW_CALL_COUNT.compareAndSet(nowValue, nowValue + 1)) {
							break;//��ȡ����
						}
						try {
							this.wait(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						nowValue = NOW_CALL_COUNT.get();//��ȡһ�����ݣ����ڶԱ�
					}
					System.out.println(this.getName() + "======�ҿ�ʼ�������ˣ�");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(this.getName() + "======���������ˣ�");
					NOW_CALL_COUNT.getAndDecrement();
					this.notify();
				}
			};
		}
		for(int i = 0 ; i < THREAD_COUNT ; i++) {
			threads[i].start();
		}
	}
}
