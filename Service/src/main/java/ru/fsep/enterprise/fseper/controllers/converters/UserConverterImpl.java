package ru.fsep.enterprise.fseper.controllers.converters;

import com.inspiresoftware.lib.dto.geda.adapter.BeanFactory;
import com.inspiresoftware.lib.dto.geda.adapter.ValueConverter;
import com.inspiresoftware.lib.dto.geda.assembler.Assembler;
import com.inspiresoftware.lib.dto.geda.assembler.DTOAssembler;
import ru.fsep.enterprise.fseper.controllers.dto.*;
import ru.fsep.enterprise.fseper.models.PersonInfo;
import ru.fsep.enterprise.fseper.models.Post;
import ru.fsep.enterprise.fseper.models.User;

import java.net.URL;
import java.util.*;

/**
  Created by Fedorov on 16.07.2015
 */
public class UserConverterImpl implements UserConverter{

    private final static String INT_TO_STR_ADAPTER_NAME = "IntegerToString";
    private final static String DOOBLE_TO_STR_ADAPTER_NAME = "DoubleToString";
    private final static String URL_TO_STR_ADAPTER_NAME = "UrlToString";


    private final ValueConverter integerToStringConverter = new ValueConverter() {
        public Object convertToDto(Object o, BeanFactory beanFactory) {
            return String.valueOf(o);
        }

    public Object convertToEntity(Object o, Object o1, BeanFactory beanFactory) {
            return Integer.parseInt(o.toString());
        }
    };

    private final ValueConverter urlToStringConverter = new ValueConverter() {
        public Object convertToDto(Object o, BeanFactory beanFactory) {
            return String.valueOf(o);
        }

        public Object convertToEntity(Object o, Object o1, BeanFactory beanFactory) {
            // TODO
            return null;
        }
    };

    private final ValueConverter doubleToStringConverter = new ValueConverter() {
        public Object convertToDto(Object o, BeanFactory beanFactory) {
            return String.valueOf(o);
        }

        public Object convertToEntity(Object o, Object o1, BeanFactory beanFactory) {
            return Double.parseDouble((String) o);
        }
    };

    private final Assembler postAssembler = DTOAssembler.newAssembler(PostDto.class, Post.class);

    public PostDto fromPost(Post entity) {
        PostDto postDto = new PostDto();
        Map<String, Object> adapters = new HashMap<String, Object>();
        adapters.put(INT_TO_STR_ADAPTER_NAME, integerToStringConverter);

        postAssembler.assembleDto(postDto, entity, adapters, null);
        return postDto;
    }

    public PostsDto fromPosts(List<Post> entities) {
        List<PostDto> postsDto = new LinkedList<PostDto>();
        for (Post post : entities) {
            postsDto.add(fromPost(post));
        }
        PostsDto postsDtoOut = new PostsDto();
        postsDtoOut.setPosts(postsDto);
        return postsDtoOut;
    }

    public PersonInfoDto fromPersonInfo(PersonInfo entity){
        PersonInfoDto PIDto = new PersonInfoDto();
        Map<String, Object> adapter = new HashMap<String, Object>();
        adapter.put(DOOBLE_TO_STR_ADAPTER_NAME, doubleToStringConverter);
        adapter.put(URL_TO_STR_ADAPTER_NAME, urlToStringConverter);

        BeanFactory bean = new BeanFactory() {
            public Class getClazz(String s) {
                return null;
            }

            public Object get(String s) {
                return null;
            }
        };
        postAssembler.assembleDto(PIDto, entity, adapter, bean);
        PIDto.setPosts(fromPosts(entity.getPosts()));
        return PIDto;
    }

    public UserDto fromUser(User entity){
        UserDto userDto = new UserDto();
        userDto.setId(String.valueOf(entity.getId()));
        userDto.setPersonInfo(fromPersonInfo(entity.getPersonInfo()));
        ConverterOfTasksAndStepsEntitiesImpl converterForTask = new ConverterOfTasksAndStepsEntitiesImpl();
        userDto.setTasks(converterForTask.fromTasks(entity.getTasks()));
        return userDto;
    }

    public UsersDto fromUsers(List<User> entities){
        List<UserDto> usersDto = new LinkedList<UserDto>();
        for (User user : entities) {
            usersDto.add(fromUser(user));
        }
        UsersDto usersDtoOut = new UsersDto();
        usersDtoOut.setUsers(usersDto);
        return usersDtoOut;
    }

    public Post toPost(PostDto dto){
        Post post = new Post();
        Map<String, Object> adapter = new HashMap<String, Object>();

        return post;
    }

    public List<Post> toPosts(PostsDto dto){
        List<Post> listOfPost = new LinkedList<Post>();

        return null;
    }
}

