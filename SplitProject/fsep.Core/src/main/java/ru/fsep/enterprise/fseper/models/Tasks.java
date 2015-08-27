package ru.fsep.enterprise.fseper.models;

import java.util.List;

/**
 * Created by Ôëşğ on 26.08.2015.
 */
public class Tasks {

    List<Task> Tasks;

    public Tasks() {
    }

    public Tasks(List<Task> tasks) {
        Tasks = tasks;
    }

    public List<Task> getTasks() {
        return Tasks;
    }
}
