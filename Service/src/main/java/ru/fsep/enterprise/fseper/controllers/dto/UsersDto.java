package ru.fsep.enterprise.fseper.controllers.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.Objects;
import com.inspiresoftware.lib.dto.geda.annotations.Dto;
import com.inspiresoftware.lib.dto.geda.annotations.DtoField;

import java.util.List;

/**
 * Created by Fedorov on 15.07.2015.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Dto
public class UsersDto {
    @Override
    public int hashCode() {
        return Objects.hashCode(Users);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final UsersDto other = (UsersDto) obj;
        return Objects.equal(this.Users, other.Users);
    }

    @DtoField

    private List<UserDto> Users;

    public List<UserDto> getUsers() {
        return Users;
    }

    public void setUsers(List<UserDto> users) {
        Users = users;
    }
}
