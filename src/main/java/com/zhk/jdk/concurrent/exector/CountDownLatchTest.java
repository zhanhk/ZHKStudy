package com.zhk.jdk.concurrent.exector;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
	
	private final static int GROUP_SIZE = 5;
	
	public static void main(String []args) {
		processOneGroup("����1");
		processOneGroup("����2");
	}
	
	private static void processOneGroup(final String groupName) {
		final CountDownLatch start_count_down = new CountDownLatch(1);
		final CountDownLatch end_count_down = new CountDownLatch(GROUP_SIZE);
		System.out.println("==========================>\n���飺" + groupName + "������ʼ��");
		for(int i = 0 ; i < GROUP_SIZE ; i++) {
			new Thread(String.valueOf(i)) {
				public void run() {
					System.out.println("�����߳��飺��" + groupName + "��,�ڣ�" + this.getName() + " ���߳�,���Ѿ�׼��������");
					try {
						start_count_down.await();//�ȴ���ʼָ�������start_count_down.countDown();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("�����߳��飺��" + groupName + "��,�ڣ�" + this.getName() + " ���߳�,����ִ����ɣ�");
					end_count_down.countDown();
				}
			}.start();
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("���͸�λ��Ԥ����");
		start_count_down.countDown();//��ʼ����
		try {
			end_count_down.await();//�ȴ�����������������
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("���飺" + groupName + "����������");
	}
}
