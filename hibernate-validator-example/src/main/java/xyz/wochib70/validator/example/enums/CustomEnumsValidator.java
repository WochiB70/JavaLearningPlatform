package xyz.wochib70.validator.example.enums;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;

public class CustomEnumsValidator implements ConstraintValidator<EnumValidator, Integer> {

    private Class<? extends BaseEnum> enumClass;

    public CustomEnumsValidator() {
        System.out.println("CustomEnumsValidator init");
    }

    @Override
    public void initialize(EnumValidator constraintAnnotation) {
        enumClass = constraintAnnotation.enumClass();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (Objects.isNull(value)) {
            return false;
        }
        return BaseEnum.of(value, enumClass).isPresent();
    }


}
