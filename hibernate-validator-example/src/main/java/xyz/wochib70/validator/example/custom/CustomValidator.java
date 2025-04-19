package xyz.wochib70.validator.example.custom;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;

public class CustomValidator implements ConstraintValidator<Custom, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (Objects.isNull(value)){
            return false;
        }
        return value.contains("custom");
    }

}
