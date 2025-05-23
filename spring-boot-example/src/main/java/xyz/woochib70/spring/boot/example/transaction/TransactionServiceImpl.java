package xyz.woochib70.spring.boot.example.transaction;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.woochib70.spring.boot.example.transaction.entity.TransactionEntity;
import xyz.woochib70.spring.boot.example.transaction.mapper.TransactionMapper;

import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
public class TransactionServiceImpl {


    @Resource
    private TransactionMapper transactionMapper;


    @Transactional
    public void batchInsert(List<TransactionEntity> transactions, Random random) {
        for (TransactionEntity transaction : transactions) {
            transactionMapper.insert(transaction);
        }
        if (Objects.nonNull(random) && random.nextBoolean()) {
            throw new RuntimeException("rollback in transactional annotation");
        }

    }
}
