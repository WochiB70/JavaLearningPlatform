package xyz.wochib70.validator.example.enums;

import lombok.Data;

@Data
public class EnumModel {

    @EnumValidator(enumClass = OneEnum.class, message = "Invalid enum value OneEnum only accepts 1 or 2")
    private Integer one;


    @EnumValidator(enumClass = TwoEnum.class, message = "Invalid enum value TwoEnum only accepts 11 or 12")
    private Integer two;
}
