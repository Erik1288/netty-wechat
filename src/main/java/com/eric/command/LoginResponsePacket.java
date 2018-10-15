package com.eric.command;

import com.eric.codec.Packet;

/**
 * User: Eric
 * Date: 15/10/2018
 */
public class LoginResponsePacket extends Packet {
    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
