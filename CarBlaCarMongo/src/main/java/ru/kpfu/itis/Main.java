package ru.kpfu.itis;

import com.mongodb.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by artur on 22.05.2017.
 */
public class Main {
    public static void main(String[] args) {
        Mongo m = new MongoClient("localhost" , 27017);
        DB db = m.getDB( "school");

        Set<String> colls = db.getCollectionNames();
        DBCollection coll = db.getCollection("students");

        ArrayList<Student> students = new ArrayList<>();

        DBCursor cursor = coll.find();
        while (cursor.hasNext()){
            BasicDBObject obj = (BasicDBObject) cursor.next();
            int id = obj.getInt("_id");
            String name = obj.getString("name");
            BasicDBList dbList = (BasicDBList) obj.get("scores");

            ArrayList<Score> scores = new ArrayList<>();
            for (int i = 0; i < dbList.size(); i++) {
                BasicDBObject scoreObj = (BasicDBObject) dbList.get(i);
                String type = scoreObj.getString("type");
                double score = scoreObj.getDouble("score");
                scores.add(new Score(type, score));
            }
            students.add(new Student(id, name, scores));
        }

        for (int i = 0; i < students.size(); i++) {
            BasicDBObject newDocument = new BasicDBObject();
            BasicDBList scores = new BasicDBList();

            ArrayList<Score> updatedList = f(students.get(i).getScores());

            for (int j = 0; j < updatedList.size(); j++) {
                scores.add(new BasicDBObject("type", updatedList.get(j).getType()).append("score", updatedList.get(j).getScore()));
            }

            newDocument.put("$set", new BasicDBObject("scores", scores));

            BasicDBObject searchQuery = new BasicDBObject().append("_id", students.get(i).getId());

            coll.update(searchQuery, newDocument);
        }

        for (int i = 0; i < students.size(); i++) {
            System.out.println(students.get(i));
        }
    }

    public static ArrayList<Score> f(ArrayList<Score> scores) {
        double min = 100;
        int index = 0;
        for (int i = 0; i < scores.size(); i++) {
            if (scores.get(i).getScore() < min) {
                min = scores.get(i).getScore();
                index = i;
            }
        }
        scores.remove(index);
        return scores;
    }
}
