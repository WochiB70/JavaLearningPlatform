package xyz.wochib70.validator.example.custom;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = CustomValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Custom {

    String message() default "字符串必须包含'custom'";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
