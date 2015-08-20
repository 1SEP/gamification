package ru.fsep.enterprise.fseper.test.data;

import ru.fsep.enterprise.fseper.models.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ���� on 10.07.2015.
 */
public class TestDataCore {
    public static final User USER = new User(1, initAuthData(), initPersonInfo(), initTasks());
    public static final Task TASK_1 = USER.getTasks().get(0);
    public static final Step STEP_1_OF_TASK_1 = TASK_1.getSteps().get(0);
    public static final Step STEP_2_OF_TASK_1 = TASK_1.getSteps().get(1);

    public static final Task TASK_2 = USER.getTasks().get(1);
    public static final Step STEP_1_OF_TASK_2 = TASK_2.getSteps().get(0);
    public static final Step STEP_2_OF_TASK_2 = TASK_2.getSteps().get(1);

    static private final String surname = "Komarov";
    static private final String name = "Nikita";
    static private final String birthDay = "13.12.1990";
    static private final String role = "have responsibility for the recoupment project";

    static private final String postName1 = "It director";
    static private final String postDescription1 = "He creates new steps of company development in IT";
    static private final String postName2 = "Team Lead";
    static private final String postDescription2 = "He is mentor of developer's crew";


    static public List<Post> initPosts() {
        List<Post> posts = new ArrayList<Post>();
        posts.add(new Post(1, postName1, postDescription1));
        posts.add(new Post(2, postName2, postDescription2));
        return posts;
    }

    static public List<Task> initTasks() {
        List<Task> tasks = new ArrayList<Task>();
        String description = "create controller";
        Calendar c = Calendar.getInstance();
        Date date = c.getTime();
        tasks.add(new Task(1, true, description, date, initSteps(), false));
        description = "refactor models";
        tasks.add(new Task(2, true, description, date, initSteps(), false));
        return tasks;
    }

    static public PersonInfo initPersonInfo() {
        PersonInfo personInfo;
        personInfo = new PersonInfo(surname, name, 8.8, birthDay, initPosts(), role, null);
        return personInfo;
    }

    static public AuthData initAuthData() {
        return new AuthData("password", "login");
    }

    static public List<Step> initSteps() {
        List<Step> steps = new ArrayList<Step>();
        String description = "step by step perform";
        steps.add(new Step(1, 1, description, false));
        description = "second step of task";
        steps.add(new Step(2, 1, description, false));
        return steps;
    }

    static public User initUser() {
        return new User(0, null, initPersonInfo(), initTasks());
    }


}
