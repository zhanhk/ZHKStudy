package com.zhk.jdk.concurrent.exector;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class BarrierTest {
	
	private static final int THREAD_COUNT = 10;
	
	private final static CyclicBarrier CYCLIC_BARRIER = new CyclicBarrier(THREAD_COUNT  ,
		new Runnable() {
			public void run() {
				System.out.println("======>���ǵ��Σ����ε���������׼������һ������!");
			}
		}
	);
	
	public static void main(String []args) 
			throws InterruptedException, BrokenBarrierException {
		for(int i = 0 ; i < 10 ; i++) {
			new Thread(String.valueOf(i)) {
				public void run() {
					try {
						System.out.println("�����̣߳�" + this.getName() + " ���Ǵﵽ���εص㣡");
						CYCLIC_BARRIER.await();
						System.out.println("�����̣߳�" + this.getName() + " �ҿ�ʼ�ﳵ��");
						CYCLIC_BARRIER.await();
						System.out.println("�����̣߳�" + this.getName() + " ���ǿ�ʼ��ɽ��");
						CYCLIC_BARRIER.await();
						System.out.println("�����̣߳�" + this.getName() + " ���ǻر�����Ϣ��");
						CYCLIC_BARRIER.await();
						System.out.println("�����̣߳�" + this.getName() + " ���ǿ�ʼ�˳��ؼң�");
						CYCLIC_BARRIER.await();
						System.out.println("�����̣߳�" + this.getName() + " ���ǵ����ˣ�");
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (BrokenBarrierException e) {
						e.printStackTrace();
					}
				}
			}.start();
		}
	}
}
