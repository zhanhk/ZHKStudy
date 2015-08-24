package netty;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.Executors;

/**
 * Created by shihc on 2015/8/18.
 */
public class HelloServer {
    public static void main(String[] args) {
        // Server服务启动器，通过构造方法指定一个ChannelFactory实现
        //其中后两个参数分别是BOSS和WORK的线程池
        ServerBootstrap bootstrap = new ServerBootstrap(
                new NioServerSocketChannelFactory(
                        Executors.newCachedThreadPool(),
                        Executors.newCachedThreadPool()));
        // 设置一个处理客户端消息和各种消息事件的类(Handler)
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                return Channels.pipeline(new HelloServerHandler());
            }
        });
        // 开放8000端口供客户端访问。
        bootstrap.bind(new InetSocketAddress(8000));
    }

    private static class HelloServerHandler extends SimpleChannelHandler {

        //channelOpen => channelConnected
        /**
         * 先打开连接
         * @param ctx
         * @param e
         * @throws Exception
         */
        public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
            System.out.println("channelOpen");
        }

        /**
         * 客户端连接后，再触发此方法
         * @param ctx
         * @param e
         */
        @Override
        public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
            System.out.println("channelConnected");
        }

        // 服务端收到客户端发送过来的消息时，触发此方法
        @Override
        public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
            ChannelBuffer buffer = (ChannelBuffer)e.getMessage();
            System.out.println("Receive:"+buffer.toString(Charset.defaultCharset()));
            String msg = buffer.toString(Charset.defaultCharset()) + "has been processed!";
            ChannelBuffer buffer2 = ChannelBuffers.buffer(msg.length());
            buffer2.writeBytes(msg.getBytes());
            e.getChannel().write(buffer2);
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
            System.out.println("exceptionCaught");
        }
    }
}
