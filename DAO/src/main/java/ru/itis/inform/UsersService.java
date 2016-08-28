package ru.itis.inform;

import java.util.*;

public class UsersService {
    private UsersDao usersDao;

    public UsersService(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    public boolean isRegistered(String userName) {
        ArrayList<User> allUsers = usersDao.findAll();

        for(User user : allUsers) {
            if (user.getName().equals(userName)) {
                return true;
            }
        }

        return false;
    }
}