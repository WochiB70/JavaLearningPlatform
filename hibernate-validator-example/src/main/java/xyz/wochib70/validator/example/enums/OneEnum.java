package xyz.wochib70.validator.example.enums;

import java.util.Optional;

public enum OneEnum implements BaseEnum {
    ENUM_ONE_A(1),
    ENUM_ONE_B(2);

    private final Integer code;

    OneEnum(Integer code) {
        this.code = code;
    }

    @Override
    public Integer getCode() {
        return code;
    }


    public static Optional<OneEnum> of(Integer code) {
        return BaseEnum.of(code, OneEnum.class);
    }
}
