package xyz.wochib70.validator.example.enums;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CustomEnumsValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EnumValidator {

    /**
     * 获取待验证的枚举类型
     *
     * @return
     */
    Class<? extends BaseEnum> enumClass();

    /**
     * 报错提示
     *
     * @return
     */
    String message() default "Invalid enum value";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
