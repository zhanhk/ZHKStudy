package com.zhk.tcp.netty4.server;

import com.zhk.util.DateUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Desc:
 * @Author: zhanhk
 * @Date: Created in 下午2:51 18/12/25
 * @copyright Navi WeCloud
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<Object> {
    static AtomicInteger count = new AtomicInteger(1);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;
        System.out.println(count.getAndIncrement() + ":" + body + ctx.toString());
        ctx.writeAndFlush(DateUtil.getNowDate("yyyy-MM-dd HH:mm:ss")+"-Welcome to Netty-"+body+".$_");
    }

    /**
     * 客户端断开连接
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelInactive:"+ctx.toString());
        ctx.fireChannelActive();
    }
}