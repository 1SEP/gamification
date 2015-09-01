package ru.fsep.enterprise.fseper.test.data;
import ru.fsep.enterprise.fseper.models.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class TestDataForUserDao {

    public static final int USER_ID = 0;
    public static final int INCORRECT_USER_ID = 2;
    public static final String INCORRECT_FIRSTNAME = "Incorrect first name";
    public static final String INCORRECT_LASTNAME = "Incorrect last name";
    public static final User USER = new User(USER_ID, initAuthData(), initPersonInfo(), TestDataCore.initTasks());
    public static final User INCORRECT_USER = new User(INCORRECT_USER_ID, initAuthData(), initPersonInfo(), TestDataCore.initTasks());
    public static final Map<String, Object> USER_MAP = createUserMap();
    public static final Map<String, Object> USER_MAP_WITH_NAMES = getUserMapWithNames();
    public static final List<User> LIST_OF_USERS = getListOfUsers();

    public static final int POST_ID = 1;
    public static final int INCORRECT_POST_ID = 2;
    public static final Post POST = getPost();
    public static final Post INCORRECT_POST = getIncorrectPost();
    public static final Map<String, Object> POST_MAP = getPostMap();
    public static final List LIST_OF_POSTS = getListOfPosts();

    private static Map<String, Object> getUserMapWithNames() {
        Map<String, Object> map = new HashMap<String, Object>();
        String firstName = USER.getPersonInfo().getFirstName();
        String lastName = USER.getPersonInfo().getLastName();
        map.put("firstName", firstName);
        map.put("lastName", lastName);

        return map;
    }

    private static List getListOfPosts() {
        List<Post> result = new ArrayList<Post>();
        result.add(POST);
        return result;
    }

    private static Post getIncorrectPost() {
        return new Post(INCORRECT_POST_ID, "Incorrect post name", "incorrect post description");
    }

    private static Post getPost() {
        return new Post(POST_ID, "postName", "postDescription");
    }

    private static Map<String, Object> getPostMap() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("postId", POST.getId());
        result.put("postName", POST.getName());
        result.put("postDesciption", POST.getDescription());

        return result;
    }

    private static Map<String, Object> createUserMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", USER_ID);
        return map;
    }

    private static List<User> getListOfUsers() {
        List<User> result = new ArrayList<User>();
        result.add(USER);
        return result;
    }

    static public List<Post> initPosts() {
        List<Post> posts = new ArrayList<Post>();
        String post = "It director";
        String description = "He creates new steps of company development in IT";
        posts.add(new Post(1, post, description));
        post = "Team Lead";
        description = "He is mentor of developer's crew";
        posts.add(new Post(2, post, description));

        return posts;
    }

    static public PersonInfo initPersonInfo() {
        URL photo = null;
        try {
            photo = new URL("http://cs627828.vk.me/v627828952/1121a/dVYbT2kT7ps.jpg");
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(e);
        }
        String surname = "Almakayev";
        String name = "Ildar";
        String birthDay = "1991-12-31";
        String role = "java-dev";
        PersonInfo personInfo = new PersonInfo(name, surname, 8.8, birthDay, initPosts(), role, photo);

        return personInfo;
    }

    static public AuthData initAuthData() {
        return new AuthData("ildar_pas", "ildar_login");
    }

    static public List<Step> initSteps() {
        List<Step> steps = new ArrayList<Step>();
        String description = "step by step perform";
        steps.add(new Step(1, 1, description, false));
        description = "second step of task";
        steps.add(new Step(2, 1, description, false));
        return steps;
    }
}


