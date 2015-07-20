package ru.fsep.enterprise.fseper.service.dao;

import ru.fsep.enterprise.fseper.models.*;

import java.net.URL;
import java.util.*;

public class TestData {
    static final int USER_ID = 1;
    static final int INCORRECT_USER_ID = 2;
    public static final String INCORRECT_FIRSTNAME = "Incorrect first name";
    public static final String INCORRECT_LASTNAME = "Incorrect last name";
    static final User USER = new User(USER_ID, initAuthData(), initPersonInfo(), initTasks());
    static final User INCORRECT_USER = new User(INCORRECT_USER_ID, initAuthData(), initPersonInfo(), initTasks());
    static final Map<String, Object> USER_MAP = createUserMap();
    static final List<User> LIST_OF_USERS = getListOfUsers();
    static final Post POST = getPost();
    public static final Post INCORRECT_POST = getIncorrectPost();
    public static final int INCORRECT_POST_ID = 2;

    private static Post getIncorrectPost() {
        return new Post(INCORRECT_POST_ID, "Incorrect post name", "incorrect post description");
    }

    static final Map<String, Object> POST_MAP = getPostMap();

    private static Post getPost() {
        return new Post(1, "postName", "postDescription");
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
        return map;
    }

    private static List<User> getListOfUsers(){
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

    static public List<Task> initTasks(){
        List<Task> tasks = new ArrayList<Task>();
        String description = "create controller";
        Calendar c = Calendar.getInstance();
        Date date = c.getTime();
        tasks.add(new Task(1, true, description, date, initSteps(), false));
        description = "refactor models";
        tasks.add(new Task(1, true, description, date, initSteps(), false));
        return tasks;
    }

    static  public PersonInfo initPersonInfo(){
        URL photo = null;
        String surname = "Almakayev";
        String name = "Ildar";
        String birthDay = "31.12.1991";
        String role = "have responsibility for the recoupment project";
        PersonInfo personInfo  = new PersonInfo(surname, name, 8.8, birthDay, initPosts(), role, photo);
        return personInfo;
    }

    static public AuthData initAuthData(){
        return new AuthData("password", "login");
    }

    static  public List<Step> initSteps(){
        List<Step> steps = new ArrayList<Step>();
        String description = "step by step perform";
        steps.add(new Step(1, 1, description, false));
        description = "second step of task";
        steps.add(new Step(2, 1, description, false));
        return steps;
    }
}