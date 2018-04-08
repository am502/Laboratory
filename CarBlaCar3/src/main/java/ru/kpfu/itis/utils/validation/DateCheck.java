package ru.kpfu.itis.utils.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by artur on 10.04.2017.
 */

@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = DateCheckValidator.class)
@Documented
public @interface DateCheck {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
