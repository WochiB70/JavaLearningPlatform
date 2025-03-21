package xyz.wochib70.mybatis.advanced.typehandler.entity;


import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class TypeHandlerEntity {

    private Long id;

    private String name;

    private JsonField jsonField;


}
