package ru.itis.inform;

public class Main {
    public static void main(String[] args) {
        User user1 = new User(0, "Wayne");
        User user2 = new User(1, "Jill");
        User user3 = new User(2, "Kate");

//        UsersDao db = new UsersDaoJdbcBasedImpl("users");
//
//        db.save(user1);
//        db.save(user2);
//        db.save(user3);
//
//        db.find(0);
//        db.find(1);
//        db.find(2);

        UsersDao dbtemp = new UsersDaoJdbcTemplateBasedImpl("users");

        dbtemp.save(user1);
        dbtemp.save(user2);
        dbtemp.save(user3);

        dbtemp.find(0);
        dbtemp.find(1);
        dbtemp.find(2);
    }
}