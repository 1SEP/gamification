package ru.fsep.enterprise.fseper.controllers.converters;

import ru.fsep.enterprise.fseper.controllers.dto.PersonInfoDto;
import ru.fsep.enterprise.fseper.controllers.dto.PostDto;
import ru.fsep.enterprise.fseper.controllers.dto.UserDto;
import ru.fsep.enterprise.fseper.controllers.dto.UsersDto;
import ru.fsep.enterprise.fseper.models.PersonInfo;
import ru.fsep.enterprise.fseper.models.Post;
import ru.fsep.enterprise.fseper.models.User;

import java.util.List;

/**
 * Created by Fedorov on 16.07.2015.
 */
public interface UserConverter {

    UserDto fromUser(User entity);
    UsersDto fromUsers(List<User> entities);
    PostDto fromPost(Post entity);
    List<PostDto>  fromPosts(List<Post> entities);
    PersonInfoDto fromPersonInfo(PersonInfo entity);
    User toUser(UserDto dto);
    List<User> toUsers(UsersDto dto);
    Post toPost(PostDto dto);
    List<Post> toPosts(List<PostDto> dtos);
    PersonInfo toPersonInfo (PersonInfoDto dto);
}
