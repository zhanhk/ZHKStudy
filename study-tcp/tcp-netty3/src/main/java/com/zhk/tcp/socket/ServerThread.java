package com.zhk.tcp.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketException;

/**
 * 该类为多线程类，用于服务端
 *
 * @author zhanhk
 * @version 1.0
 * @date 2015-05-28
 * @modify
 * @copyright Navi Tsp
 */
public class ServerThread implements Runnable {

    private Socket client = null;

    public ServerThread(Socket client) {
        this.client = client;
    }

    //处理通信细节的静态方法，这里主要是方便线程池服务器的调用
    public static void execute(Socket client) {

        String message = client.getLocalAddress().getHostAddress() + ":" + client.getPort() + "与客户端断开连接！conn:" + ServerExecutor.count.decrementAndGet();
        try {
            //获取Socket的输出流，用来向客户端发送数据
            PrintStream out = new PrintStream(client.getOutputStream());
            //获取Socket的输入流，用来接收从客户端发送过来的数据
            BufferedReader buf = new BufferedReader(new InputStreamReader(client.getInputStream()));
            boolean flag = true;
            while (flag) {
                //接收从客户端发送过来的数据
                String str = null;
                try {
                    str = buf.readLine();
                } catch (SocketException e) {
                    System.out.println(message);
                    flag = false;
                }

                if (str == null || "".equals(str)) {
                    flag = false;
                } else {
                    if ("bye".equals(str)) {
                        flag = false;
                        System.out.println(message);
                    } else {
                        //将接收到的字符串前面加上echo，发送到对应的客户端
                        out.println("echo:" + str);
                    }
                }
            }
            out.close();
            buf.close();
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        execute(client);
    }
}

