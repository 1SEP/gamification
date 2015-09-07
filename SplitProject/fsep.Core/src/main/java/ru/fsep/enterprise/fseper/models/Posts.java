package ru.fsep.enterprise.fseper.models;

import java.util.List;

/**
 * Author Fedorov Juriy on 02.09.2015
 */
public class Posts {
    private List<Post> posts;

    public Posts(List<Post> posts) {
        this.posts = posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Post> getPosts() {
        return posts;
    }
}
