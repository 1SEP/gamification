package ru.fsep.enterprise.fseper.controllers.dto;

import java.util.List;

/**
 * Created by Fedorov on 15.07.2015.
 */
public class UsersDto {
    private List<UserDto> Users;

    public List<UserDto> getUsers() {
        return Users;
    }

    public void setUsers(List<UserDto> users) {
        Users = users;
    }
}
