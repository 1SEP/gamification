package ru.fsep.enterprise.fseper.service.dao;

import ru.fsep.enterprise.fseper.models.Post;

import java.util.List;

/**
 * Created by Ôëþð on 09.07.2015.
 */
public interface PostsDao {
    void addPost(Post post, int userId);
    void removePost(int postId);
    void updatePost(Post post);
    List<Post> getPosts(int userId);
}
