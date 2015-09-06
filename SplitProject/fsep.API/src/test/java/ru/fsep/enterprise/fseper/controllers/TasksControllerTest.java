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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.fsep.enterprise.fseper.AppConfig;
import ru.fsep.enterprise.fseper.AppTestContext;
import ru.fsep.enterprise.fseper.service.exceptions.TaskNotFoundException;
import ru.fsep.enterprise.fseper.service.facades.TasksServiceFacade;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.fsep.enterprise.fseper.test.data.TestDataAPI.*;
import static ru.fsep.enterprise.fseper.test.data.TestDataCore.*;

/**
 * Created by Ôëþð on 20.07.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppTestContext.class, AppConfig.class})
@WebAppConfiguration
public class TasksControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private TasksServiceFacade tasksServiceFacade;

    @Autowired
    WebApplicationContext context;

    final ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception {
        Mockito.reset(tasksServiceFacade);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        when(tasksServiceFacade.getTask(Matchers.anyInt())).thenThrow(new TaskNotFoundException(5));
        doReturn(TASK_1).when(tasksServiceFacade).getTask(TASK_1.getId());
    }

    @Test
    public void testGetTask() throws Exception {
        mockMvc.perform(get("/tasks/{task-id}.json", TASK_1.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id", is(String.valueOf(TASK_1.getId()))))
                .andExpect(jsonPath("$.data.privated", is(String.valueOf(TASK_1.isPrivated()))))
                .andExpect(jsonPath("$.data.description", is(TASK_1.getDescription())))
                .andExpect(jsonPath("$.data.dueDate", is(String.valueOf(TASK_1.getDueDate()))))
                .andExpect(jsonPath("$.data.steps[0].id", is(String.valueOf(STEP_1_OF_TASK_2.getId()))))
                .andExpect(jsonPath("$.data.steps[0].taskId", is(String.valueOf(STEP_1_OF_TASK_2.getTaskId()))))
                .andExpect(jsonPath("$.data.steps[0].description", is(STEP_1_OF_TASK_2.getDescription())))
                .andExpect(jsonPath("$.data.steps[0].finished", is(String.valueOf(STEP_1_OF_TASK_2.isFinished()))))
                .andExpect(jsonPath("$.data.steps[1].id", is(String.valueOf(STEP_2_OF_TASK_1.getId()))))
                .andExpect(jsonPath("$.data.steps[1].taskId", is(String.valueOf(STEP_2_OF_TASK_1.getTaskId()))))
                .andExpect(jsonPath("$.data.steps[1].description", is(STEP_2_OF_TASK_1.getDescription())))
                .andExpect(jsonPath("$.data.steps[1].finished", is(String.valueOf(STEP_2_OF_TASK_1.isFinished()))))
                .andExpect(jsonPath("$.data.finished", is(String.valueOf(TASK_1.isFinished()))));
        verify(tasksServiceFacade).getTask(TASK_1.getId());
    }

    @Test
    public void testGetNotFoundTask() throws Exception {
        mockMvc.perform(get("/tasks/{task-id}.json", 5).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code", is("404")))
                .andExpect(jsonPath("$.status", is("error")))
                .andExpect(jsonPath("$.message", is("Task with id <" + 5 + "> not found")))
                .andExpect(jsonPath("$.data", is("TaskNotFoundException")));
    }

    @Test
    public void testUpdateTask() throws Exception {
        String json = mapper.writeValueAsString(TASK_DTO);
        mockMvc.perform(put("/tasks/{task-id}", TASK_DTO.getId())
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
    public void testRemoveTask() throws Exception {
        mockMvc.perform(delete("/tasks/{task-id}", TASK_1.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(tasksServiceFacade).removeTask(TASK_1.getId());
    }

    @Test
    public void testGetSteps() throws Exception {
        mockMvc.perform(get("/tasks/{task-id}/steps.json", TASK_1.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].id", is(String.valueOf(STEP_1_OF_TASK_2.getId()))))
                .andExpect(jsonPath("$.data[0].taskId", is(String.valueOf(STEP_1_OF_TASK_2.getTaskId()))))
                .andExpect(jsonPath("$.data[0].description", is(STEP_1_OF_TASK_2.getDescription())))
                .andExpect(jsonPath("$.data[0].finished", is(String.valueOf(STEP_1_OF_TASK_2.isFinished()))))
                .andExpect(jsonPath("$.data[1].id", is(String.valueOf(STEP_2_OF_TASK_1.getId()))))
                .andExpect(jsonPath("$.data[1].taskId", is(String.valueOf(STEP_2_OF_TASK_1.getTaskId()))))
                .andExpect(jsonPath("$.data[1].description", is(STEP_2_OF_TASK_1.getDescription())))
                .andExpect(jsonPath("$.data[1].finished", is(String.valueOf(STEP_2_OF_TASK_1.isFinished()))));
    }

    @Test
    public void testGetStep() throws Exception {
        mockMvc.perform(get("/tasks/{task-id}/steps/{step-id}.json", TASK_1.getId(), STEP_1_OF_TASK_2.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id", is(String.valueOf(STEP_1_OF_TASK_2.getId()))))
                .andExpect(jsonPath("$.data.taskId", is(String.valueOf(STEP_1_OF_TASK_2.getTaskId()))))
                .andExpect(jsonPath("$.data.description", is(STEP_1_OF_TASK_2.getDescription())))
                .andExpect(jsonPath("$.data.finished", is(String.valueOf(STEP_1_OF_TASK_2.isFinished()))));
    }

    @Test
    public void testGetStepsByFilter() throws Exception {
        mockMvc.perform(get("/tasks/{task-id}/steps.json/filter={finished}", TASK_1.getId(), STEP_1_OF_TASK_2.isFinished())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].id", is(String.valueOf(STEP_1_OF_TASK_2.getId()))))
                .andExpect(jsonPath("$.data[0].taskId", is(String.valueOf(STEP_1_OF_TASK_2.getTaskId()))))
                .andExpect(jsonPath("$.data[0].description", is(STEP_1_OF_TASK_2.getDescription())))
                .andExpect(jsonPath("$.data[0].finished", is(String.valueOf(STEP_1_OF_TASK_2.isFinished()))))
                .andExpect(jsonPath("$.data[1].id", is(String.valueOf(STEP_2_OF_TASK_1.getId()))))
                .andExpect(jsonPath("$.data[1].taskId", is(String.valueOf(STEP_2_OF_TASK_1.getTaskId()))))
                .andExpect(jsonPath("$.data[1].description", is(STEP_2_OF_TASK_1.getDescription())))
                .andExpect(jsonPath("$.data[1].finished", is(String.valueOf(STEP_2_OF_TASK_1.isFinished()))));
    }

    @Test
    public void testAddStep() throws Exception {
        String json = mapper.writeValueAsString(STEP_DTO);
        mockMvc.perform(post("/tasks/{task-id}/steps/assignments", TASK_1.getId())
                .content(json.getBytes())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.id", is(STEP_DTO.getId())))
                .andExpect(jsonPath("$.data.taskId", is(STEP_DTO.getTaskId())))
                .andExpect(jsonPath("$.data.description", is(STEP_DTO.getDescription())))
                .andExpect(jsonPath("$.data.finished", is(STEP_DTO.getFinished())));
    }

    @Test
    public void testUpdateStep() throws Exception {
        String json = mapper.writeValueAsString(STEP_DTO);
        mockMvc.perform(put("/tasks/{task-id}/steps", TASK_1.getId())
                .content(json.getBytes())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.id", is(STEP_DTO.getId())))
                .andExpect(jsonPath("$.data.taskId", is(STEP_DTO.getTaskId())))
                .andExpect(jsonPath("$.data.description", is(STEP_DTO.getDescription())))
                .andExpect(jsonPath("$.data.finished", is(STEP_DTO.getFinished())));
    }

    @Test
    public void testRemoveStep() throws Exception {
        mockMvc.perform(delete("/tasks/{task-id}/steps/{step-id}", TASK_1.getId(), STEP_1_OF_TASK_2.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}