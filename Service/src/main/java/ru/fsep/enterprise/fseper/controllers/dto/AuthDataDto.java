package ru.fsep.enterprise.fseper.controllers.dto;

import java.net.URL;

/**
 * Created by Fedorov on 15.07.2015.
 */
public class AuthDataDto {
    private String login;
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

