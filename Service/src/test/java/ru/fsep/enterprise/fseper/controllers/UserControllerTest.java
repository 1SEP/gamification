package ru.fsep.enterprise.fseper.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.fsep.enterprise.fseper.AppContext;
import ru.fsep.enterprise.fseper.AppTestContext;
import ru.fsep.enterprise.fseper.models.Task;
import ru.fsep.enterprise.fseper.models.User;
import ru.fsep.enterprise.fseper.service.facades.UsersServiceFacade;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.fsep.enterprise.fseper.testDatas.TestData.USER;

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
    WebApplicationContext context;
    final ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception {
        Mockito.reset(usersServiceFacade);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void testGetUserById() throws Exception {
        User user = USER;
        int userId = user.getId();
        List<Task> listOfTask = user.getTasks();

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
    public void testAssignmentsTask() throws Exception {

    }

    @Test
    public void testGetPrivatedTasks() throws Exception {

    }

    @Test
    public void testGetFinishedTasks() throws Exception {

    }

    @Test
    public void testGetTasksByDate() throws Exception {

    }

    @Test
    public void testSignUpUser() throws Exception {

    }

    @Test
    public void testGetTask() throws Exception {

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

    }
}