package ru.fsep.enterprise.fseper.service.dao;

import ru.fsep.enterprise.fseper.models.Post;
import ru.fsep.enterprise.fseper.service.jdbc.utils.DaoArgumentsVerifier;
import ru.fsep.enterprise.fseper.service.jdbc.utils.ParamsMapper;
import ru.fsep.enterprise.fseper.service.jdbc.utils.SqlQueryExecutor;

import java.util.List;
import java.util.Map;

/**
 * Created by Rauf on 13.07.2015.
 */
public class PostsDaoImpl implements PostsDao {

    private SqlQueryExecutor sqlQueryExecutor;
    private ParamsMapper paramsMapper;
    private DaoArgumentsVerifier verifier;

    public PostsDaoImpl(SqlQueryExecutor sqlQueryExecutor, ParamsMapper paramsMapper, DaoArgumentsVerifier verifier) {
        this.sqlQueryExecutor = sqlQueryExecutor;
        this.paramsMapper = paramsMapper;
        this.verifier = verifier;
    }

    static final String SQL_ADD_POST =
            "INSERT INTO post(id) VALUES (:id);";

    static final String SQL_REMOVE_POST =
            "DELETE FROM post WHERE (id = :postId);";

    static final String SQL_UPDATE_POST =
            ";";

    static final String SQL_GET_POSTS =
            "SELECT * FROM post;";

    public void addPost(Post post) {
        verifier.verifyPost(post.getId());
        Map<String,Object> paramMap = paramsMapper.asMap(,);
        sqlQueryExecutor.
    }

    public void removePost(int postId) {

    }

    public void updatePost(int postId) {

    }

    public List<Post> getPosts() {
        return null;
    }
}
