package ru.fsep.enterprise.fseper.service.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.fsep.enterprise.fseper.models.Post;
import ru.fsep.enterprise.fseper.service.jdbc.utils.DaoArgumentsVerifier;
import ru.fsep.enterprise.fseper.service.jdbc.utils.ParamsMapper;
import ru.fsep.enterprise.fseper.service.jdbc.utils.SqlQueryExecutor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

@Repository
public class PostsDaoImpl implements PostsDao {
    @Autowired
    private SqlQueryExecutor sqlQueryExecutor;

    @Autowired
    private ParamsMapper paramsMapper;

    @Autowired
    private DaoArgumentsVerifier daoArgumentsVerifier;

    public PostsDaoImpl() {
    }

    public PostsDaoImpl(SqlQueryExecutor sqlQueryExecutor, ParamsMapper paramsMapper, DaoArgumentsVerifier daoArgumentsVerifier) {
        this.sqlQueryExecutor = sqlQueryExecutor;
        this.paramsMapper = paramsMapper;
        this.daoArgumentsVerifier = daoArgumentsVerifier;
    }

    public static final RowMapper<Post> POST_ROW_MAPPER = new RowMapper<Post>() {
        public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String description = rs.getString("description");
            return new Post(id, name, description);
        }
    };

    //language=SQL
    static final String SQL_INSERT_POST = "INSERT INTO post VALUES (:postId, :postName, :postDescription);";
    //language=SQL
    static final String SQL_INSERT_INTO_POSTS = "INSERT INTO posts VALUES (:postId, :userId);";
    //language=SQL
    static final String SQL_REMOVE_POST = "DELETE FROM post WHERE (id = :postId);";
    //language=SQL
    static final String SQL_UPDATE_POST = "UPDATE post SET name = :name, description = :description WHERE id = :id;";
    //language=SQL
    static final String SQL_GET_POSTS_BY_USER_ID = "SELECT * FROM post INNER JOIN posts ON posts.info_id = :userId;";

    public void addPost(Post post, int userId) {
        daoArgumentsVerifier.verifyPost(post);
        int postId = post.getId();
        String postName = post.getName();
        String postDescription = post.getDescription();
        Map<String, Object> paramMap = paramsMapper.asMap(asList("postId", "postName", "postDescription"),
                asList(postId, postName, postDescription));
        sqlQueryExecutor.updateQuery(SQL_INSERT_POST, paramMap);

        paramMap = paramsMapper.asMap(asList("userId"), asList(userId));
        sqlQueryExecutor.updateQuery(SQL_INSERT_INTO_POSTS, paramMap);
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
        List<Post> posts = sqlQueryExecutor.queryForObjects(SQL_GET_POSTS_BY_USER_ID, paramMap,
                POST_ROW_MAPPER);
        return posts;
    }
}

