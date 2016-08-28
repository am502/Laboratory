package ru.itis.inform;

import java.util.*;

public class UsersDaoFileBasedImpl implements UsersDao {
    ArrayList<User> users = new ArrayList<User>();

    public void save(User user) {
        users.add(user);
    }

    public User find(int id) {
        return users.get(id);
    }

    public ArrayList<User> findAll() {
        return users;
    }
}