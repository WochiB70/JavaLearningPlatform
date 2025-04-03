package xyz.wochib70.jimmer.integration.springboot.test;

import jakarta.annotation.Resource;
import org.babyfish.jimmer.sql.JSqlClient;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.wochib70.jimmer.integration.springboot.dto.BookViewAndAuthorIds;
import xyz.wochib70.jimmer.integration.springboot.entity.Book;
import xyz.wochib70.jimmer.integration.springboot.entity.BookFetcher;
import xyz.wochib70.jimmer.integration.springboot.entity.BookTable;

import java.util.List;

@SpringBootTest
public class AssociationIdExampleTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(AssociationIdExampleTest.class);

    @Resource
    private JSqlClient sqlClient;


    @Test
    public void nonStaticDtoQueryAssociationIdsTest() {
        BookTable table = BookTable.$;

        List<Book> books = sqlClient
                .createQuery(table)
                .where(table.name().eq("Learning GraphQL"))
                .orderBy(table.edition().desc())
                .select(
                        table.fetch(
                                BookFetcher.$
                                        .allScalarFields()
                                        .authors()
                        )
                )
                .execute();

        books.forEach(book -> LOGGER.info("book:{}", book));
    }

    @Test
    public void nonStaticDtoQueryAssociationIdsByIdsFieldTest() {
        BookTable table = BookTable.$;

        List<Book> books = sqlClient
                .createQuery(table)
                .where(table.name().eq("Learning GraphQL"))
                .orderBy(table.edition().desc())
                .select(
                        table.fetch(
                                BookFetcher.$
                                        .allScalarFields()
                                        .authorIds()
                        )
                )
                .execute();

        books.forEach(book -> LOGGER.info("book:{}", book));
    }


    @Test
    public void staticDtoQueryAssociationIdsTest() {
        BookTable table = BookTable.$;
        List<BookViewAndAuthorIds> books = sqlClient.createQuery(table)
                .where(table.name().eq("Learning GraphQL"))
                .orderBy(table.edition().desc())
                .select(table.fetch(BookViewAndAuthorIds.class))
                .execute();

        books.forEach(book -> LOGGER.info("book:{}", book));
    }
}
