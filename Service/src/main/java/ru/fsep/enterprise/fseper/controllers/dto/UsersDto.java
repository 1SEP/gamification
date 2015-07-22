package ru.fsep.enterprise.fseper.controllers.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.inspiresoftware.lib.dto.geda.annotations.Dto;
import com.inspiresoftware.lib.dto.geda.annotations.DtoField;

import java.util.List;

/**
 * Created by Fedorov on 15.07.2015.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Dto
public class UsersDto {
    @DtoField
    private List<UserDto> Users;

    public List<UserDto> getUsers() {
        return Users;
    }

    public void setUsers(List<UserDto> users) {
        Users = users;
    }
}
