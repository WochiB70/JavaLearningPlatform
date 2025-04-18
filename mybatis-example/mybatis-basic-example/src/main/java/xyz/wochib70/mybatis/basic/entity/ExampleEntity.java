package xyz.wochib70.mybatis.basic.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class ExampleEntity {

    private Long id;

    private String name;

    private String description;
}
