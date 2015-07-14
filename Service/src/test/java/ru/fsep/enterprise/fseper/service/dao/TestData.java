package ru.fsep.enterprise.fseper.service.dao;

import ru.fsep.enterprise.fseper.models.Post;
import ru.fsep.enterprise.fseper.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Marsel Sidikov and Ildar Almakayev (First Software Engineering Platform))
 */
public class TestData {
    public static final int USER_ID = 1;
    public static final String NAME = null;
    public static final String SURNAME = null;
    public static final User USER = new User(USER_ID, null, null, null);
    public static final Map<String, Object> USER_MAP = createUserMap();
    public static final List<User> LIST_OF_USERS = getListOfUsers();
    public static final Post POST = getPost();
    public static final Map<String, Object> POST_MAP = getPostMap();

    private static Map<String, Object> getPostMap() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("postId", POST.getId());
        result.put("postName", POST.getName());
        result.put("postDesciption", POST.getDescription());
        return result;
    }

    private static Post getPost() {
        return new Post(1, "postName", "postDescription");
    }

    private static Map<String, Object> createUserMap() {
        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("userId", USER_ID);
        map.put("firstName", NAME);
        map.put("lastName", SURNAME);
        return map;
    }

    private static List<User> getListOfUsers(){
        List<User> result = new ArrayList<User>();
        result.add(USER);
        return result;
    }
}
