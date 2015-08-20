package ru.fsep.enterprise.fseper.test.data;

import ru.fsep.enterprise.fseper.controllers.dto.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Ôëþð on 19.08.2015.
 */
public class TestDataAPI {
    public static final StepDto STEP_DTO = initStepDto("3", "2", "step of steps", "false");

    public static final TaskDto TASK_DTO = initTaskDto();
    public static final StepDto STEP_DTO_1_OF_TASK = TASK_DTO.getSteps().get(0);
    public static final StepDto STEP_DTO_2_OF_TASK = TASK_DTO.getSteps().get(1);
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
    static public List<TaskDto> initTasksDto() {
        List<TaskDto> listOfTasks = new LinkedList<TaskDto>();
        listOfTasks.add(initTaskDto());
        TasksDto tasksDto = new TasksDto();
        tasksDto.setTaskDtos(listOfTasks);
        return listOfTasks;
    }
    static public UserDto initUserDto() {
        return new UserDto("1", initTasksDto(), initPersonInfoDto());
    }
    static public PersonInfoDto initPersonInfoDto() {
        return new PersonInfoDto(surname, name, "8.8", birthDay, role, null, initPostsDto());
    }
    static public List<PostDto> initPostsDto() {
        List<PostDto> listOfPostDto = new LinkedList<PostDto>();
        listOfPostDto.add(new PostDto("1", postName1, postDescription1));
        listOfPostDto.add(new PostDto("2", postName2, postDescription2));
        return listOfPostDto;
    }
}
