package ru.fsep.enterprise.fseper.service.dao;

import ru.fsep.enterprise.fseper.models.Post;

import java.util.List;

/**
 * Created by дыў№ on 09.07.2015.
 */
public interface PostsDao {
    void addPost(Post post);
    void removePost(int postId);
    void updatePost(int postId);
    List<Post> getPosts();
}
