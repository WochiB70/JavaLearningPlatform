package xyz.wochib70.exam.domain;

import java.util.Objects;
import java.util.Optional;

public interface BaseEnum {


    String getCode();

    String getDescription();


    static <T extends BaseEnum> Optional<T> of(String code, Class<T> clazz) {
        for (T enumConstant : clazz.getEnumConstants()) {
            if (Objects.equals(enumConstant.getCode(), code)) {
                return Optional.of(enumConstant);
            }
        }

        return Optional.empty();
    }
}
