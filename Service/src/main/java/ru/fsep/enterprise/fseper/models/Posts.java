package ru.fsep.enterprise.fseper.models;

import java.util.List;

/**
 * Author Fedorov on 20.07.2015
 */
public class Posts {
    public List<Post> posts;

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
