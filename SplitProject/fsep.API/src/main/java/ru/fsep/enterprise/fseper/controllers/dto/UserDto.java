package ru.fsep.enterprise.fseper.controllers.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.inspiresoftware.lib.dto.geda.annotations.Dto;
import com.inspiresoftware.lib.dto.geda.annotations.DtoCollection;
import com.inspiresoftware.lib.dto.geda.annotations.DtoField;
import ru.fsep.enterprise.fseper.controllers.converters.Matcher;

import java.util.List;

/**
 * Author Fedorov Juriy on 14.07.2015
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@Dto
public class UserDto {

    @DtoField(converter = "IntegerToString")
    private String id;

    @DtoCollection(value = "tasks.tasks", readOnly = true, dtoToEntityMatcher = Matcher.class)
    private List<TaskDto> tasks;

    @DtoField (value = "instructions", converter = "PersonInfoToPersonInfoDto", readOnly = true)
    private PersonInfoDto PersonInfo;

    public UserDto() {
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("tasks", tasks)
                .add("PersonInfo", PersonInfo)
                .toString();
    }

    public UserDto(String id, List<TaskDto> tasks, PersonInfoDto personInfo) {
        this.id = id;
        this.tasks = tasks;
        this.PersonInfo = personInfo;
    }



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

    @Override
    public int hashCode() {
        return Objects.hashCode(id, tasks, PersonInfo);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final UserDto other = (UserDto) obj;
        return Objects.equal(this.id, other.id)
                && Objects.equal(this.tasks, other.tasks)
                && Objects.equal(this.PersonInfo, other.PersonInfo);
    }
}
