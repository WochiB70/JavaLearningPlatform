package xyz.wochib70.validator.example.enums;

import java.util.Objects;
import java.util.Optional;

public interface BaseEnum {


    Integer getCode();


    static <T extends BaseEnum> Optional<T> of(Integer code, Class<T> enumClass) {
        for (T each : enumClass.getEnumConstants()) {
            if (Objects.equals(each.getCode(), code)) {
                return Optional.of(each);
            }
        }
        return Optional.empty();
    }
}

