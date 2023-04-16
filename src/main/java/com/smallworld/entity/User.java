package com.smallworld.entity;

public class User {
    String fullname;
    int age;

    public User(String fullname, int age) {
        this.fullname = fullname;
        this.age = age;
    }

    public User(String fullname) {
        this.fullname = fullname;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User [fullname=" + fullname + ", Age=" + this.age + "]";
    }

}
