package netty;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.Executors;

/**
 * Created by shihc on 2015/8/18.
 */
public class HelloClient {
    public static void main(String args[]) {
        // Client服务启动器
        ClientBootstrap bootstrap = new ClientBootstrap(
                new NioClientSocketChannelFactory(
                        Executors.newCachedThreadPool(),
                        Executors.newCachedThreadPool()));
        // 设置一个处理服务端消息和各种消息事件的类(Handler)
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                return org.jboss.netty.channel.Channels.pipeline(new HelloClientHandler());
            }
        });
        // 连接到本地的8000端口的服务端
        bootstrap.connect(new InetSocketAddress("10.10.24.19", 8000));
    }

    private static class HelloClientHandler extends SimpleChannelHandler {
        /**
         * 当绑定到服务端的时候触发，给服务端发消息。
         */
        @Override
        public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
            // 将字符串，构造成ChannelBuffer，传递给服务端
            String msg = "Hello, I'm client.";
            ChannelBuffer buffer = ChannelBuffers.buffer(msg.length());
            buffer.writeBytes(msg.getBytes());
            e.getChannel().write(buffer);
        }

        // 客户端收到服务端发送过来的消息时，触发此方法
        @Override
        public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
            ChannelBuffer buffer = (ChannelBuffer)e.getMessage();
            System.out.println("Receive:" + buffer.toString(Charset.defaultCharset()));
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
            System.out.println("exceptionCaught");
        }
    }
}
