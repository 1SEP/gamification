package ru.fsep.enterprise.fseper.service.dao.service.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import ru.fsep.enterprise.fseper.service.dao.models.Post;
import ru.fsep.enterprise.fseper.service.dao.models.User;
import ru.fsep.enterprise.fseper.service.dao.service.jdbc.utils.DaoArgumentsVerifier;
import ru.fsep.enterprise.fseper.service.dao.service.jdbc.utils.ParamsMapper;
import ru.fsep.enterprise.fseper.service.dao.service.jdbc.utils.SqlQueryExecutor;

import java.util.*;

import static java.util.Arrays.asList;
import static java.util.Collections.sort;

public class UsersDaoImpl implements UsersDao {

    private SqlQueryExecutor sqlQueryExecutor;
    private ParamsMapper paramsMapper;
    private DaoArgumentsVerifier verifier;


    public UsersDaoImpl(SqlQueryExecutor sqlQueryExecutor, ParamsMapper paramsMapper, DaoArgumentsVerifier verifier) {
        this.sqlQueryExecutor = sqlQueryExecutor;
        this.paramsMapper = paramsMapper;
        this.verifier = verifier;
    }

    //language=SQL
    public static final String SQL_GET_USER_BY_ID = "SELECT * FROM users WHERE  id = :userId;";
    //language=SQL
    public static final String SQL_DELETE_USER_BY_ID = "DELETE FROM users WHERE id = :userId;";
    //language - SQL
    public static final String SQL_UPDATE_USER = ";";//incomplete
    //language - SQL
    public static final String SQL_GET_ALL_USERS = "SELECT * FROM users ;";
    //language - SQL
    public static final String SQL_GET_ALL_SORTED_USERS = "SELECT * FROM users ORDER BY first_name, last_name;";
    //language - SQL
    public static final String SQL_GET_ALL_USERS_BY_NAME = "SELECT * FROM users WHERE (first_name = : firstName) " +
            "AND (last_name = :lastname) ORDER BY first_name, last_name";
    //language - SQL
    public static final String SQL_INSERT_USER = "INSERT INTO users(id) VALUES (:id);";
    //language - SQL
    public static final String SQL_GET_ALL_SORTED_USERS_BY_RATING = "SELECT * FROM users ORDER BY rating";
    //language - SQL
    public static final String SQL_GEL_USERS_BY_POST = "SELECT * FROM users ORDER BY post";//incomplete
    //language - SQL
    public static final String SQL_GET_USERS_BY_POST = "SELECT * FROM users where (post_id = :post.id);";

    public static final RowMapper<User> USER_ROW_MAPPER = new BeanPropertyRowMapper<User>(User.class);

    public void logIn(User user) {
        verifier.verifyUser(user.getId());
        Map<String, Object> paramMap = paramsMapper.asMap(asList("author_data"), asList(user.getAuthData()));
        sqlQueryExecutor.updateQuery(SQL_INSERT_USER, paramMap);
    }

    public User getUser(int userId) {
        verifier.verifyUser(userId);
        Map<String, Object> paramMap = paramsMapper.asMap(asList("userId"), asList(userId));
        User user = sqlQueryExecutor.queryForObject(SQL_GET_USER_BY_ID, paramMap, USER_ROW_MAPPER );
        return user;
    }

    public User updateUser(User user) {
        //verifier.verifyUser(user.getId());
        Map<String, Object> paramMap = paramsMapper.asMap(asList("id", "person_info", "author_data", "tasks"),
                asList(user.getId(), user.getPersonInfo(), user.getAuthData(), user.getTasks()));
        sqlQueryExecutor.updateQuery(SQL_UPDATE_USER, paramMap);
        return user;
    }

    public void removeUser(int userId) {
        verifier.verifyUser(userId);
        Map<String, Object> paramMap = paramsMapper.asMap(asList("id"), asList(userId));
        sqlQueryExecutor.updateQuery(SQL_DELETE_USER_BY_ID, paramMap);
    }

    public List<User> getUsers() {
        List<User> result = sqlQueryExecutor.queryForObjects(SQL_GET_ALL_USERS, new BeanPropertyRowMapper<User>(User.class));
        return result;
    }

    public List<User> getUsersByName(String firstName, String lastName) {
        Map<String, Object> paramMap = paramsMapper.asMap(asList("first_name", "last_name"),
                asList(firstName, lastName));
        List<User> users = sqlQueryExecutor.queryForObjects(SQL_GET_ALL_USERS_BY_NAME, paramMap,
                new BeanPropertyRowMapper<User>(User.class));
        return users;
    }

    public List<User> getUsersByPost(Post post) {
        Map<String, Object> paramMap = paramsMapper.asMap(asList("post_id"), asList(post.getId()));
        List<User> users = sqlQueryExecutor.queryForObjects(SQL_GEL_USERS_BY_POST, paramMap, new BeanPropertyRowMapper<User>(User.class));
        return users;
    }

    public List<User> getSortedUsers() {
        List<User> users = sqlQueryExecutor.queryForObjects(SQL_GET_ALL_SORTED_USERS, new BeanPropertyRowMapper<User>(User.class));
        return users;
    }

    public List<User> getSortedUsersByRating() {
        List<User> users = sqlQueryExecutor.queryForObjects(SQL_GET_ALL_SORTED_USERS_BY_RATING,
                new BeanPropertyRowMapper<User>(User.class));
        return users;
    }
}
