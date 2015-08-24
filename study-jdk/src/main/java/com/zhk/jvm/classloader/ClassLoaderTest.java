package com.zhk.jvm.classloader;

public class ClassLoaderTest {

    /**
     * @param args
     * @Description:启动类
     * @author YHJ create at 2011-6-4 下午08:30:12
     */
    @SuppressWarnings("static-access")
    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
        System.out.println("counter1:" + singleton.getCounter1());
        System.out.println("counter2:" + singleton.getCounter2());
    }

    /**
     我们先猜测一下运行结果
     然后我们再来调换一下单实例生成的顺序，将
     private static Singleton singleton=new Singleton();
     private static int counter1;
     private static int counter2 = 0;
     修改为
     private static int counter1;
     private static int counter2 = 0;
     private static Singleton singleton=new Singleton();
     */
}
