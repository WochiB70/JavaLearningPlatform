package xyz.wochib70.mybatis.advanced.interceptor.dao;


import org.apache.ibatis.annotations.Mapper;
import xyz.wochib70.mybatis.advanced.interceptor.entity.AdvancedInterceptorEntity;

@Mapper
public interface AdvancedInterceptorDao {


    public void insert(AdvancedInterceptorEntity entity);


    public AdvancedInterceptorEntity findById(Long id);
}
