package ru.fsep.enterprise.fseper.service.jdbc.utils;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import ru.fsep.enterprise.fseper.models.User;
import ru.fsep.enterprise.fseper.service.dao.UsersDaoImpl;

import java.util.List;

public class SqlQueryExecutorImplTest {

    //language=SQL
    public static final String SQL_GET_ALL_USERS = "SELECT * FROM users";

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
        List<User> actual = sqlQueryExecutor.queryForObjects(SQL_GET_ALL_USERS, UsersDaoImpl.USER_ROW_MAPPER);
        System.out.println(actual);
    }

    @Test
    public void testQueryForObjectsWithParamMap() throws Exception {
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

//    @After
//    public void closeDatabase() {
//        database.shutdown();
//    }
}