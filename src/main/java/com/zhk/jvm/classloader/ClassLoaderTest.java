package com.zhk.jvm.classloader;

public class ClassLoaderTest {

	/**
	 * @Description:������
	 * @param args
	 * @author YHJ create at 2011-6-4 ����08:30:12
	 */
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		Singleton singleton = Singleton.getInstance();
		System.out.println("counter1:" + singleton.getCounter1());
		System.out.println("counter2:" + singleton.getCounter2());
	}
	
	/**
	�����Ȳ²�һ�����н��
	Ȼ��������������һ�µ�ʵ�����ɵ�˳�򣬽�
	    private static Singleton singleton=new Singleton();
	    private static int counter1;
	    private static int counter2 = 0;
	�޸�Ϊ
	    private static int counter1;
	    private static int counter2 = 0;
	    private static Singleton singleton=new Singleton();
	*/
}
