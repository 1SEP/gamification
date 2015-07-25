package ru.fsep.enterprise.fseper;

import ru.fsep.enterprise.fseper.controllers.dto.StepDto;
import ru.fsep.enterprise.fseper.controllers.dto.TaskDto;
import ru.fsep.enterprise.fseper.models.*;

import java.net.URL;
import java.util.*;

/**
 * Created by ���� on 10.07.2015.
 */
public class TestData {

    public static final User USER = new User(1, initAuthData(), initPersonInfo(), initTasks());
    public static final StepDto STEPDTO = initStepDto();
    static public StepDto initStepDto(){
    public static final User USER = new User(1, initAuthData(), initPersonInfo(), initTasks());
    public static final StepDto STEP_DTO = initStepDto("3", "2", "step of steps", "false");
    public static final TaskDto TASK_DTO = initTaskDto();


    static public StepDto initStepDto(String id, String taskId, String Description, String Finished) {
>>>>>>> a8ed60a509d4ed07b8d729ee5276f5512a817f8d
        StepDto stepDto = new StepDto();
        stepDto.setId(id);
        stepDto.setTaskId(taskId);
        stepDto.setDescription(Description);
        stepDto.setFinished(Finished);
        return stepDto;
    }

    static public TaskDto initTaskDto() {

        Calendar c = Calendar.getInstance();
        Date date = c.getTime();
        String s = String.valueOf(date);

        List<StepDto> stepDtos = new LinkedList<StepDto>();
        stepDtos.add(initStepDto("1", "3", "step of third task", "true"));
        stepDtos.add(initStepDto("2", "3", "second step of third task", "false"));

        TaskDto taskDto = new TaskDto();
        taskDto.setId("3");
        taskDto.setDueDate(s);
        taskDto.setPrivated("false");
        taskDto.setDescription("best task in the world");
        taskDto.setSteps(stepDtos);
        taskDto.setFinished("true");
        return taskDto;
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
        personInfo = new PersonInfo(surname, name, 8.8, birthDay, initPosts(), role, photo);
        return personInfo;
    }

    static public AuthData initAuthData(){
        return new AuthData("password", "login");
    }

    static  public List<Step> initSteps(){

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

    static public User initUser(){
        return new User (0 ,null, initPersonInfo(), initTasks());
    }
}
