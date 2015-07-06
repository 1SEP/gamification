package ru.fsep.enterprise.fseper.models;

import com.google.common.base.MoreObjects;
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
    private List<Step> steps;
    private boolean finished;

    public Task(int id, boolean privated, String description, Date dueDate, List<Step> steps, boolean finished) {
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

    public boolean isPrivated() {
        return privated;
    }

    public String getDescription() {
        return description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public boolean isFinished() {
        return finished;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("privated", privated)
                .add("description", description)
                .add("dueDate", dueDate)
                .add("steps", steps)
                .add("finished", finished)
                .toString();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, privated, description, dueDate, steps, finished);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Task other = (Task) obj;
        return Objects.equal(this.id, other.id)
                && Objects.equal(this.privated, other.privated)
                && Objects.equal(this.description, other.description)
                && Objects.equal(this.dueDate, other.dueDate)
                && Objects.equal(this.steps, other.steps)
                && Objects.equal(this.finished, other.finished);
    }
    // TODO: make body by wiki
}
