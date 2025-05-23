package xyz.woochib70.spring.boot.example.transaction.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import xyz.woochib70.spring.boot.example.transaction.entity.TransactionEntity;

@Mapper
public interface TransactionMapper {

    @Insert("""
            INSERT INTO transaction
            VALUES (#{transaction.id},#{transaction.name},#{transaction.description})
            """)
    void insert(@Param("transaction") TransactionEntity transaction);
}
