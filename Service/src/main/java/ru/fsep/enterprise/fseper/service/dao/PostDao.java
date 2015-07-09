package ru.fsep.enterprise.fseper.service.dao;

import ru.fsep.enterprise.fseper.models.Post;

/**
 * Created by дыў№ on 09.07.2015.
 */
public interface PostDao {
    void addPost(Post post);
    void removePost(int postId);
}
