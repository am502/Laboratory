package ru.kpfu.itis;

import java.util.ArrayList;

/**
 * Created by artur on 22.05.2017.
 */
public class Student {
    int id;
    String name;
    ArrayList<Score> scores;

    public Student(int id, String name, ArrayList<Score> scores) {
        this.id = id;
        this.name = name;
        this.scores = scores;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Score> getScores() {
        return scores;
    }

    public void setScores(ArrayList<Score> scores) {
        this.scores = scores;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", scores=" + scores +
                '}';
    }
}
