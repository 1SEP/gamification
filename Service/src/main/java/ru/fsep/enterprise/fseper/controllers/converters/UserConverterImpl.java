package ru.fsep.enterprise.fseper.controllers.converters;

import com.inspiresoftware.lib.dto.geda.adapter.BeanFactory;
import com.inspiresoftware.lib.dto.geda.adapter.ValueConverter;
import com.inspiresoftware.lib.dto.geda.assembler.Assembler;
import com.inspiresoftware.lib.dto.geda.assembler.DTOAssembler;
import ru.fsep.enterprise.fseper.controllers.dto.*;
import ru.fsep.enterprise.fseper.models.*;

import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Fedorov on 16.07.2015
 */
public class UserConverterImpl implements UserConverter {

    private final static String INT_TO_STR_ADAPTER_NAME = "IntegerToString";
    private final static String DOUBLE_TO_STR_ADAPTER_NAME = "DoubleToString";
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
            URL url = null;
            try{
                url = new URL((String) o);
            }catch (Exception e){
                System.out.println(e);
            }
            return url;
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

    public PersonInfoDto fromPersonInfo(PersonInfo entity) {
        PersonInfoDto PIDto = new PersonInfoDto();
        Map<String, Object> adapter = new HashMap<String, Object>();
        adapter.put(DOUBLE_TO_STR_ADAPTER_NAME, doubleToStringConverter);
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

    public UserDto fromUser(User entity) {
        UserDto userDto = new UserDto();
        userDto.setId(String.valueOf(entity.getId()));
        userDto.setPersonInfo(fromPersonInfo(entity.getPersonInfo()));
        TasksAndStepsConverterImpl converterForTask = new TasksAndStepsConverterImpl();
        userDto.setTasks(converterForTask.fromTasks(entity.getTasks()));
        return userDto;
    }

    public UsersDto fromUsers(List<User> entities) {
        List<UserDto> usersDto = new LinkedList<UserDto>();
        for (User user : entities) {
            usersDto.add(fromUser(user));
        }
        UsersDto usersDtoOut = new UsersDto();
        usersDtoOut.setUsers(usersDto);
        return usersDtoOut;
    }

    public Post toPost(PostDto dto) {
        Post post = new Post();
        Map<String, Object> adapter = new HashMap<String, Object>();
        adapter.put(INT_TO_STR_ADAPTER_NAME, integerToStringConverter);

        postAssembler.assembleEntity(dto, post, adapter, null);

        return post;
    }

<<<<<<< HEAD
    public List<Post> toPosts(PostsDto dtos) {
        List<Post> posts = new LinkedList<Post>();
=======
<<<<<<< HEAD
    public List<Post> toPosts(PostsDto dto) {
        List<Post> listOfPost = new LinkedList<Post>();
=======
    public Posts toPosts(PostsDto dtos) {
        Posts posts = new Posts();
        List<Post> listPost = new LinkedList<Post>();
>>>>>>> 18bdb940ec484cf804a753498a2d8341c4fdf7c4
        for (PostDto postDto : dtos.getPosts()) {
            posts.add(toPost(postDto));
        }
        return posts;
    }
>>>>>>> 4e21058cc50d10ec5273629fdd0ecbea9a334e2b

    public PersonInfo toPersonInfo (PersonInfoDto dto) {
        PersonInfo personInfo = new PersonInfo(dto.getFirstName(),
                dto.getLastName(),
                Double.parseDouble(dto.getRating()),
                dto.getBirthday(),
                toPosts(dto.getPosts()),
                dto.getRole(),
                (URL) urlToStringConverter.convertToEntity(dto.getPhoto(),URL_TO_STR_ADAPTER_NAME, null));
//        Map<String, Object> adapter = new HashMap<String, Object>();
//        adapter.put(DOUBLE_TO_STR_ADAPTER_NAME, doubleToStringConverter);
//        adapter.put(URL_TO_STR_ADAPTER_NAME, urlToStringConverter);
//
//        postAssembler.assembleEntity(dto, personInfo, adapter, null);

        return personInfo;
    }

    public User toUser (UserDto dto){

        return new User(integerToStringConverter.convertToEntity(dto.getId(), INT_TO_STR_ADAPTER_NAME, null),
                null,
                toPersonInfo(dto.getPersonInfo()),
                null);
    }
}

