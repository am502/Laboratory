package ru.itis.inform;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Checker {
    ReaderWriter rw = new ReaderWriter();
    Pattern pattern;

    public boolean task1(){
        pattern = Pattern.compile("([\\s]{4}[А-Я][а-я]+([,]?[\\s][а-я]+)*.)" +
                "(([\\s]|[\\s]{4})[А-Я][а-я]+([,]?[\\s][а-я]+)*.)*");
        Matcher matcher = pattern.matcher(rw.reader());
        return matcher.matches();
    }

    public int task2(String s){
        pattern = Pattern.compile(s);
        Matcher matcher = pattern.matcher(rw.reader());
        int count = 0;
        while(matcher.find()){
            count++;
        }
        return count;
    }

    public void task3(String subString, String replacement){
        pattern = Pattern.compile(subString);
        Matcher matcher = pattern.matcher(rw.reader());
        rw.writer(matcher.replaceAll(replacement));
    }
}