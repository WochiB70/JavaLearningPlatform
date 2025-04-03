package xyz.wochib70.jimmer.integration.springboot.test;


import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.babyfish.jimmer.sql.JSqlClient;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.wochib70.jimmer.integration.springboot.dto.BookView;
import xyz.wochib70.jimmer.integration.springboot.entity.*;

import java.util.List;


@SpringBootTest
public class FetcherAssociationExampleTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(FetcherAssociationExampleTest.class);

    @Resource
    private JSqlClient sqlClient;


    @Test
    public void dynamicEntitiesQuery() {
        BookTable table = BookTable.$;

        List<Book> books = sqlClient
                .createQuery(table)
                .where(table.name().eq("Learning GraphQL"))
                .orderBy(table.edition().desc())
                .select(
                        table.fetch(
                                BookFetcher.$
                                        .allScalarFields()
                                        .authors(
                                                AuthorFetcher.$
                                                        .allScalarFields()
                                        )
                        )
                )
                .execute();

        for (Book book : books) {
            LOGGER.info("book: {}", book);
            for (Author author : book.authors()) {
                LOGGER.info("author: {}", author);
            }
        }
    }

    @Test
    public void staticDtoQuery(){
        BookTable table = BookTable.$;

        List<BookView> books = sqlClient
                .createQuery(table)
                .where(table.name().eq("Learning GraphQL"))
                .orderBy(table.edition().desc())
                .select(
                        table.fetch(BookView.class)
                )
                .execute();

        for (BookView book : books) {
            LOGGER.info("book: {}", book);
        }
    }

}
