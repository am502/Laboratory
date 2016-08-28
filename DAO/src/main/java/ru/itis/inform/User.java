package ru.itis.inform;

public class User {
    private String name;
    private int id;
    public User(int id, String name){
        this.id = id;
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public int getId(){
        return id;
    }

    public String toString(){
        return id + " " + name;
    }
}