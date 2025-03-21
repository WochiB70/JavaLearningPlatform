package xyz.wochib70.mybatis.advanced.typehandler.entity;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class JsonField {

    private String name;

    private Integer code;
}
