package xyz.wochib70.jimmer.integration.springboot.test;


import jakarta.annotation.Resource;
import org.babyfish.jimmer.sql.JSqlClient;
import org.babyfish.jimmer.sql.ast.Expression;
import org.babyfish.jimmer.sql.ast.tuple.Tuple2;
import org.babyfish.jimmer.sql.ast.tuple.Tuple3;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.wochib70.jimmer.integration.springboot.entity.Book;
import xyz.wochib70.jimmer.integration.springboot.entity.BookFetcher;
import xyz.wochib70.jimmer.integration.springboot.entity.BookTable;

import java.util.List;

@SpringBootTest
public class BasicExampleTest {

    private final static Logger LOGGER = org.slf4j.LoggerFactory.getLogger(BasicExampleTest.class);

    @Resource
    private JSqlClient sqlClient;

    @Test
    public void simpleQueryTest() {
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

        tuples.forEach(tuple -> {
            LOGGER.info("tuple: {}", tuple);
        });
    }

    @Test
    public void simpleQueryToBookTest() {
        BookTable table = BookTable.$;

        List<Book> books = sqlClient
                .createQuery(table)
                .where(table.name().eq("GraphQL in Action"))
                .orderBy(table.edition().desc())
                .select(
                        table.fetch(
                                BookFetcher.$
                                        .name()
                                        .edition()
                                        .price()
                        )
                )
                .execute();
        books.forEach(book -> {
            LOGGER.info("book: {}", book);
        });
    }

    @Test
    public void simpleQueryForNativeSqlTest() {
        BookTable table = BookTable.$;

        List<Tuple2<Book, Integer>> tuples = sqlClient
                .createQuery(table)
                .where(table.name().eq("GraphQL in Action"))
                .orderBy(table.edition().desc())
                .select(
                        table.fetch(
                                BookFetcher.$
                                        .allScalarFields()
                        ),
                        Expression.numeric().sql(
                                Integer.class,
                                "row_number() over(partition by %e order by %e desc)",
                                new Expression<?>[]{table.storeId(), table.price()}
                        )
                )
                .execute();

        tuples.forEach(tuple -> LOGGER.info("storeId {}, tuple: {}", tuple.get_1(), tuple.get_2()));
    }
}
