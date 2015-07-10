package ru.fsep.enterprise.fseper.service.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import ru.fsep.enterprise.fseper.models.*;
import ru.fsep.enterprise.fseper.service.jdbc.utils.DaoArgumentsVerifier;
import ru.fsep.enterprise.fseper.service.jdbc.utils.ParamsMapper;
import ru.fsep.enterprise.fseper.service.jdbc.utils.SqlQueryExecutor;

import java.util.*;

import static java.util.Arrays.asList;
import static java.util.Collections.sort;

public class UsersDaoImpl implements UsersDao {

    private SqlQueryExecutor sqlQueryExecutor;
    private ParamsMapper paramsMapper;
    private DaoArgumentsVerifier verifier;

    //SQL queries
    public static final String SQL_GET_USER_BY_ID = "SELECT * FROM users WHERE  id = :userId;";
    public static final String SQL_DELETE_USER_BY_ID = "DELETE FROM users WHERE id = :userId;";
    public static final String SQL_UPDATE_USER = ";";//incomplete
    public static final String SQL_GET_ALL_USERS = "SELECT * FROM users;";
    public static final String SQL_GET_ALL_USERS_BY_NAME = "SELECT * FROM users WHERE (first_name = : firstName) " +
            "AND (last_name = :lastname) AND (patronymic = :patronymic);";
    public static final String SQL_INSERT_USER = "INSERT INTO users(id) VALUES (:id);";
    public static final String SQL_GET_ALL_SORTED_USERS_BY_RATING = "SELECT * FROM users";//incomplete
    public static final String SQL_GEL_USERS_BY_POST = "SELECT * FROM users WHERE post";//incomplete

    public void logIn(User user) {
        verifier.verifyUser(user.getId());
        Map<String, Object> paramMap = paramsMapper.asMap(asList("author_data"), asList(user.getAuthData()));
        sqlQueryExecutor.updateQuery(SQL_INSERT_USER, paramMap);
    }

    public User getUser(int userId) {
        verifier.verifyUser(userId);
        Map<String, Object> paramMap = paramsMapper.asMap(asList("userId"), asList(userId));
        User user = (User) sqlQueryExecutor.queryForObjects(SQL_GET_USER_BY_ID, paramMap, new BeanPropertyRowMapper<User>(User.class));
        return user;
    }

    public User updateUser(User user) {
        verifier.verifyUser(user.getId());
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

    public List<User> getUsersByName(String firstName, String lastName, String patronymic) {
        Map<String, Object> paramMap = paramsMapper.asMap(asList("first_name", "last_name", "patronymic"),
                asList(firstName, lastName, patronymic));
        List<User> users = sqlQueryExecutor.queryForObjects(SQL_GET_ALL_USERS_BY_NAME, paramMap,
                new BeanPropertyRowMapper<User>(User.class));
        return users;
    }

    public Post getUsersByPost() {
        return null;
    }

    public List<User> getSortedUsers() {
        List<User> users = sqlQueryExecutor.queryForObjects(SQL_GET_ALL_USERS, new BeanPropertyRowMapper<User>(User.class));
        return users;
    }

    public List<User> getSortedUsersByRating() {
        List<User> users = sqlQueryExecutor.queryForObjects(SQL_GET_ALL_SORTED_USERS_BY_RATING,
                new BeanPropertyRowMapper<User>(User.class));
        return users;
    }
}
