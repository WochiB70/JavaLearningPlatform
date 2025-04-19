package xyz.wochib70.validator.example.enums;

import java.util.Optional;

public enum TwoEnum implements BaseEnum {
    ENUM_TWO_A(11),
    ENUM_TWO_B(12),
    ;

    private final Integer code;

    TwoEnum(Integer code) {
        this.code = code;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    public static Optional<TwoEnum> of(Integer code) {
        return BaseEnum.of(code, TwoEnum.class);
    }
}
