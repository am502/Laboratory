package ru.kpfu.itis.utils.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by artur on 31.03.2017.
 */
public class DateCheckValidator implements ConstraintValidator<DateCheck, Object> {

    @Override
    public void initialize(DateCheck dateCheck) {
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            return ((Date) value).after(new Date(System.currentTimeMillis()));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
