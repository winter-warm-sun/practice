package net.message;

import java.io.DataInputStream;
import java.net.DatagramSocket;

public class Message {
    String sender;
    String getter;
    String type;
    String sendTime;



    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getGetter() {
        return getter;
    }

    public void setGetter(String getter) {
        this.getter = getter;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public Message() {
    }
    public Message(String sender, String getter) {
        this.sender = sender;
        this.getter = getter;
    }

    public void send(DatagramSocket ds, String IP, int udpPort) {

    }
    public void parse(DataInputStream dis){

    }
}
