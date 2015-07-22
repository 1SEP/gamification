package ru.fsep.enterprise.fseper.service.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import ru.fsep.enterprise.fseper.models.*;
import ru.fsep.enterprise.fseper.service.jdbc.utils.DaoArgumentsVerifier;
import ru.fsep.enterprise.fseper.service.jdbc.utils.ParamsMapper;
import ru.fsep.enterprise.fseper.service.jdbc.utils.SqlQueryExecutor;

import java.net.URL;
import java.util.*;
import static java.util.Arrays.asList;

public class UsersDaoImpl implements UsersDao {

    private SqlQueryExecutor sqlQueryExecutor;
    private ParamsMapper paramsMapper;
    private DaoArgumentsVerifier daoArgumentsVerifier;

    public static final RowMapper<User> USER_ROW_MAPPER = new BeanPropertyRowMapper<User>(User.class);

    public UsersDaoImpl(SqlQueryExecutor sqlQueryExecutor, ParamsMapper paramsMapper, DaoArgumentsVerifier verifier) {
        this.sqlQueryExecutor = sqlQueryExecutor;
        this.paramsMapper = paramsMapper;
        this.daoArgumentsVerifier = verifier;
    }

    //language=SQL
    public static final String SQL_GET_USER_BY_ID = "SELECT * FROM users WHERE  id = :userId;";
    //language=SQL
    public static final String SQL_DELETE_USER_BY_ID = "DELETE FROM users WHERE id = :userId;";
    //language=SQL
    public static final String SQL_UPDATE_USER = "UPDATE users SET first_name = :firstName, last_name = :firstName, " +
            "birthday = :birthday, rating = :rating, photo = :photo, role = :role, login = :login, password_hash = :password," +
            " WHERE id = :userId;";
    //language=SQL
    public static final String SQL_GET_ALL_USERS = "SELECT * FROM users;";
    //language=SQL
    public static final String SQL_GET_ALL_SORTED_USERS_BY_NAME = "SELECT * FROM users ORDER BY first_name, last_name;";
    //language=SQL
    public static final String SQL_GET_ALL_USERS_BY_NAME = "SELECT * FROM users WHERE (first_name = : firstName) " +
            "AND (last_name = :lastName) ORDER BY first_name, last_name;";
    //language=SQL
    public static final String SQL_INSERT_USER = "INSERT INTO users(id, first_name, last_name, birthday, rating, " +
            "photo, role, login, password_hash) " +
            "VALUES (:userId, :firstName, :lastName, :birthday, :rating, :photo, :role, :login, :password);";
    //language=SQL
    public static final String SQL_GET_ALL_SORTED_USERS_BY_RATING = "SELECT * FROM users ORDER BY rating;";
    //language=SQL
    public static final String SQL_GET_USERS_BY_POST = "SELECT * FROM users where (post.id = :postId) " +
            "AND (post.name = :postName) AND (post.description = :postDescription);";

    public void logIn(User user) {
        daoArgumentsVerifier.verifyUser(user);
        int userId = user.getId();

        PersonInfo personInfo = user.getPersonInfo();
        String firstName = personInfo.getFirstName();
        String lastName = personInfo.getLastName();
        String birthday = personInfo.getBirthday();
        double rating = personInfo.getRating();
        URL photo = personInfo.getPhoto();
        String role = personInfo.getRole();

        AuthData authData = user.getAuthData();
        String login = authData.getLogin();
        String passwordHash = authData.getPasswordHash();

        Map<String, Object> paramMap = paramsMapper.asMap(asList("userId", "firstName", "lastName", "birthday",
                        "rating", "photo", "role", "login", "password"),
                asList(userId, firstName, lastName, birthday, rating, photo, role, login, passwordHash));
        sqlQueryExecutor.updateQuery(SQL_INSERT_USER, paramMap);
    }

    public User getUser(int userId) {
        daoArgumentsVerifier.verifyUserById(userId);
        Map<String, Object> paramMap = paramsMapper.asMap(asList("userId"), asList(userId));
        User user = sqlQueryExecutor.queryForObject(SQL_GET_USER_BY_ID, paramMap, USER_ROW_MAPPER );
        return user;
    }

    public User updateUser(User user) {
        daoArgumentsVerifier.verifyUser(user);
        int userId = user.getId();

        PersonInfo personInfo = user.getPersonInfo();
        String firstName = personInfo.getFirstName();
        String lastName = personInfo.getLastName();
        String birthday = personInfo.getBirthday();
        double rating = personInfo.getRating();
        URL photo = personInfo.getPhoto();
        String role = personInfo.getRole();

        AuthData authData = user.getAuthData();
        String login = authData.getLogin();
        String passwordHash = authData.getPasswordHash();

        Map<String, Object> paramMap = paramsMapper.asMap(asList("userId", "firstName", "lastName", "birthday",
                        "rating", "photo", "role", "login", "password"),
                asList(userId, firstName, lastName, birthday, rating, photo, role, login, passwordHash));
        sqlQueryExecutor.updateQuery(SQL_UPDATE_USER, paramMap);
        return user;
    }

    public void removeUser(int userId) {
        daoArgumentsVerifier.verifyUserById(userId);
        Map<String, Object> paramMap = paramsMapper.asMap(asList("userId"), asList(userId));
        sqlQueryExecutor.updateQuery(SQL_DELETE_USER_BY_ID, paramMap);
    }

    public List<User> getUsers() {
        List<User> result = sqlQueryExecutor.queryForObjects(SQL_GET_ALL_USERS, USER_ROW_MAPPER);
        return result;
    }

    public List<User> getUsersByName(String firstName, String lastName) {
        daoArgumentsVerifier.verifyUserByName(firstName, lastName);
        Map<String, Object> paramMap = paramsMapper.asMap(asList("firstName", "lastName"),
                asList(firstName, lastName));
        List<User> users = sqlQueryExecutor.queryForObjects(SQL_GET_ALL_USERS_BY_NAME, paramMap,
                USER_ROW_MAPPER);
        return users;

    }

    public List<User> getUsersByPost(Post post) {
        daoArgumentsVerifier.verifyPost(post);
        Map<String, Object> paramMap = paramsMapper.asMap(asList("postId", "postName", "postDescription"),
                asList(post.getId(), post.getName(), post.getDescription()));
        List<User> users = sqlQueryExecutor.queryForObjects(SQL_GET_USERS_BY_POST, paramMap,
                USER_ROW_MAPPER);
        return users;
    }

    public List<User> getSortedUsersByName() {
        List<User> users = sqlQueryExecutor.queryForObjects(SQL_GET_ALL_SORTED_USERS_BY_NAME, USER_ROW_MAPPER);
        return users;
    }

    public List<User> getSortedUsersByRating() {
        List<User> users = sqlQueryExecutor.queryForObjects(SQL_GET_ALL_SORTED_USERS_BY_RATING,
                USER_ROW_MAPPER);
        return users;
    }
}