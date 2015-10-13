package ru.fsep.enterprise.fseper.service.jdbc.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import ru.fsep.enterprise.fseper.models.*;
import ru.fsep.enterprise.fseper.service.dao.PostsDaoImpl;
import ru.fsep.enterprise.fseper.service.dao.TasksDaoImpl;
import ru.fsep.enterprise.fseper.service.dao.UsersDao;
import ru.fsep.enterprise.fseper.service.dao.UsersDaoImpl;
import ru.fsep.enterprise.fseper.test.data.TestDataForTaskDao;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static ru.fsep.enterprise.fseper.service.dao.PostsDaoImpl.POST_ROW_MAPPER;
import static ru.fsep.enterprise.fseper.service.dao.TasksDaoImpl.TASK_ROW_MAPPER;
import static ru.fsep.enterprise.fseper.service.dao.UsersDaoImpl.*;
import static ru.fsep.enterprise.fseper.test.data.TestDataForTaskDao.TASK_LIST;
import static ru.fsep.enterprise.fseper.test.data.TestDataForUserDao.*;

public class SqlQueryExecutorImplTest {

    EmbeddedDatabase databaseTest;
    SqlQueryExecutor sqlQueryExecutorTest;
    ParamsMapper paramsMapperTest;

    private void databaseInitialize() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        databaseTest = builder.setType(EmbeddedDatabaseType.HSQL)
                .addScript("file:src/test/java/ru/fsep/enterprise/fseper/service/jdbc/utils/schema.sql")
                .addScript("file:src/test/java/ru/fsep/enterprise/fseper/service/jdbc/utils/test-data.sql").build();
    }

    @Before
    public void setUp() throws Exception {
        databaseInitialize();
        paramsMapperTest = new ParamsMapperJDBCImpl();
        sqlQueryExecutorTest = new SqlQueryExecutorImpl(databaseTest);
    }

    @Test
    public void testQueryForObjects() throws Exception {
        List<User> actual = sqlQueryExecutorTest.queryForObjects(SQL_GET_ALL_USERS, USER_ROW_MAPPER);
        assertNotNull("The ref must not be null: ", actual);
    }

    @Test
    public void testQueryForObjectsWithParamMap() throws Exception {
        Map<String, Object> paramMap = paramsMapperTest.asMap(asList("firstName", "lastName"),
                asList("Ildar", "Almakayev"));
        List<User> actual = sqlQueryExecutorTest.queryForObjects(SQL_GET_ALL_USERS_BY_NAME, paramMap,
                USER_ROW_MAPPER);
        assertNotNull("The ref must not be null: ", actual);
    }

    @Test
    public void testQueryForObject() throws Exception {
        Map<String, Object> paramMap = paramsMapperTest.asMap(asList("userId"), asList(USER_ID));
        User actual = sqlQueryExecutorTest.queryForObject(SQL_GET_USER_BY_ID, paramMap, USER_ROW_MAPPER);
        assertNotNull("The ref must not be null: ", actual);
    }

    @Test
    public void testUpdateQueryForUpdateUser() throws Exception {
        String firstName = "Ivan";
        String lastName = "Ivanov";
        String birthday = "2000-12-12";
        double rating = 100.0;
        URL photo = new URL("http://cs627828.vk.me/v627828952/1121a/dVYbT2kT7ps.jpg");
        String role = "cpp-dev";
        List<Post> postsList = Collections.emptyList();
        Posts posts = new Posts(postsList);
        PersonInfo personInfo = new PersonInfo(firstName, lastName, rating, birthday, posts, role, photo);
        String login = "ivanovLogin";
        String passwordHash = "Ivanov_password_hash";
        AuthData authData = new AuthData(passwordHash, login);
        int userId = USER.getId();

        Map<String, Object> paramMap = paramsMapperTest.asMap(asList("userId", "firstName", "lastName", "birthday", "rating", "photo", "role", "login", "password"),
                asList(userId, firstName, lastName, birthday, rating, photo.toString(), role, login, passwordHash));

        sqlQueryExecutorTest.updateQuery(SQL_UPDATE_USER, paramMap);

        Map<String, Object> paramMapForUserId = paramsMapperTest.asMap(asList("userId"), asList(userId));
        User actual = sqlQueryExecutorTest.queryForObject(SQL_GET_USER_BY_ID, paramMapForUserId, USER_ROW_MAPPER);
        User expected = new User(0, authData, personInfo, new Tasks(Collections.EMPTY_LIST));
        assertEquals(expected.getAuthData(), actual.getAuthData());
    }


    @Test
    public void testUpdateUserQueryForRemoveUser() throws Exception {
        int userId = USER_ID;
        Map<String, Object> paramMap = paramsMapperTest.asMap(asList("userId"), asList(userId));
        sqlQueryExecutorTest.updateQuery(SQL_DELETE_USER_BY_ID, paramMap);
        int actual = sqlQueryExecutorTest.queryForInt("SELECT COUNT(*) FROM users", paramMap);
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    public void testUpdateQueryForInsertUser() throws Exception {
        String firstName = "Misha";
        String lastName = "Mishkin";
        String birthday = "2001-12-12";
        double rating = 99.0;
        URL photo = new URL("http://cs627828.vk.me/v627828952/1121a/dVYbT2kT7ps.jpg");
        String role = "ruby-dev";
        List<Post> postsList = Collections.emptyList();
        Posts posts = new Posts(postsList);
        PersonInfo personInfo = new PersonInfo(firstName, lastName, rating, birthday, posts, role, photo);
        String login = "mishaLogin";
        String passwordHash = "Misha_password_hash";
        AuthData authData = new AuthData(login, passwordHash);

        Map<String, Object> paramMap = paramsMapperTest.asMap(asList("firstName", "lastName", "birthday", "rating",
                        "photo", "role", "login", "password"),
                asList(firstName, lastName, birthday, rating, photo.toString(), role, login, passwordHash));

        sqlQueryExecutorTest.updateQuery(SQL_INSERT_USER, paramMap);

        User expected = new User(1, authData, personInfo, null);
        int expectedUserId = expected.getId();
        Map<String, Object> paramMapForInsertedUser = paramsMapperTest.asMap(asList("userId"), asList(expectedUserId));
        User actual = sqlQueryExecutorTest.queryForObject(SQL_GET_USER_BY_ID, paramMapForInsertedUser, USER_ROW_MAPPER);

        assertNotNull("The ref must not be null: ", actual);
    }

    @Test
    public void testQueryForIntWithNamedJdbcTemplate() throws Exception {
        String firstName = USER.getPersonInfo().getFirstName();
        Map<String, Object> paramMap = paramsMapperTest.asMap(asList("firstName"), asList(firstName));
        int actual = sqlQueryExecutorTest.queryForInt("select count(*) from users where first_name =:firstName", paramMap);
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void testQueryForIntWithJdbcTemplate() throws Exception {
        int actual = sqlQueryExecutorTest.queryForInt("SELECT COUNT(*) FROM users");
        assertTrue("The count of users: ", actual >= 0);
    }

    @After
    public void closeDatabase() {
        databaseTest.shutdown();
    }
}