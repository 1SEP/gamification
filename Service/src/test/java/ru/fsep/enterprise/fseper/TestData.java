package ru.fsep.enterprise.fseper;

import ru.fsep.enterprise.fseper.models.*;


import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ���� on 10.07.2015.
 */
public class TestData {
    public static final User USER = new User(1, initAuthData(), initPersonInfo(), initTasks());

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
        tasks.add( new Task(1, true, description, date, initSteps(), false));
        return tasks;
    }
    static  public PersonInfo initPersonInfo(){
        PersonInfo personInfo;
        URL photo = null;
//        try {
//            photo = new URL("C:\\Users\\Flyur\\gamification\\Service\\src\\test\\java\\resources");
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
        String surname = "Komarov";
        String name = "Nikita";
        String birthDay = "13.12.1990";
        String role = "have responsibility for the recoupment project";
        personInfo  = new PersonInfo(surname, name, 8.8, birthDay, initPosts(), role, photo);
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
