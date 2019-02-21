package com.zhk.jdk.concurrent.future;

import java.util.concurrent.*;

/**
 *我们通常都是开启一个新的子线程去执行比较耗时的代码，这使用起来非常简单，只需要将耗时的代码封装在Runnable中的run()方法里面，
 *
 * 然后调用thread.start()就行。但是我相信很多人有时候都有这样的需求，就是获取子线程运行的结果，比如客户端远程调用服务（耗时服务），
 *
 * 我们有需要得到该调用服务返回的结果，这该怎么办呢？很显然子线程运行的run()方法是没有返回值。这个时候Future的作用就发挥出来了。
 *
 * @Desc:
 * @Author: zhanhk
 * @Date: Created in 下午2:46 18/12/21
 * @copyright Navi WeCloud
 */
public class FutureThreadTest {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Callable<Integer> calculateCallable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                // TODO Auto-generated method stub
                Thread.sleep(2000);//模拟耗时时间
                int result = 1+2;
                return result;
            }
        };
        FutureTask<Integer> calculateFutureTask = new FutureTask<>(calculateCallable);
        Thread t1 = new Thread(calculateFutureTask);
        t1.start();
        //现在加入Thread运行的是一个模拟远程调用耗时的服务，并且依赖他的计算结果（比如网络计算器）
        try {
            //模拟耗时任务，主线程做自己的事情，体现多线程的优势
            Thread.sleep(3000);
            int a = 3+5;
            Integer result = calculateFutureTask.get();
            System.out.println("result = "+(a+result));//模拟主线程依赖子线程的运行结果
            long endTime = System.currentTimeMillis();
            System.out.println("time = "+(endTime-startTime)+"ms");
        } catch (InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
