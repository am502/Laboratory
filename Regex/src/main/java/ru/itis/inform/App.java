package ru.itis.inform;

public class App {
    public static void main(String[] args) {
        Checker ch = new Checker();
        System.out.println(ch.task1());
        System.out.println(ch.task2("кол-во вхождение данной строки"));
        ch.task3("строка, которую хотим изменить", "на что изменить");
    }
}