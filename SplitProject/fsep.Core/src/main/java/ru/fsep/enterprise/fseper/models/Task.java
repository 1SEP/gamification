package ru.fsep.enterprise.fseper.models;

import com.google.common.base.Objects;

import java.util.Date;
import java.util.List;

/**
 * 05.07.15
 * Task
 *
 * @author Marsel Sidikov, Ramil Fakhrutdinov (First Software Engineering Platform)
 * @version v1.0
 */

public class Task {
    private int id;

    private boolean privated;

    private String description;

    private Date dueDate;

    private Steps steps;

    private boolean finished;

    public Task() {

    }

    public Task(int id, boolean privated, String description, Date dueDate, Steps steps, boolean finished) {
        this.id = id;
        this.privated = privated;
        this.description = description;
        this.dueDate = dueDate;
        this.steps = steps;
        this.finished = finished;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isPrivated() {
        return privated;
    }

    public void setPrivated(boolean privated) {
        this.privated = privated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Steps getSteps() {
        return steps;
    }

    public void setSteps(Steps steps) {
        this.steps = steps;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equal(id, task.id) &&
                Objects.equal(privated, task.privated) &&
                Objects.equal(finished, task.finished) &&
                Objects.equal(description, task.description) &&
                Objects.equal(dueDate, task.dueDate) &&
                Objects.equal(steps, task.steps);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, privated, description, dueDate, steps, finished);
    }
}
