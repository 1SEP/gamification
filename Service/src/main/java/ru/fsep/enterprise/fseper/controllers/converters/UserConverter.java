package ru.fsep.enterprise.fseper.controllers.converters;

import org.springframework.stereotype.Component;
import ru.fsep.enterprise.fseper.controllers.dto.*;
import ru.fsep.enterprise.fseper.models.*;

import java.util.List;

/**
 * Created by Fedorov on 16.07.2015.
 */
public interface UserConverter {

    UserDto fromUser(User entity);
    UsersDto fromUsers(List<User> entities);
    PostDto fromPost(Post entity);
    PostsDto fromPosts(List<Post> entities);
    PersonInfoDto fromPersonInfo(PersonInfo entity);
    User toUser(UserDto dto);
    List<User> toUsers(UsersDto dto);
    Post toPost(PostDto dto);
    List<Post> toPosts(PostsDto dtos);
    PersonInfo toPersonInfo (PersonInfoDto dto);
}
