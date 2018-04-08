package ru.itis.inform.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.itis.inform.config.AppConfig;
import ru.itis.inform.impl.Geralt;

/**
 * Created by artur on 14.02.2017.
 */

public class App {
    public static void main(String[] args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(
                AppConfig.class);
        Geralt geralt = (Geralt) context.getBean("geralt");
        geralt.attack();
        geralt.defend();

        ApplicationContext contextXml = new ClassPathXmlApplicationContext("app-context.xml");
        Geralt geraltOfRivia = (Geralt) contextXml.getBean("geralt");
        geraltOfRivia.attack();
        geraltOfRivia.defend();
    }
}
