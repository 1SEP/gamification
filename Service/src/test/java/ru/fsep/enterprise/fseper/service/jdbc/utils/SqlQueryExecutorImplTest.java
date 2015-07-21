package ru.fsep.enterprise.fseper.service.jdbc.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

public class SqlQueryExecutorImplTest {

    EmbeddedDatabase database;
    SqlQueryExecutorImpl sqlQueryExecutor;

    private void databaseInitialize() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        database = builder.setType(EmbeddedDatabaseType.HSQL)
                .addScript("file:src/test/java/ru/fsep/enterprise/fseper/service/jdbc/utils/schema.sql")
                .addScript("file:src/test/java/ru/fsep/enterprise/fseper/service/jdbc/utils/test-data.sql").build();
    }

    @Before
    public void setUp() throws Exception {
        databaseInitialize();
        sqlQueryExecutor = new SqlQueryExecutorImpl(database);
    }

    @Test
    public void testQueryForObjects() throws Exception {

    }

    @Test
    public void testQueryForObjects1() throws Exception {

    }

    @Test
    public void testQueryForObject() throws Exception {

    }

    @Test
    public void testUpdateQuery() throws Exception {

    }

    @Test
    public void testQueryForInt() throws Exception {

    }

    @Test
    public void testQueryForInt1() throws Exception {

    }

    @After
    public void closeDatabase() {
        database.shutdown();
    }
}