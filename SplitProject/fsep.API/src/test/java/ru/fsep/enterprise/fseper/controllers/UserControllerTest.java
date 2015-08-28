package ru.fsep.enterprise.fseper.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
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
import ru.fsep.enterprise.fseper.controllers.converters.TasksAndStepsConverterImpl;
import ru.fsep.enterprise.fseper.models.User;
import ru.fsep.enterprise.fseper.service.exceptions.UserNotFoundException;
import ru.fsep.enterprise.fseper.service.facades.TasksServiceFacade;
import ru.fsep.enterprise.fseper.service.facades.UsersServiceFacade;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.fsep.enterprise.fseper.test.data.TestDataAPI.*;
import static ru.fsep.enterprise.fseper.test.data.TestDataCore.*;
/**
 * Author Fedorov Juriy on 24.07.2015
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppTestContext.class, AppContext.class})
@WebAppConfiguration
public class UserControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private UsersServiceFacade usersServiceFacade;

    @Autowired
    private TasksServiceFacade tasksServiceFacade;
    @Autowired
    WebApplicationContext context;

    @Autowired
    TasksAndStepsConverterImpl tasksAndStepsConverter;

    final ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception {
        User user = USER;
        int userId = user.getId();
        Mockito.reset(usersServiceFacade);
        Mockito.reset(tasksServiceFacade);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void testGetUserById() throws Exception {
        User user = USER;
        int userId = user.getId();

        when(usersServiceFacade.getUser(userId)).thenReturn(user);
        //when(usersServiceFacade.getTasks(userId)).thenReturn(listOfTask);

        mockMvc.perform(get("/user/{user-id}.json", userId).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                        //.andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.data.id", is("1")))
                        //.andExpect(jsonPath("&.data.personInfo.birthday", is("13.12.1990")))
                .andExpect(jsonPath("$.data.personInfo.rating", is("8.8")))
                .andExpect(jsonPath("$.data.personInfo.firstName", is("Komarov")))
                .andExpect(jsonPath("$.data.personInfo.posts[0].name", is("It director")));
        //.andExpect(jsonPath("$.data.tasks.privated", is("true")));
        verify(usersServiceFacade).getUser(userId);
    }

    @Test
    public void testGetUserWithIncorrectId() throws Exception{
        User user = USER;
        int userId = user.getId();
        //doReturn(user).when(usersServiceFacade).getTask(userId);
        when(usersServiceFacade.getUser(userId)).thenReturn(user);
        when(usersServiceFacade.getUser(Matchers.anyInt())).thenThrow(new UserNotFoundException(5));
        mockMvc.perform(get("/user/{user-id}.json", 5).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testAssignmentsTask() throws Exception {
        String json = mapper.writeValueAsString(TASK_DTO);
        mockMvc.perform(post("/user/{user-id}/tasks/assignments", USER.getId())
                .content(json.getBytes())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.id", is(TASK_DTO.getId())))
                .andExpect(jsonPath("$.data.privated", is(TASK_DTO.getPrivated())))
                .andExpect(jsonPath("$.data.description", is(TASK_DTO.getDescription())))
                .andExpect(jsonPath("$.data.dueDate", is(TASK_DTO.getDueDate())))
                .andExpect(jsonPath("$.data.steps[0].id", is(STEP_DTO_1_OF_TASK.getId())))
                .andExpect(jsonPath("$.data.steps[0].taskId", is(STEP_DTO_1_OF_TASK.getTaskId())))
                .andExpect(jsonPath("$.data.steps[0].description", is(STEP_DTO_1_OF_TASK.getDescription())))
                .andExpect(jsonPath("$.data.steps[0].finished", is(STEP_DTO_1_OF_TASK.getFinished())))
                .andExpect(jsonPath("$.data.steps[1].id", is(STEP_DTO_2_OF_TASK.getId())))
                .andExpect(jsonPath("$.data.steps[1].taskId", is(STEP_DTO_2_OF_TASK.getTaskId())))
                .andExpect(jsonPath("$.data.steps[1].description", is(STEP_DTO_2_OF_TASK.getDescription())))
                .andExpect(jsonPath("$.data.steps[1].finished", is(STEP_DTO_2_OF_TASK.getFinished())))
                .andExpect(jsonPath("$.data.finished", is(String.valueOf(TASK_DTO.getFinished()))));
    }

    @Test
    public void testSignUpUser() throws Exception {

    }
    public void testGetTasks() throws Exception {
        when(tasksServiceFacade.getTasks(USER.getId())).thenReturn(USER.getTasks());
        mockMvc.perform(get("/user/{user-id}/tasks.json", USER.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].id", is(String.valueOf(1))))
                .andExpect(jsonPath("$.data[0].privated", is(String.valueOf(TASK_1.isPrivated()))))
                .andExpect(jsonPath("$.data[0].description", is(TASK_1.getDescription())))
                .andExpect(jsonPath("$.data[0].dueDate", is(String.valueOf(TASK_1.getDueDate()))))
                .andExpect(jsonPath("$.data[0].steps[0].id", is(String.valueOf(STEP_1_OF_TASK_1.getId()))))
                .andExpect(jsonPath("$.data[0].steps[0].taskId", is(String.valueOf(STEP_1_OF_TASK_1.getTaskId()))))
                .andExpect(jsonPath("$.data[0].steps[0].description", is(STEP_1_OF_TASK_1.getDescription())))
                .andExpect(jsonPath("$.data[0].steps[0].finished", is(String.valueOf(STEP_1_OF_TASK_1.isFinished()))))
                .andExpect(jsonPath("$.data[0].steps[1].id", is(String.valueOf(STEP_2_OF_TASK_1.getId()))))
                .andExpect(jsonPath("$.data[0].steps[1].taskId", is(String.valueOf(STEP_2_OF_TASK_1.getTaskId()))))
                .andExpect(jsonPath("$.data[0].steps[1].description", is(STEP_2_OF_TASK_1.getDescription())))
                .andExpect(jsonPath("$.data[0].steps[1].finished", is(String.valueOf(STEP_2_OF_TASK_1.isFinished()))))
                .andExpect(jsonPath("$.data[0].finished", is(String.valueOf(TASK_1.isFinished()))))
                .andExpect(jsonPath("$.data[1].id", is(String.valueOf(2))))
                .andExpect(jsonPath("$.data[1].privated", is(String.valueOf(TASK_2.isPrivated()))))
                .andExpect(jsonPath("$.data[1].description", is(TASK_2.getDescription())))
                .andExpect(jsonPath("$.data[1].dueDate", is(String.valueOf(TASK_2.getDueDate()))))
                .andExpect(jsonPath("$.data[1].steps[0].id", is(String.valueOf(STEP_1_OF_TASK_2.getId()))))
                .andExpect(jsonPath("$.data[1].steps[0].taskId", is(String.valueOf(STEP_1_OF_TASK_2.getTaskId()))))
                .andExpect(jsonPath("$.data[1].steps[0].description", is(STEP_1_OF_TASK_2.getDescription())))
                .andExpect(jsonPath("$.data[1].steps[0].finished", is(String.valueOf(STEP_1_OF_TASK_2.isFinished()))))
                .andExpect(jsonPath("$.data[1].steps[1].id", is(String.valueOf(STEP_2_OF_TASK_2.getId()))))
                .andExpect(jsonPath("$.data[1].steps[1].taskId", is(String.valueOf(STEP_2_OF_TASK_2.getTaskId()))))
                .andExpect(jsonPath("$.data[1].steps[1].description", is(STEP_2_OF_TASK_2.getDescription())))
                .andExpect(jsonPath("$.data[1].steps[1].finished", is(String.valueOf(STEP_2_OF_TASK_2.isFinished()))))
                .andExpect(jsonPath("$.data[1].finished", is(String.valueOf(TASK_2.isFinished()))));
    }


    @Test
    public void testUpdateUserById() throws Exception {
        User user = USER;
        user.getPersonInfo().setRole("new role");
        int userId = user.getId();
        String json = mapper.writeValueAsString(user);
        mockMvc.perform(put("/user/{user-id}", userId, user).content(json.getBytes())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id", is("1")))
                .andExpect(jsonPath("$.data.personInfo.birthday", is("13.12.1990")));

    }

    @Test
    public void testDeleteUserById() throws Exception {
        User user = USER;
        int userId = user.getId();
        when(usersServiceFacade.getUser(userId)).thenReturn(user);
        mockMvc.perform(delete("/user/{user-id}", userId).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.login", is("login")));
    }
}