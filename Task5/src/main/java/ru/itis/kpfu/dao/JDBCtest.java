package ru.itis.kpfu.dao;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCtest {

    public static void main(String[] args) {

        Connection connection = null;
        String url = "jdbc:postgresql://localhost:5432/test";
        String name = "postgres";
        String password = "postgres";
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Драйвер подключен");
            connection = DriverManager.getConnection(url, name, password);
            System.out.println("Cоединение установлено");
            //используется для простых случаев без параметров
            Statement statement = null;

            statement = connection.createStatement();
            //выполним запрос
            ResultSet result1 = statement.executeQuery(
                    "SELECT * FROM users where id >2 and id <10");
            //result указатель на первую строку с выборки
            //чтобы вывести данные , мы будем использовать
            //метод next() с помощью которого переходим к следующему элементу
            System.out.println("Выводим statement");
            while (result1.next()) {
                System.out.println("Номер в выборке #" + result1.getRow()
                        + "\t Номер в базе #" + result1.getInt("id")
                        + "\t" + result1.getString("name"));
            }
            // Вставить запись
            statement.executeUpdate(
                    "INSERT INTO users(id,name,password) values(9,'name','password')");
            //Обновить запись
            statement.executeUpdate(
                    "UPDATE users SET name = 'admin' where id = 1");



            //2.PreparedStatement: предварительно компилирует запросы,
            // которые могут содержать входные параметры
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM users where id > ? and id < ?");
            //устанавливаем в нужную позицию значения определенного типа
            preparedStatement.setInt(1, 2);
            preparedStatement.setInt(2, 10);
            //выполняем запрос
            ResultSet result2 = preparedStatement.executeQuery();

            System.out.println("Выводим PreparedStatement");
            while (result2.next()) {
                System.out.println("Номер в выборке #" + result2.getRow()
                        + "\t Номер в базе #" + result2.getInt("id")
                        + "\t" + result2.getString("name"));
            }

            preparedStatement = connection.prepareStatement(
                    "INSERT INTO users(name,password) values(?,?)");
            preparedStatement.setString(1, "user_name");
            preparedStatement.setString(2, "user_name");
            //метод принимает значение без параметров
            //тем же способом можно сделать и АПДЕЙТ
            preparedStatement.executeUpdate();



            //3.CallableStatement: используется для вызова хранимых функций,
            // которые могут содержать входные и выходные параметры
            CallableStatement callableStatement = null;
            //вызываем функцию (хранится в бд)
            callableStatement = connection.prepareCall(
                    " { call myfunc(?,?) } ");
            //Задаем входные параметры
            callableStatement.setString(1, "Dima");
            callableStatement.setString(2, "Alex");
            //Выполняем запрос
            ResultSet result3 = callableStatement.executeQuery();
            //Если CallableStatement возвращает несколько объектов резалт сет
            // то нужно выводить данные в цикле с помощью метода next
            result3.next();
            System.out.println(result3.getString("MESSAGE"));
            //?если функция вставляет или обновляет, то используется метод executeUpdate()

        } catch (Exception ex) {
            //выводим наиболее значимые сообщения
            Logger.getLogger(JDBCtest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCtest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }
}