package ru.fsep.enterprise.fseper.models;

/**
 * 05.07.15
 * AuthData
 *
 * @author Marsel Sidikov, Ramil Fakhrutdinov (First Software Engineering Platform)
 * @version v1.0
 */

public class AuthData {

    private String passwordHash;

    private String login;

    public AuthData(String passwordHash, String login) {
        this.passwordHash = passwordHash;
        this.login = login;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getLogin() {
        return login;
    }

    // TODO : add equals, toString and hashCode implementations
}
