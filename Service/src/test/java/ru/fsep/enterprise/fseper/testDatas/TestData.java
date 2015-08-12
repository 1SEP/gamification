package ru.fsep.enterprise.fseper.testDatas;

import ru.fsep.enterprise.fseper.controllers.dto.*;
import ru.fsep.enterprise.fseper.models.*;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by ���� on 10.07.2015.
 */
public class TestData {
    public static final User USER = new User(1, initAuthData(), initPersonInfo(), initTasks());
    public static final StepDto STEP_DTO = initStepDto("3", "2", "step of steps", "false");
    public static final TaskDto TASK_DTO = initTaskDto();

    static private final URL photo = null;
    static private final String surname = "Komarov";
    static private final String name = "Nikita";
    static private final String birthDay = "13.12.1990";
    static private final String role = "have responsibility for the recoupment project";

    static private final String postName1 = "It director";
    static private final String postDescription1 = "He creates new steps of company development in IT";
    static private final String postName2 = "Team Lead";
    static private final String postDescription2 = "He is mentor of developer's crew";

    static public StepDto initStepDto(String id, String taskId, String Description, String Finished) {
        StepDto stepDto = new StepDto();
        stepDto.setId(id);
        stepDto.setTaskId(taskId);
        stepDto.setDescription(Description);
        stepDto.setFinished(Finished);
        return stepDto;
    }

    static public TaskDto initTaskDto() {

//        Calendar c = Calendar.getInstance();
//        Date date = c.getTime();
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd.mm.yyyy hh:mm");
        String s = String.valueOf(format.format(date));

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

    static public List<TaskDto> initTasksDto(){
        List<TaskDto> listOfTasks = new LinkedList<TaskDto>();
        listOfTasks.add(initTaskDto());
        TasksDto tasksDto = new TasksDto();
        tasksDto.setTaskDtos(listOfTasks);
        return listOfTasks;
    }

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
//        try {
//            photo = new URL("C:\\Users\\Flyur\\gamification\\Service\\src\\test\\java\\resources");
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
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

    static public User initUser(){
        return new User (0 ,null, initPersonInfo(), initTasks());
    }

    static public List<PostDto>  initPostsDto() {
        List<PostDto> listOfPostDto = new LinkedList<PostDto>();
        listOfPostDto.add(new PostDto("1", postName1, postDescription1));
        listOfPostDto.add(new PostDto("2", postName2, postDescription2));
        return listOfPostDto;
    }

    static public PersonInfoDto initPersonInfoDto(){
        return new PersonInfoDto(surname, name ,"8.8" , birthDay, role, null, initPostsDto());
    }

    static public UserDto initUserDto(){
        return new UserDto("1", initTasksDto(), initPersonInfoDto());
    }

}
