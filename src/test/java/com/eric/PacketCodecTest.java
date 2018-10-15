package com.eric;

import com.eric.codec.Packet;
import com.eric.codec.PacketCodec;
import com.eric.command.LoginRequestPacket;
import io.netty.buffer.ByteBuf;
import org.junit.Assert;
import org.junit.Test;

/**
 * User: Eric
 * Date: 15/10/2018
 */
public class PacketCodecTest {
    @Test
    public void TestCodec() {
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(1);
        loginRequestPacket.setUsername("eric");
        loginRequestPacket.setPassword("psd");

        ByteBuf byteBuf = PacketCodec.encode(loginRequestPacket);

        Packet packet = PacketCodec.decode(byteBuf);

        Assert.assertNotNull(packet);
        Assert.assertTrue(packet instanceof LoginRequestPacket);


    }
}
