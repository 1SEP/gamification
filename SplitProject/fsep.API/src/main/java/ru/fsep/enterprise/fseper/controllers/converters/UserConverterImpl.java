package ru.fsep.enterprise.fseper.controllers.converters;

import com.inspiresoftware.lib.dto.geda.adapter.BeanFactory;
import com.inspiresoftware.lib.dto.geda.adapter.ValueConverter;
import com.inspiresoftware.lib.dto.geda.assembler.Assembler;
import com.inspiresoftware.lib.dto.geda.assembler.DTOAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.fsep.enterprise.fseper.controllers.dto.PersonInfoDto;
import ru.fsep.enterprise.fseper.controllers.dto.PostDto;
import ru.fsep.enterprise.fseper.controllers.dto.UserDto;
import ru.fsep.enterprise.fseper.models.*;

import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Fedorov on 16.07.2015
 */
@Component
public class UserConverterImpl implements UserConverter {
    @Autowired
    TasksAndStepsConverter tasksAndStepsConverter;


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

    private final Assembler personInfoAssembler = DTOAssembler.newAssembler(PersonInfoDto.class, PersonInfo.class);

    public PersonInfoDto fromPersonInfo(PersonInfo entity) {
        PersonInfoDto PIDto = new PersonInfoDto();
        Map<String, Object> adapter = new HashMap<String, Object>();
        adapter.put(DOUBLE_TO_STR_ADAPTER_NAME, doubleToStringConverter);
        adapter.put(URL_TO_STR_ADAPTER_NAME, urlToStringConverter);
        adapter.put(INT_TO_STR_ADAPTER_NAME, integerToStringConverter);
        personInfoAssembler.assembleDto(PIDto, entity, adapter, new PostsFactory());
        return PIDto;
    }

    public UserDto fromUser(User entity) {
        UserDto user = new UserDto(String.valueOf(entity.getId()),
                tasksAndStepsConverter.fromTasks(entity.getTasks()),
                fromPersonInfo(entity.getPersonInfo()));
        return user;
    }

    public List<UserDto> fromUsers(List<User> entities){
        int sizeOfList = entities.size();
        List<UserDto> usersDto = new LinkedList<UserDto>();
        for (int i = 0; i < sizeOfList; i++){
            usersDto.add(fromUser(entities.get(i)));
        }
        return usersDto;
    }

    public PersonInfo toPersonInfo(PersonInfoDto dto){
        PersonInfo entity = new PersonInfo();
        Map<String, Object> adapter = new HashMap<String, Object>();
        adapter.put(DOUBLE_TO_STR_ADAPTER_NAME, doubleToStringConverter);
        adapter.put(URL_TO_STR_ADAPTER_NAME, urlToStringConverter);
        adapter.put(INT_TO_STR_ADAPTER_NAME, integerToStringConverter);
        personInfoAssembler.assembleEntity(dto, entity, adapter, new PostsFactory());
        Posts posts = new Posts(toPosts(dto.getPosts()));
        entity.setPosts(posts);
        return entity;
    }

    public List<Post> toPosts(List<PostDto> postsDto) {
        List<Post> Posts = new LinkedList<Post>();
        for (int i = 0; i < postsDto.size(); i++){
            Posts.add(new Post(Integer.parseInt(postsDto.get(i).getId()), postsDto.get(i).getName(), postsDto.get(i).getDescription()));
        }
        return Posts;
    }

    public User toUser(UserDto dto){
        Tasks tasks = new Tasks(tasksAndStepsConverter.toTasks(dto.getTasks()));
        User entity = new User(Integer.parseInt(dto.getId()), null, toPersonInfo(dto.getPersonInfo()), tasks);
        return entity;
    }



    public List<User> toUsers(List<UserDto> dto){
        int sizeOfList = dto.size();
        List<User> users = new LinkedList<User>();
        for (int i = 0; i < sizeOfList; i++){
            users.add(toUser(dto.get(i)));
        }
        return users;
    }
}

/*integerToStringConverter.convertToEntity(dto.getId(), INT_TO_STR_ADAPTER_NAME, null*/