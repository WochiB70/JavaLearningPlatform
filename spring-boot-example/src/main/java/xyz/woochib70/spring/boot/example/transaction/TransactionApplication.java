package xyz.woochib70.spring.boot.example.transaction;


import jakarta.annotation.Resource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;
import xyz.woochib70.spring.boot.example.transaction.entity.TransactionEntity;
import xyz.woochib70.spring.boot.example.transaction.mapper.TransactionMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

@SpringBootApplication
@MapperScan("xyz.woochib70.spring.boot.example.transaction.mapper")
@EnableTransactionManagement
public class TransactionApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(TransactionApplication.class, args);
    }

    @Resource
    private TransactionTemplate transactionTemplate;

    @Resource
    private TransactionServiceImpl transactionService;

    @Resource
    private PlatformTransactionManager platformTransactionManager;

    @Resource
    private TransactionMapper transactionMapper;

    @Override
    public void run(String... args) throws Exception {
        CountDownLatch count = new CountDownLatch(3);

        Random random = new Random();
        var list = new ArrayList<TransactionEntity>();
        for (int i = 0; i < 10; i++) {
            TransactionEntity entity = new TransactionEntity();
            entity.setId(random.nextLong(500L));
            entity.setName("annotation-name:" + random.nextLong());
            entity.setDescription("annotation-description:" + random.nextLong());
            list.add(entity);
        }

        Runnable transactionalRunnable = () -> {
            try {
                transactionService.batchInsert(list, random);
            } finally {
                count.countDown();
            }
        };

        Runnable transactionRunnable = () -> {
            transactionTemplate.execute(action -> {
                try {
                    for (int i = 0; i < 10; i++) {
                        TransactionEntity entity = new TransactionEntity();
                        entity.setId(random.nextLong(500L));
                        entity.setName("template-name:" + random.nextLong());
                        entity.setDescription("template-description:" + random.nextLong());
                        transactionMapper.insert(entity);
                    }
                    if (random.nextBoolean()) {
                        throw new RuntimeException("rollback in template");
                    }
                    return 1;
                } catch (Exception e) {
                    action.setRollbackOnly();
                    e.printStackTrace();
                }
                return -1;
            });
            count.countDown();
        };

        Runnable platformTransactionRunnable = () -> {
            TransactionStatus transaction = platformTransactionManager.getTransaction(new DefaultTransactionDefinition());
            try {
                for (int i = 0; i < 10; i++) {
                    TransactionEntity entity = new TransactionEntity();
                    entity.setId(random.nextLong(500L));
                    entity.setName("platform-name:" + random.nextLong());
                    entity.setDescription("platform-description:" + random.nextLong());
                    transactionMapper.insert(entity);
                }
                if (random.nextBoolean()) {
                    throw new RuntimeException("rollback in platform");
                }
                platformTransactionManager.commit(transaction);
            } catch (Exception e) {
                transaction.setRollbackOnly();
                platformTransactionManager.rollback(transaction);
                e.printStackTrace();
            }
            count.countDown();
        };


        new Thread(transactionalRunnable).start();
        new Thread(transactionRunnable).start();
        new Thread(platformTransactionRunnable).start();

        count.await();
        System.out.println("success");
    }
}
