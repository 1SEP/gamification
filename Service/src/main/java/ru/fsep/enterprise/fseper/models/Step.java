package ru.fsep.enterprise.fseper.models;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * 05.07.15
 * Step
 *
 * @author Marsel Sidikov, Ramil Fakhrutdinov (First Software Engineering Platform)
 * @version v1.0
 */

public class Step {
    private int id;

    private int taskId;

    private String description;

    private boolean finished;

    public Step() {

    }

    public Step(int id, int taskId, String description, boolean finished) {
        this.id = id;
        this.taskId = taskId;
        this.description = description;
        this.finished = finished;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        Step step = (Step) o;
        return Objects.equal(id, step.id) &&
                Objects.equal(taskId, step.taskId) &&
                Objects.equal(finished, step.finished) &&
                Objects.equal(description, step.description);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, taskId, description, finished);
    }
}
