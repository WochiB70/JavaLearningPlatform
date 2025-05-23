package xyz.wochib70.exam.domain;

import xyz.wochib70.exam.common.BaseEnum;

import java.util.Optional;

public enum ShelvesStatus implements BaseEnum {
    SHELVES_ON("1", "已上架"),
    SHELVES_OFF("0", "未上架");;

    private final String code;

    private final String desc;

    ShelvesStatus(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDescription() {
        return desc;
    }

    public static Optional<ShelvesStatus> of(String code) {
        return BaseEnum.of(code, ShelvesStatus.class);
    }

}
