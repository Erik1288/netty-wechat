package com.eric.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

/**
 * User: Eric
 * Date: 15/10/2018
 */
public class FirstServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("Server: receive " + byteBuf.toString(Charset.forName("utf-8")));


        ByteBuf buffer = ctx.alloc().buffer();
        ctx.channel().writeAndFlush(buffer.writeBytes("got it".getBytes()));
        System.out.println("Server: send " + "got it.");
    }
}
