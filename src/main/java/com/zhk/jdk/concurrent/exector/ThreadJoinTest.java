package com.zhk.jdk.concurrent.exector;

public class ThreadJoinTest {
	
	private final static int GROUP_SIZE = 5;

	public static void main(String []args) throws InterruptedException {
		Thread []threadGroup1 = new Thread[5];
		Thread []threadGroup2 = new Thread[5];
		for(int i = 0 ; i < GROUP_SIZE ; i++) {
			final int num = i;
			threadGroup1[i] = new Thread() {
				public void run() {
					int j = 0;
					while(j++ < 10) {
						System.out.println("����1�����̣߳�" + num + " ������ҵڣ�" + j + " �����У�");
					}
				}
			};
			threadGroup2[i] = new Thread() {
				public void run() {
					int j = 0;
					while(j++ < 10) {
						System.out.println("����2�����̣߳�" + num + " ������ҵڣ�" + j + " �����У�");
					}
				}
			};
			threadGroup1[i].start();
		}
		for(int i = 0 ; i < GROUP_SIZE ; i++) {
			threadGroup1[i].join();
		}
		System.out.println("-==================>�߳���1ִ�����ˣ����ֵ����ˣ�");
		for(int i = 0 ; i < GROUP_SIZE ; i++) {
			threadGroup2[i].start();
		}
		for(int i = 0 ; i < GROUP_SIZE ; i++) {
			threadGroup2[i].join();
		}
		System.out.println("ȫ�����������������ؼҺ�ϡ����");
	}
}
