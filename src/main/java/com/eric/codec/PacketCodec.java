package com.eric.codec;

import com.eric.command.Command;
import com.eric.serialize.Serializer;
import com.eric.serialize.SerializerType;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * User: Eric
 * Date: 15/10/2018
 *
 */
public class PacketCodec {
    private static final int MAGIC_NUMBER = 0x12345678;

    /**
     * 解码
     * @param bytebuf 二进制数据
     * @return 数据包
     */
    public static Packet decode(ByteBuf bytebuf) {
        // 跳过magic
        bytebuf.skipBytes(4);

        byte version = bytebuf.readByte();

        byte serializeAlgorithm = bytebuf.readByte();

        byte command = bytebuf.readByte();

        int length = bytebuf.readInt();

        // 不要这么写，这么写效率低
        // ByteBuf content = bytebuf.readBytes(length);

        // 怎么写效率高点
        // ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();
        // bytebuf.readBytes(byteBuf, length);
        byte[] content = new byte[length];
        bytebuf.readBytes(content);

        Class<? extends Packet> klass = Command.getClassTypeByteByCommandId(command);
        Serializer serializer = SerializerType.findSerializerType(serializeAlgorithm);

        // byte[] -> Packet
        return serializer.deserialize(klass, content);
    }

    public static ByteBuf encode(Packet packet) {
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }
}
