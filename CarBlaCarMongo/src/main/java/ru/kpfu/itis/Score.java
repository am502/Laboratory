package ru.kpfu.itis;

/**
 * Created by artur on 22.05.2017.
 */
public class Score {
    String type;
    double score;

    public Score(String type, double score) {
        this.type = type;
        this.score = score;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Score{" +
                "type='" + type + '\'' +
                ", score=" + score +
                '}';
    }
}
