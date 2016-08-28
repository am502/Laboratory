package ru.itis.inform;

import java.util.*;

interface UsersDao {
    void save(User user);
    User find(int id);
    ArrayList<User> findAll();
}