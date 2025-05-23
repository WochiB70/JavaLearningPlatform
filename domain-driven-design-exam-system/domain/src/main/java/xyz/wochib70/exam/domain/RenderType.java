package xyz.wochib70.exam.domain;

import xyz.wochib70.exam.common.BaseEnum;

public enum RenderType implements BaseEnum {

    SINGLE_ANSWER_MULTIPLE_CHOICE("", ""),

    MATERIAL_SINGLE_ANSWER_MULTIPLE_CHOICE("", ""),

    MULTIPLE_ANSWER_MULTIPLE_CHOICE("", ""),

    MATERIAL_MULTIPLE_ANSWER_MULTIPLE_CHOICE("", ""),

    ORDINARY_FILL_BLANK("", "");

    private final String code;

    private final String desc;

    RenderType(String code, String desc) {
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
}
