package ru.fsep.enterprise.fseper.controllers.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.inspiresoftware.lib.dto.geda.annotations.Dto;
import com.inspiresoftware.lib.dto.geda.annotations.DtoField;

import java.util.List;

/**
 * Author Fedorov Juriy on 14.07.2015
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@Dto
public class UserDto {

    @DtoField(converter = "IntegerToString")
    private String id;

    @DtoField (value = "instructions", converter = "TaskToTaskDto", readOnly = true)
    private List<TaskDto> tasks;

    @DtoField (value = "instructions", converter = "PersonInfoToPersonInfoDto", readOnly = true)
    private   PersonInfoDto PersonInfo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<TaskDto> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskDto> tasks) {
        this.tasks = tasks;
    }

    public PersonInfoDto getPersonInfo() {
        return PersonInfo;
    }

    public void setPersonInfo(PersonInfoDto personInfo) {
        PersonInfo = personInfo;
    }
}
