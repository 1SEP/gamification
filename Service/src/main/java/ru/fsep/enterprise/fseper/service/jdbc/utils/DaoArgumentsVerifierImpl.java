package ru.fsep.enterprise.fseper.service.jdbc.utils;

import ru.fsep.enterprise.fseper.models.Post;
import ru.fsep.enterprise.fseper.models.User;
import ru.fsep.enterprise.fseper.service.exceptions.PostsNotFoundException;
import ru.fsep.enterprise.fseper.service.exceptions.UserNotFoundException;
import java.util.Map;
import static java.util.Arrays.asList;

public class DaoArgumentsVerifierImpl implements DaoArgumentsVerifier {

    private SqlQueryExecutor sqlQueryExecutor;
    private ParamsMapper paramsMapper;

    //language=SQL
    private static final String SQL_COUNT_USERS_BY_ID =
            "SELECT COUNT (*) FROM users WHERE (id = :userId)";
    //language=SQL
    private static final String SQL_GET_USERS_BY_NAME =
            "SELECT COUNT (*) FROM users WHERE first_name = :firstName, last_name =: lastName";
    //language=SQL
    private static final String SQL_COUNT_OF_POST_BY_POSTID = "SELECT count(*) FROM posts WHERE id = : postId";

    public void verifyUser(User user) {
        verifyUserById(user.getId());

        String firstName = user.getPersonInfo().getFirstName();
        String lastName = user.getPersonInfo().getLastName();
        verifyUserByName(firstName, lastName);
    }

    public void verifyUserById(int userId) {
        Map<String, Object> paramMap = paramsMapper.asMap(asList("userId"), asList(userId));
        int countAvailableUsers = sqlQueryExecutor.queryForInt(SQL_COUNT_USERS_BY_ID, paramMap);
        if (countAvailableUsers != 1) {
            throw new UserNotFoundException(userId);
        }
    }

    public void verifyUserByName(String firstName, String lastName) {
        Map<String, Object> paramMap = paramsMapper.asMap(asList("firstName", "lastName"), asList(firstName, lastName));
        int countOfUsers = sqlQueryExecutor.queryForInt(SQL_GET_USERS_BY_NAME, paramMap);
        if (countOfUsers == 0) {
            throw new UserNotFoundException(firstName, lastName);
        }
    }

    public void verifyTask(int taskId) {
    }

    public void verifyPostById(int postId) {
        Map<String, Object> paramMap = paramsMapper.asMap(asList("postId"), asList(postId));
        int countOfPosts = sqlQueryExecutor.queryForInt(SQL_COUNT_OF_POST_BY_POSTID, paramMap);
        if (countOfPosts == 0) {
            throw new PostsNotFoundException(postId);
        }
    }

    public void verifyPost(Post post) {
        verifyPostById(post.getId());
    }
}
