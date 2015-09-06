package ru.fsep.enterprise.fseper.service.dao;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import ru.fsep.enterprise.fseper.AppContext;
import ru.fsep.enterprise.fseper.models.Post;
import ru.fsep.enterprise.fseper.service.exceptions.PostsNotFoundException;
import ru.fsep.enterprise.fseper.service.exceptions.UserNotFoundException;
import ru.fsep.enterprise.fseper.service.jdbc.utils.DaoArgumentsVerifier;
import ru.fsep.enterprise.fseper.service.jdbc.utils.ParamsMapper;
import ru.fsep.enterprise.fseper.service.jdbc.utils.SqlQueryExecutor;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static ru.fsep.enterprise.fseper.service.dao.PostsDaoImpl.POST_ROW_MAPPER;
import static ru.fsep.enterprise.fseper.service.dao.PostsDaoImpl.SQL_GET_POSTS_BY_USER_ID;
import static ru.fsep.enterprise.fseper.test.data.TestDataForUserDao.*;

@ContextConfiguration(classes = {AppContext.class})
public class PostsDaoImplTest {

    private PostsDaoImpl postsDaoImplTest;

    @Mock
    private SqlQueryExecutor sqlQueryExecutorMock;
    @Mock
    private DaoArgumentsVerifier daoArgumentsVerifierMock;
    @Mock
    private ParamsMapper paramsMapperMock;

    private void stubbingDaoArgumentsVerifierMock() {
        doThrow(PostsNotFoundException.class).when(daoArgumentsVerifierMock).verifyPostById(INCORRECT_POST_ID);
        doThrow(PostsNotFoundException.class).when(daoArgumentsVerifierMock).verifyPost(INCORRECT_POST);
        doThrow(UserNotFoundException.class).when(daoArgumentsVerifierMock).verifyUserById(INCORRECT_USER_ID);
    }

    private void stubbingSqlQueryExecutorMock() {
        doReturn(LIST_OF_POSTS).when(sqlQueryExecutorMock).queryForObjects(SQL_GET_POSTS_BY_USER_ID, USER_MAP, POST_ROW_MAPPER);
    }

    private void stubbingParamMapperMock() {
        doReturn(USER_MAP).when(paramsMapperMock).asMap(asList("userId"), asList(USER_ID));
    }

    private void stubbingAll() {
        stubbingSqlQueryExecutorMock();
        stubbingDaoArgumentsVerifierMock();
        stubbingParamMapperMock();
    }

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        stubbingAll();
        postsDaoImplTest = new PostsDaoImpl(sqlQueryExecutorMock, paramsMapperMock, daoArgumentsVerifierMock);
    }

    @Test
    public void testAddPost() throws Exception {
        postsDaoImplTest.addPost(POST, USER_ID);
        verify(daoArgumentsVerifierMock).verifyPost(POST);
    }

    @Test(expected = PostsNotFoundException.class)
    public void testAddPostByIncorrectPost() {
        postsDaoImplTest.addPost(INCORRECT_POST, USER_ID);
    }

    @Test
    public void testRemovePost() throws Exception {
        postsDaoImplTest.removePost(POST_ID);
        verify(daoArgumentsVerifierMock).verifyPostById(POST_ID);
    }

    @Test(expected = PostsNotFoundException.class)
    public void testRemovePostByIncorrectPostId() {
        postsDaoImplTest.removePost(INCORRECT_POST_ID);
    }

    @Test
    public void testUpdatePost() throws Exception {
        postsDaoImplTest.updatePost(POST);
        verify(daoArgumentsVerifierMock).verifyPost(POST);
    }

    @Test(expected = PostsNotFoundException.class)
    public void testUpdatePostByIncorrectPostId() {
        postsDaoImplTest.updatePost(INCORRECT_POST);
    }

    @Test
    public void testGetPosts() throws Exception {
        List<Post> expected = LIST_OF_POSTS;
        List<Post> actual = postsDaoImplTest.getPosts(USER_ID);
        assertEquals(expected, actual);
    }

//    @Test(expected = UserNotFoundException.class)
    public void testGetPostsByIncorrectUserId() {
        List<Post> expected = LIST_OF_POSTS;
        List<Post> actual = postsDaoImplTest.getPosts(INCORRECT_USER_ID);
        assertEquals(expected, actual);
    }
}