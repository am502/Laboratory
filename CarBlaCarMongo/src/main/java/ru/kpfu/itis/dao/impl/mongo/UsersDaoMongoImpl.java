package ru.kpfu.itis.dao.impl.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import ru.kpfu.itis.dao.UsersDao;
import ru.kpfu.itis.dao.factory.MongoConnectionFactory;
import ru.kpfu.itis.model.User;

import java.util.List;

public class UsersDaoMongoImpl implements UsersDao{

    @Override
    public void save(User user) {
        BasicDBObject document = new BasicDBObject();

        // указываем поле с объекта User
        // это поле будет записываться в коллекцию/таблицу
        document.put("nickname", user.getNickname());
        document.put("email", user.getEmail());

        MongoConnectionFactory factory = MongoConnectionFactory.getInstance("users");
        // записываем данные в коллекцию/таблицу
            factory.getTable().insert(document);
    }

    @Override
    public User findOne(Long userId) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findByNickname(String nickname) {
        User user = null;

        BasicDBObject query = new BasicDBObject();
        // задаем поле и значение поля по которому будем искать
        query.put("nickname", nickname);

        MongoConnectionFactory factory = MongoConnectionFactory.getInstance("users");
        // осуществляем поиск
            DBObject result = factory.getTable().findOne(query);
            // Заполняем сущность полученными данными с коллекции
            user = new User();
            user.setNickname(result.get("nickname").toString());
            user.setEmail(result.get("email").toString());
        // возвращаем полученного пользователя
        return user;
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }

    @Override
    public User findByNicknameIgnoreCase(String name) {
        return null;
    }

    @Override
    public User findByEmailIgnoreCase(String email) {
        return null;
    }

    /**
     * @param email - это старая почта пользователя
     * @param newEmail- это новая почта который мы хотим задать
     */
    public void updateByEmail(String email, String newEmail){
        BasicDBObject newData = new BasicDBObject();

        // задаем новый логин
        newData.put("email", newEmail);

        // указываем обновляемое поле и текущее его значение
        BasicDBObject searchQuery = new BasicDBObject().append("email", email);

        MongoConnectionFactory factory = MongoConnectionFactory.getInstance("users");
        // обновляем запись
            factory.getTable().update(searchQuery, newData);

    }
}
