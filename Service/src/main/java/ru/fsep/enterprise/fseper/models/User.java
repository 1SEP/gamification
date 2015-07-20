package ru.fsep.enterprise.fseper.models;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.util.List;

/**
 * 05.07.15
 * User
 *
 * @author Marsel Sidikov, Ramil Fakhrutdinov (First Software Engineering Platform)
 * @version v1.0
 */

public class User {

    private int id;

    private AuthData authData;

    private PersonInfo personInfo;

    private List<Task> tasks;

    public User(Object id, AuthData authData, PersonInfo personInfo, List<Task> tasks) {
        this.id = id;
        this.authData = authData;
        this.personInfo = personInfo;
        this.tasks = tasks;
    }

    public int getId() {
        return id;
    }

    public AuthData getAuthData() {
        return authData;
    }

    public PersonInfo getPersonInfo() {
        return personInfo;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id, this.personInfo);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("Id", this.id)
                .add("PersonInfo", this.personInfo)
                .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        final User that = (User) obj;
        return Objects.equal(this.id, that.id)
                && Objects.equal(this.personInfo, that.personInfo);
    }
}
