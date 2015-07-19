package ru.fsep.enterprise.fseper.controllers.converters;

import com.inspiresoftware.lib.dto.geda.adapter.BeanFactory;
import com.inspiresoftware.lib.dto.geda.adapter.ValueConverter;
import com.inspiresoftware.lib.dto.geda.assembler.Assembler;
import com.inspiresoftware.lib.dto.geda.assembler.DTOAssembler;
import ru.fsep.enterprise.fseper.controllers.dto.*;
import ru.fsep.enterprise.fseper.models.Post;
import ru.fsep.enterprise.fseper.models.User;

import java.net.URL;
import java.util.*;

/**
  Created by Fedorov on 16.07.2015
 */
public class UserConverterImpl implements UserConverter{

    private final static String INT_TO_STR_ADAPTER_NAME = "IntegerToString";
    private final String DOOBLE_TO_STR_ADAPTER_NAME = "DoubleToString";
    private final String URL_TO_STR_ADAPTER_NAME = "UrlToString";


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

    public UserDto fromUser(User entity) {
        return null;
    }

    public UsersDto fromUsers(List<User> entities) {
        return null;
    }

    public PostDto fromPost(Post entity) {
        PostDto postDto = new PostDto();
        Map<String, Object> adapters = new HashMap<String, Object>();
        adapters.put(INT_TO_STR_ADAPTER_NAME, integerToStringConverter);

        postAssembler.assembleDto(postDto, entity, adapters, null);

        return postDto;
    }

    public PostsDto fromPosts(List<Post> entities) {
        int i = 0;
        while (!entities.isEmpty()){
            PostDto postDto = new PostDto();
            Map<String, Object> adapters = new HashMap<String, Object>();
            adapters.put(INT_TO_STR_ADAPTER_NAME, integerToStringConverter);

            postAssembler.assembleDto(postDto, entities.get(i), adapters, null);
            i++;
        }
        return null;
    }
}
