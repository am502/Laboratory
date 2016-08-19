package itis;

import java.util.ArrayList;

public class UserDaoFileBasedImpl implements UsersDao {
    private ArrayList<User> list;

    public void saveUser(User user){
        list.add(user);
    }

    public User getUser(int userId){
        return
    }
}
