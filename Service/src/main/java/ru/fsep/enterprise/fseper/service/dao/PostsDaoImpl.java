package ru.fsep.enterprise.fseper.service.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.fsep.enterprise.fseper.models.Post;
import ru.fsep.enterprise.fseper.service.jdbc.utils.DaoArgumentsVerifier;
import ru.fsep.enterprise.fseper.service.jdbc.utils.ParamsMapper;
import ru.fsep.enterprise.fseper.service.jdbc.utils.SqlQueryExecutor;

import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
@Repository
public class PostsDaoImpl implements PostsDao {

    private SqlQueryExecutor sqlQueryExecutor;
    private ParamsMapper paramsMapper;
    private DaoArgumentsVerifier daoArgumentsVerifier;

    public static final RowMapper<Post> POST_ROW_MAPPER = new BeanPropertyRowMapper<Post>(Post.class);
    public PostsDaoImpl(){}
    public PostsDaoImpl(SqlQueryExecutor sqlQueryExecutor, ParamsMapper paramsMapper, DaoArgumentsVerifier verifier) {
        this.sqlQueryExecutor = sqlQueryExecutor;
        this.paramsMapper = paramsMapper;
        this.daoArgumentsVerifier = verifier;
    }

    //language=SQL
    static final String SQL_ADD_POST = "INSERT INTO post(id, name, description) " +
            "VALUES (:postId, :postName, :postDescription) WHERE users.id = :userId;";
    //language=SQL
    static final String SQL_REMOVE_POST = "DELETE FROM post WHERE (id = :postId);";
    //language=SQL
    static final String SQL_UPDATE_POST = "UPDATE post SET name = :name, description = :description WHERE id = :id;";
    //language=SQL
    static final String SQL_GET_POSTS = "SELECT * FROM post WHERE users.id =: userId;";

    public void addPost(Post post, int userId) {
        daoArgumentsVerifier.verifyPost(post);
        daoArgumentsVerifier.verifyUserById(userId);
        int postId = post.getId();
        String postName = post.getName();
        String postDescription = post.getDescription();
        Map<String, Object> paramMap = paramsMapper.asMap(asList("userId", "postId", "postName", "postDescription"),
                asList(userId, postId, postName, postDescription));
        sqlQueryExecutor.updateQuery(SQL_ADD_POST, paramMap);
    }

    public void removePost(int postId) {
        daoArgumentsVerifier.verifyPostById(postId);
        Map<String, Object> paramMap = paramsMapper.asMap(asList("id"), asList(postId));
        sqlQueryExecutor.updateQuery(SQL_REMOVE_POST, paramMap);
    }

    public Post updatePost(Post post) {
        daoArgumentsVerifier.verifyPost(post);
        Map<String, Object> paramMap = paramsMapper.asMap(asList("id", "name", "description"),
                asList(post.getId(), post.getName(), post.getDescription()));
        sqlQueryExecutor.updateQuery(SQL_UPDATE_POST, paramMap);
        return post;
    }

    public List<Post> getPosts(int userId) {
        daoArgumentsVerifier.verifyUserById(userId);
        Map<String, Object> paramMap = paramsMapper.asMap(asList("userId"), asList(userId));
        List<Post> posts = sqlQueryExecutor.queryForObjects(SQL_GET_POSTS, paramMap,
                POST_ROW_MAPPER);
        return posts;
    }
}

