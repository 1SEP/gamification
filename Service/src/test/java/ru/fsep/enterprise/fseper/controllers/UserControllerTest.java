package ru.fsep.enterprise.fseper.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mortbay.jetty.webapp.WebAppContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.fsep.enterprise.fseper.AppTestContext;
import ru.fsep.enterprise.fseper.TestData;
import ru.fsep.enterprise.fseper.models.User;
import ru.fsep.enterprise.fseper.service.facades.UsersServiceFacade;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Author Fedorov Juriy on 24.07.2015
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppTestContext.class, WebAppContext.class})
@WebAppConfiguration
public class UserControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private UsersServiceFacade usersServiceFacade;
    @Autowired
    WebApplicationContext context;

    @Before
    public void setUp() throws Exception {
        Mockito.reset(usersServiceFacade);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void testGetUserById() throws Exception {
        User user = TestData.USER;
        int userId = user.getId();
        when(usersServiceFacade.getUser(userId)).thenReturn(user);

        mockMvc.perform(get("/user/{user-id}", userId).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("&.data.id", is(1)));
        verify(usersServiceFacade).getTask(userId);
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

    }

    @Test
    public void testDeleteUserById() throws Exception {

    }
}