package itis;

public interface UsersDao {
    void saveUser(User user);
    User getUser(int userId);
}
