package ru.fsep.enterprise.fseper.models;

/**
 * 05.07.15
 * Post
 *
 * @author Marsel Sidikov, Ramil Fakhrutdinov (First Software Engineering Platform)
 * @version v1.0
 */

public class Post {

    private int id;

    private String name;

    private String description;

    public Post(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    // TODO : add equals, toString and hashCode implementations
}
