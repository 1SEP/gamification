package ru.fsep.enterprise.fseper.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.fsep.enterprise.fseper.AppContext;
import ru.fsep.enterprise.fseper.AppTestContext;
import ru.fsep.enterprise.fseper.TestData;
import ru.fsep.enterprise.fseper.controllers.converters.TasksAndStepsConverter;
import ru.fsep.enterprise.fseper.controllers.converters.TasksAndStepsConverterImpl;
import ru.fsep.enterprise.fseper.models.Task;
import ru.fsep.enterprise.fseper.models.User;
import ru.fsep.enterprise.fseper.service.facades.UsersServiceFacade;
import ru.fsep.enterprise.fseper.service.facades.UsersServiceFacadeImpl;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Created by Ôëþð on 20.07.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppTestContext.class, AppContext.class})
@WebAppConfiguration
public class TasksControllerTest {
    User user;
    int userId;
    int taskId;
    Task task;
    private MockMvc mockMvc;
    @Autowired
    private UsersServiceFacade usersServiceFacade;
    @Autowired
    private TasksAndStepsConverter converter;
    @Autowired
    WebApplicationContext context;
    @Before
    public void setUp() throws Exception{
        user = TestData.USER;
        //List<Task> tasks= TestData.USER.getTasks();
        userId = user.getId();
        taskId = user.getTasks().get(0).getId();
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        usersServiceFacade = mock(UsersServiceFacadeImpl.class);
    }
    public void getTaskById(){
        List<Task> tasks = user.getTasks();
        for (Task t : tasks) {
            if (t.getId() == taskId) task = t;
        }
    }
    @Test
    public void testGetTask() throws Exception {
        getTaskById();
        when(usersServiceFacade.getTask(taskId)).thenReturn(task);
        mockMvc.perform(get("{task-id}.json",taskId))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
           //     .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.id", is(String.valueOf(taskId))))
                .andExpect(jsonPath("$.privated", is(String.valueOf(task.isPrivated()))))
                .andExpect(jsonPath("$.description", is(task.getDescription())))
                .andExpect(jsonPath("$.duedate", is(String.valueOf(task.getDueDate()))))
                .andExpect(jsonPath("$.steps", is(converter.fromSteps(task.getSteps()))))
                .andExpect(jsonPath("$.finished", is(String.valueOf(task.isFinished()))));
        verify(usersServiceFacade).getTask(taskId);
    }

    @Test
    public void testUpdateTask() throws Exception {

    }

    @Test
    public void testRemoveTask() throws Exception {

    }

    @Test
    public void testGetSteps() throws Exception {

    }

    @Test
    public void testGetStep() throws Exception {

    }

    @Test
    public void testGetStepsByFilter() throws Exception {

    }

    @Test
    public void testAddStep() throws Exception {

    }

    @Test
    public void testUpdateStep() throws Exception {

    }

    @Test
    public void testRemoveStep() throws Exception {

    }
}