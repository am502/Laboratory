package ru.kpfu.itis.dao.factory;

import com.mongodb.*;

import java.util.Arrays;
import java.util.Properties;

public class MongoConnectionFactory {

    private static MongoConnectionFactory connectionFactory = null;

    // это клиент который обеспечит подключение к БД
    private MongoClient mongoClient;

    MongoCredential mongoCredential;

    private DB db;

    private DBCollection table;

    private MongoConnectionFactory(Properties prop) {
        // Создаем подключение
        mongoCredential = MongoCredential.createScramSha1Credential(
                "root",
                "admin",
                "root".toCharArray());
        mongoClient = new MongoClient(
                new ServerAddress("localhost", 27017),
                Arrays.asList(mongoCredential));

        // Выбираем БД для дальнейшей работы
        db = mongoClient.getDB("testdb");

        // Выбираем коллекцию/таблицу для дальнейшей работы
        table = db.getCollection(prop.getProperty("table"));

    }

    public static MongoConnectionFactory getInstance(String table) {
        if (connectionFactory == null) {
            Properties properties = new Properties();
            properties.setProperty("table", table);

            connectionFactory = new MongoConnectionFactory(properties);
        }
        return connectionFactory;
    }

    public DBCollection getTable() {
        return table;
    }

    public void shutdown() {
        mongoClient.close();
    }
}