package ru.fsep.enterprise.fseper.service.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import ru.fsep.enterprise.fseper.models.Post;
import ru.fsep.enterprise.fseper.models.User;
import ru.fsep.enterprise.fseper.service.jdbc.utils.DaoArgumentsVerifier;
import ru.fsep.enterprise.fseper.service.jdbc.utils.ParamsMapper;
import ru.fsep.enterprise.fseper.service.jdbc.utils.SqlQueryExecutor;

import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

/**
 * Created by Rauf on 13.07.2015.
 */
public class PostsDaoImpl implements PostsDao {


    private SqlQueryExecutor sqlQueryExecutor;
    private ParamsMapper paramsMapper;
    private DaoArgumentsVerifier daoArgumentsVerifier;

    public static final RowMapper<User> USER_ROW_MAPPER = new BeanPropertyRowMapper<User>(User.class);

    public PostsDaoImpl(SqlQueryExecutor sqlQueryExecutor, ParamsMapper paramsMapper, DaoArgumentsVerifier verifier) {
        this.sqlQueryExecutor = sqlQueryExecutor;
        this.paramsMapper = paramsMapper;
        this.daoArgumentsVerifier = verifier;
    }

    static final String SQL_ADD_POST =
            "INSERT INTO post(id) VALUES (:id);";

    static final String SQL_REMOVE_POST =
            "DELETE FROM post WHERE (id = :postId);";

    static final String SQL_UPDATE_POST =
            "UPDATE post SET name = :name AND description = :description WHERE id = :id;";

    static final String SQL_GET_POSTS =
            "SELECT * FROM post WHERE id = :id;";

    public void addPost(Post post, int userId) {
        daoArgumentsVerifier.verifyPost(post.getId());
        Map<String, Object> paramMap = paramsMapper.asMap(asList("postId"), asList(post.getId()));
        sqlQueryExecutor.updateQuery(SQL_ADD_POST, paramMap);
    }

    public void removePost(int postId) {
        daoArgumentsVerifier.verifyPost(postId);
        Map<String, Object> paramMap = paramsMapper.asMap(asList("id"), asList(postId));
        sqlQueryExecutor.updateQuery(SQL_REMOVE_POST, paramMap);
    }

    public void updatePost(Post post) {
        daoArgumentsVerifier.verifyPost(post.getId());
        Map<String, Object> paramMap = paramsMapper.asMap(asList("//hzhzhzhz"),
                asList("//hzhzhz"));
        sqlQueryExecutor.updateQuery(SQL_UPDATE_POST, paramMap);
    }

    public List<Post> getPosts(int userId) {
        daoArgumentsVerifier.verifyUser(userId);
        Map<String, Object> paramMap = paramsMapper.asMap(asList("//hzhzhz"),
                asList("//hzhzhhzhz"));
        List<Post> posts = sqlQueryExecutor.queryForObjects(SQL_GET_POSTS, paramMap,
                USER_ROW_MAPPER);
        return posts;
    }
}
