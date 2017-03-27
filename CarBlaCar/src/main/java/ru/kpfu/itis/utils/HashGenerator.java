package ru.kpfu.itis.utils;

/**
 * Created by artur on 27.03.2017.
 */
public interface HashGenerator {

    String encode(String password);

    boolean match(String rawPassword, String encodedPassword);
}
