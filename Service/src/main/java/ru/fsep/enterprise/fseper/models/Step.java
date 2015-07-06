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
    private int task_id;
    private String description;
    private boolean finished;

    public Step(int id, int task_id, String description, boolean finished) {
        this.id = id;
        this.task_id = task_id;
        this.description = description;
        this.finished = finished;
    }

    public int getId() {
        return id;
    }

    public int getTask_id() {
        return task_id;
    }

    public String getDescription() {
        return description;
    }

    public boolean isFinished() {
        return finished;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("task_id", task_id)
                .add("description", description)
                .add("finished", finished)
                .toString();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, task_id, description, finished);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Step other = (Step) obj;
        return Objects.equal(this.id, other.id)
                && Objects.equal(this.task_id, other.task_id)
                && Objects.equal(this.description, other.description)
                && Objects.equal(this.finished, other.finished);
    }
    // TODO: make body by wiki
}
