package ru.fsep.enterprise.fseper.controllers.dto;

import java.util.List;

/**
 * Created by Fedorov on 15.07.2015.
 */
public class PostsDto {
    private List<PostDto> posts;

    public List<PostDto> getPosts() {
        return posts;
    }

    public void setPosts(List<PostDto> posts) {
        this.posts = posts;
    }
}
