package ru.fsep.enterprise.fseper.models;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

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

    public AuthData(){}

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

    @Override
    public int hashCode() {
        return Objects.hashCode(passwordHash, login);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final AuthData other = (AuthData) obj;
        return Objects.equal(this.passwordHash, other.passwordHash)
                && Objects.equal(this.login, other.login);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("passwordHash", passwordHash)
                .add("login", login)
                .toString();
    }
}
