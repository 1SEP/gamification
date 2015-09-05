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

    private Tasks tasks;

    public User() {
    }

    public User(int id, AuthData authData, PersonInfo personInfo, Tasks tasks) {
        this.id = id;
        this.authData = authData;
        this.personInfo = personInfo;
        this.tasks = tasks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AuthData getAuthData() {
        return authData;
    }

    public void setAuthData(AuthData authData) {
        this.authData = authData;
    }

    public PersonInfo getPersonInfo() {
        return personInfo;
    }

    public void setPersonInfo(PersonInfo personInfo) {
        this.personInfo = personInfo;
    }

    public Tasks getTasks() {
        return tasks;
    }

    public void setTasks(Tasks tasks) {
        this.tasks = tasks;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, authData, personInfo, tasks);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        return Objects.equal(this.id, other.id)
                && Objects.equal(this.authData, other.authData)
                && Objects.equal(this.personInfo, other.personInfo)
                && Objects.equal(this.tasks, other.tasks);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("authData", authData)
                .add("personInfo", personInfo)
                .add("tasks", tasks)
                .toString();
    }
}
