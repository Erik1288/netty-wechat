package com.eric.command;

import com.eric.codec.Packet;

import java.io.Serializable;

/**
 * User: Eric
 * Date: 15/10/2018
 */
public class LoginRequestPacket extends Packet implements Serializable {
    private Integer userId;

    private String username;

    private String password;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
