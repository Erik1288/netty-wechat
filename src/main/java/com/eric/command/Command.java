package com.eric.command;

import com.eric.codec.Packet;

/**
 * User: Eric
 * Date: 15/10/2018
 */
public class Command {
    static Byte LOGIN_REQUEST = 1;
    static Byte LOGIN_RESPONSE = 2;

    public static Class<? extends Packet> getClassTypeByteByCommandId(byte commandId) {
        if (commandId == LOGIN_REQUEST) {
            return LoginRequestPacket.class;
        } else if (commandId == LOGIN_RESPONSE) {
            return LoginResponsePacket.class;
        }
        return null;
    }
}
