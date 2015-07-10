package ru.fsep.enterprise.fseper.service.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import ru.fsep.enterprise.fseper.models.*;
import ru.fsep.enterprise.fseper.service.jdbc.utils.DaoArgumentsVerifier;
import ru.fsep.enterprise.fseper.service.jdbc.utils.ParamsMapper;
import ru.fsep.enterprise.fseper.service.jdbc.utils.SqlQueryExecutor;

import java.net.MalformedURLException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

public class UsersDaoImpl implements UsersDao {
    private SqlQueryExecutor sqlQueryExecutor;
    private ParamsMapper paramsMapper;
    private DaoArgumentsVerifier verifier;

    public static final String SQL_GET_USER_BY_ID = "SELECT * FROM users WHERE  id = :userId";
    public static final String SQL_DELETE_USER_BY_ID = "DELETE FROM users WHERE id = :userId";
    public static final String SQL_UPDATE_USER = "";
    public static final String SQL_GET_ALL_USERS = "SELECT * FROM users";

    public void logIn(User user) {
    }

    public User getUser(int userId) {
        verifier.verifyUser(userId);
        Map<String, Object> paramMap = paramsMapper.asMap(asList("userId"), asList(userId));
        User user = (User) sqlQueryExecutor.queryForObjects(SQL_GET_USER_BY_ID, paramMap, new BeanPropertyRowMapper<User>(User.class));
        return user;
    }

    public User updateUser(User user) {
        Map<String, Object> paramMap = paramsMapper.asMap(asList("id", "person_info", "author_data", "tasks"),
                asList(user.getId(), user.getPersonInfo(), user.getAuthData(), user.getTasks()));
        sqlQueryExecutor.updateQuery(SQL_UPDATE_USER, paramMap);
        return user;
    }


    public void removeUser(int userId) {
        Map<String, Object> paramMap = paramsMapper.asMap(asList("id"), asList(userId));
        sqlQueryExecutor.updateQuery(SQL_DELETE_USER_BY_ID, paramMap);
    }

    public List<User> getUsers() {
        List<User> result = sqlQueryExecutor.queryForObjects(SQL_GET_ALL_USERS, new BeanPropertyRowMapper<User>(User.class));
        return result;
    }

    public List<User> getUsersByName(String firstName, String lastName, String patronymic) {
        return null;
    }

    public Post getUsersByPost() {
        return null;
    }

    public List<User> getSortedUsers() {
        return null;
    }

    public List<User> getSortedUsersByRating() {
        return null;
    }
}
