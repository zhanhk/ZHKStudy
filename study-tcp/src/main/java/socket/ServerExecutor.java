package socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 该类通过Executor接口实现服务器
 *
 * @author zhanhk
 * @version 1.0
 * @date 2015-05-28
 * @modify
 * @copyright Navi Tsp
 */
public class ServerExecutor {

    public static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws IOException {
        //服务端在20006端口监听客户端请求的TCP连接
        ServerSocket server = new ServerSocket(20006);

        System.out.println("start listening ...");
        Socket client = null;
        //通过调用Executors类的静态方法，创建一个ExecutorService实例
        //ExecutorService接口是Executor接口的子接口
        Executor service = Executors.newCachedThreadPool();
        boolean f = true;
        while (f) {
            //等待客户端的连接
            client = server.accept();
            count.getAndIncrement();
            System.out.println(client.getLocalAddress().getHostAddress() + ":" + client.getPort() + "与客户端连接成功！conn :" + count.intValue());
            //调用execute()方法时，如果必要，会创建一个新的线程来处理任务，但它首先会尝试使用已有的线程，
            //如果一个线程空闲60秒以上，则将其移除线程池；
            //另外，任务是在Executor的内部排队，而不是在网络中排队
            service.execute(new ServerThread(client));
        }
        server.close();
    }
}
