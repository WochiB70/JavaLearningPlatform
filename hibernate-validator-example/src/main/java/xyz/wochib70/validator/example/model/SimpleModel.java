package xyz.wochib70.validator.example.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import xyz.wochib70.validator.example.custom.Custom;

@Data
public class SimpleModel {

    @Custom(message = "字符串必须包含'custom'")
    private String name;

    @NotBlank()
    private String desc;
}
