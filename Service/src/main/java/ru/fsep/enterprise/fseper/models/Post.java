package ru.fsep.enterprise.fseper.models;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

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

    public Post(){};

    public Post(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("description", description)
                .toString();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, description);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Post other = (Post) obj;
        return Objects.equal(this.id, other.id)
                && Objects.equal(this.name, other.name)
                && Objects.equal(this.description, other.description);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
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
}