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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.fsep.enterprise.fseper.AppContext;
import ru.fsep.enterprise.fseper.AppTestContext;
import ru.fsep.enterprise.fseper.service.facades.UsersServiceFacade;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Author Fedorov Juriy on 11.08.2015
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppTestContext.class, AppContext.class})
@WebAppConfiguration
public class UsersControllerTest {
    @Autowired
    WebApplicationContext context;
    @Autowired
    UsersServiceFacade usersServiceFacade;
    private MockMvc mockMvc;
    @Before
    public void setUp() throws Exception {
        Mockito.reset(usersServiceFacade);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void testGetAllUsers() throws Exception {
        mockMvc.perform(get("/users/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}