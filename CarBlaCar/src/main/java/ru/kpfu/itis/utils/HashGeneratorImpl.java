package ru.kpfu.itis.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by artur on 27.03.2017.
 */

@Component
public class HashGeneratorImpl implements HashGenerator {
    private PasswordEncoder encoder;

    public HashGeneratorImpl() {
        encoder = new BCryptPasswordEncoder();
    }

    @Override
    public String encode(String password) {
        return encoder.encode(password);
    }

    @Override
    public boolean match(String rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }
}
