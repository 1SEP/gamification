package ru.fsep.enterprise.fseper.controllers.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.inspiresoftware.lib.dto.geda.annotations.Dto;
import com.inspiresoftware.lib.dto.geda.annotations.DtoField;

/**
 * Created by Fedorov on 15.07.2015.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Dto
public class AuthDataDto {
    @DtoField
    private String login;
    @DtoField
    private String passwordHash;

    public AuthDataDto(String login, String passwordHash) {
        this.login = login;
        this.passwordHash = passwordHash;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}

