package xyz.wochib70.jimmer.integration.springboot;


import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.babyfish.jimmer.sql.JSqlClient;
import org.babyfish.jimmer.sql.ast.tuple.Tuple3;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import xyz.wochib70.jimmer.integration.springboot.entity.BookTable;

import java.util.List;

@SpringBootApplication
public class JimmerApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(JimmerApplication.class, args);
    }

    @Resource
    private JSqlClient sqlClient;



    @Override
    public void run(String... args) throws Exception {
        BookTable table = BookTable.$;
        List<Tuple3<Long, String, Integer>> tuples = sqlClient
                .createQuery(table)
                .where(table.name().eq("GraphQL in Action"))
                .orderBy(table.edition().desc())
                .select(
                        table.id(),
                        table.name(),
                        table.edition()
                )
                .execute();

        System.out.printf("tuples: %s%n", tuples.toString());
    }
}
