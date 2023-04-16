package com.smallworld.entity;

public class Sender {
    User sender;

    public Sender(String senderFullName) {
        this.sender = new User(senderFullName);
    }

    public Sender(String senderFullName, int age) {
        this.sender = new User(senderFullName, age);
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    @Override
    public String toString() {
        return "Sender [sender=" + sender + "]";
    }
}
