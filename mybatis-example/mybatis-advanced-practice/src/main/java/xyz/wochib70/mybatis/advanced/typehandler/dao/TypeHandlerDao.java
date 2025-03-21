package xyz.wochib70.mybatis.advanced.typehandler.dao;

import xyz.wochib70.mybatis.advanced.typehandler.entity.TypeHandlerEntity;

public interface TypeHandlerDao {


    void insert(TypeHandlerEntity entity);

    TypeHandlerEntity selectById(Long id);
}
