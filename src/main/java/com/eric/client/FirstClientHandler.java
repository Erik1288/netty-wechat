package com.eric.client;

import com.eric.codec.Packet;
import com.eric.codec.PacketCodec;
import com.eric.command.LoginRequestPacket;
import com.eric.command.LoginResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * User: Eric
 * Date: 15/10/2018
 */
public class FirstClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(1);
        loginRequestPacket.setUsername("eric1");
        loginRequestPacket.setPassword("psd");

        ByteBuf byteBuf = PacketCodec.encode(loginRequestPacket);
        ctx.channel().writeAndFlush(byteBuf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf responseByteBuf = (ByteBuf) msg;

        Packet packet = PacketCodec.decode(responseByteBuf);

        if (packet instanceof LoginResponsePacket) {
            LoginResponsePacket responsePacket = (LoginResponsePacket) packet;
            if (responsePacket.isSuccess()) {
                System.out.println("登录成功");
            } else {
                System.out.println("登录失败");
            }
        }
    }
}
