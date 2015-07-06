package ru.fsep.enterprise.fseper.models;

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

    // TODO: make body by wiki
}
