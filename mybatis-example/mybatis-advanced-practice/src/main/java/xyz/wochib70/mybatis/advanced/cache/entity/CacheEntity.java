package xyz.wochib70.mybatis.advanced.cache.entity;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CacheEntity {


    private Long id;

    private String name;

    private String description;
}
