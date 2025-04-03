package xyz.wochib70.jimmer.integration.springboot.test;


import jakarta.annotation.Resource;
import org.babyfish.jimmer.sql.JSqlClient;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.wochib70.jimmer.integration.springboot.entity.*;

import java.util.List;

@SpringBootTest
public class DeepAssociationExampleTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(DeepAssociationExampleTest.class);

    @Resource
    private JSqlClient sqlClient;


    @Test
    public void dynamicEntityTest() {
        BookStoreTable table = BookStoreTable.$;

        List<BookStore> stores = sqlClient
                .createQuery(table)
                .where(table.name().eq("MANNING"))
                .select(
                        table.fetch(
                                BookStoreFetcher.$
                                        .allScalarFields()
                                        .books(
                                                BookFetcher.$
                                                        .allScalarFields()
                                                        .authors(
                                                                AuthorFetcher.$
                                                                        .allScalarFields()
                                                        )
                                        )
                        )
                )
                .execute();
        stores.forEach(store -> LOGGER.info("store: {}", store));
    }
}
