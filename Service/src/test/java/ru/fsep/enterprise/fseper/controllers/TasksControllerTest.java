package ru.fsep.enterprise.fseper.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.fsep.enterprise.fseper.AppContext;
import ru.fsep.enterprise.fseper.AppTestContext;
import ru.fsep.enterprise.fseper.controllers.converters.TasksAndStepsConverter;
<<<<<<< HEAD
import ru.fsep.enterprise.fseper.controllers.dto.StepDto;
import ru.fsep.enterprise.fseper.models.Step;
=======
>>>>>>> 8b8eff6e20560f0682f923ef0cc6a7de17dc6edb
import ru.fsep.enterprise.fseper.models.Task;
import ru.fsep.enterprise.fseper.service.facades.UsersServiceFacade;

import java.util.List;

<<<<<<< HEAD
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
=======
//import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
>>>>>>> 8b8eff6e20560f0682f923ef0cc6a7de17dc6edb
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.fsep.enterprise.fseper.TestData.USER;
import static ru.fsep.enterprise.fseper.TestData.STEPDTO;


/**
 * Created by Ôëþð on 20.07.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppTestContext.class, AppContext.class})
@WebAppConfiguration
public class TasksControllerTest {
    private MockMvc mockMvc;
    @Autowired
    private UsersServiceFacade usersServiceFacade;
    @Autowired
    private TasksAndStepsConverter converter;
    @Autowired
    WebApplicationContext context;

    @Before
    public void setUp() throws Exception {

        Mockito.reset(usersServiceFacade);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    public void getTaskById(int taskId, Task task) {
        List<Task> tasks = USER.getTasks();
        for (Task t : tasks) {
            if (t.getId() == taskId) task = t;
        }
    }

    @Test
    public void testGetTask() throws Exception {
        Task task = USER.getTasks().get(0);
        int taskId = task.getId();
        Step step = task.getSteps().get(0);
        Step step2 = task.getSteps().get(1);
        when(usersServiceFacade.getTask(taskId)).thenReturn(task);
        mockMvc.perform(get("/tasks/{task-id}.json", taskId).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id", is(String.valueOf(taskId))))
                .andExpect(jsonPath("$.data.privated", is(String.valueOf(task.isPrivated()))))
                .andExpect(jsonPath("$.data.description", is(task.getDescription())))
                .andExpect(jsonPath("$.data.dueDate", is(String.valueOf(task.getDueDate()))))
                .andExpect(jsonPath("$.data.steps[0].id", is(String.valueOf(step.getId()))))
                .andExpect(jsonPath("$.data.steps[0].taskId", is(String.valueOf(step.getTaskId()))))
                .andExpect(jsonPath("$.data.steps[0].description", is(step.getDescription())))
                .andExpect(jsonPath("$.data.steps[0].finished", is(String.valueOf(step.isFinished()))))
                .andExpect(jsonPath("$.data.steps[1].id", is(String.valueOf(step2.getId()))))
                .andExpect(jsonPath("$.data.steps[1].taskId", is(String.valueOf(step2.getTaskId()))))
                .andExpect(jsonPath("$.data.steps[1].description", is(step2.getDescription())))
                .andExpect(jsonPath("$.data.steps[1].finished", is(String.valueOf(step2.isFinished()))))
                .andExpect(jsonPath("$.data.finished", is(String.valueOf(task.isFinished()))));
        verify(usersServiceFacade).getTask(taskId);
    }

    @Test
    public void testUpdateTask() throws Exception {

    }

    @Test
    public void testRemoveTask() throws Exception {
        Task task = USER.getTasks().get(0);
        int taskId = task.getId();
        mockMvc.perform(delete("/tasks/{task-id}", taskId).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(usersServiceFacade).removeTask(taskId);
    }

    @Test
    public void testGetSteps() throws Exception {
        Task task = USER.getTasks().get(0);
        int taskId = task.getId();
        when(usersServiceFacade.getTask(taskId)).thenReturn(task);
        List<Step> steps = task.getSteps();
        mockMvc.perform(get("/tasks/{task-id}/steps.json",taskId).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].id", is(String.valueOf(steps.get(0).getId()))))
                .andExpect(jsonPath("$.data[0].taskId", is(String.valueOf(steps.get(0).getTaskId()))))
                .andExpect(jsonPath("$.data[0].description", is(steps.get(0).getDescription())))
                .andExpect(jsonPath("$.data[0].finished", is(String.valueOf(steps.get(0).isFinished()))))
                .andExpect(jsonPath("$.data[1].id", is(String.valueOf(steps.get(1).getId()))))
                .andExpect(jsonPath("$.data[1].taskId", is(String.valueOf(steps.get(1).getTaskId()))))
                .andExpect(jsonPath("$.data[1].description", is(steps.get(1).getDescription())))
                .andExpect(jsonPath("$.data[1].finished", is(String.valueOf(steps.get(1).isFinished()))));
    }

    @Test
    public void testGetStep() throws Exception {
        Task task = USER.getTasks().get(0);
        int taskId = task.getId();
        when(usersServiceFacade.getTask(taskId)).thenReturn(task);
        List<Step> steps = task.getSteps();
        int stepId = steps.get(0).getId();
        mockMvc.perform(get("/tasks/{task-id}/steps/{step-id}.json", taskId, stepId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id", is(String.valueOf(stepId))))
                .andExpect(jsonPath("$.data.taskId", is(String.valueOf(taskId))))
                .andExpect(jsonPath("$.data.description", is(steps.get(0).getDescription())))
                .andExpect(jsonPath("$.data.finished", is(String.valueOf(steps.get(0).isFinished()))));
    }

    @Test
    public void testGetStepsByFilter() throws Exception {
        Task task = USER.getTasks().get(0);
        int taskId = task.getId();
        when(usersServiceFacade.getTask(taskId)).thenReturn(task);
        List<Step> steps = task.getSteps();
        boolean finished = steps.get(0).isFinished();
        int stepId = steps.get(0).getId();
        int stepId2 = steps.get(1).getId();
        mockMvc.perform(get("/tasks/{task-id}/steps.json/filter={finished}", taskId, finished)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].id", is(String.valueOf(stepId))))
                .andExpect(jsonPath("$.data[0].taskId", is(String.valueOf(taskId))))
                .andExpect(jsonPath("$.data[0].description", is(steps.get(0).getDescription())))
                .andExpect(jsonPath("$.data[0].finished", is(String.valueOf(steps.get(0).isFinished()))))
                .andExpect(jsonPath("$.data[1].id", is(String.valueOf(stepId2))))
                .andExpect(jsonPath("$.data[1].taskId", is(String.valueOf(taskId))))
                .andExpect(jsonPath("$.data[1].description", is(steps.get(1).getDescription())))
                .andExpect(jsonPath("$.data[1].finished", is(String.valueOf(steps.get(1).isFinished()))));
    }

    @Test
    public void testAddStep() throws Exception {
//        Task task = USER.getTasks().get(0);
//        int taskId = task.getId();
//        when(usersServiceFacade.getTask(taskId)).thenReturn(task);
//        List<Step> steps = task.getSteps();
//        int stepId = steps.get(0).getId();
//        mockMvc.perform(post("/tasks/{task-id}/steps/assignments", taskId, STEPDTO).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.data.id", is(STEPDTO.getId())))
//                .andExpect(jsonPath("$.data.taskId", is(STEPDTO.getTaskId())))
//                .andExpect(jsonPath("$.data.description", is(STEPDTO.getDescription())))
//                .andExpect(jsonPath("$.data.finished", is(STEPDTO.getFinished())));
    }

    @Test
    public void testUpdateStep() throws Exception {

    }

    @Test
    public void testRemoveStep() throws Exception {

    }
}