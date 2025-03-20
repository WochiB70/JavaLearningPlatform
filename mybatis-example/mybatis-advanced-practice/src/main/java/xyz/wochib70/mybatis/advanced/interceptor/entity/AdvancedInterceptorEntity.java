package xyz.wochib70.mybatis.advanced.interceptor.entity;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdvancedInterceptorEntity {

    private Long id;

    private String name;

    private String description;
}
