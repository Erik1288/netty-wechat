package com.eric.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

/**
 * User: Eric
 * Date: 15/10/2018
 */
public class FirstClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 1. 获取数据
        ByteBuf byteBuf = ctx.alloc().buffer();
        byteBuf.writeBytes(("hello world").getBytes());

        // 2. 写数据
        ctx.channel().writeAndFlush(byteBuf);

        System.out.println("Client: sending " + "hello world");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf response = (ByteBuf) msg;

        System.out.println("Client: receiving " + response.toString(Charset.forName("utf-8")));
    }
}
