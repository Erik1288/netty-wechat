package com.eric.server;

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
public class FirstServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;

        Packet packet = PacketCodec.decode(byteBuf);

        LoginResponsePacket responsePacket = new LoginResponsePacket();
        responsePacket.setVersion(packet.getVersion());
        if (packet instanceof LoginRequestPacket) {
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;

            if (valid(loginRequestPacket)) {
                responsePacket.setSuccess(true);
            } else {
                responsePacket.setSuccess(false);
            }
        }

        ByteBuf buf = PacketCodec.encode(responsePacket);
        ctx.channel().writeAndFlush(buf);
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        if ("eric".equals(loginRequestPacket.getUsername()))
            return true;
        else
            return false;
    }
}