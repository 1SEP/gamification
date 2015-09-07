package ru.fsep.enterprise.fseper.controllers.converters;

import com.inspiresoftware.lib.dto.geda.adapter.BeanFactory;
import ru.fsep.enterprise.fseper.controllers.dto.PostDto;
import ru.fsep.enterprise.fseper.controllers.dto.PostsDto;

/**
 * Author Fedorov Juriy on 02.09.2015
 */
public class FactoryForPosts implements BeanFactory{
    public Class getClazz(String beanKey) {
        if (beanKey.equals("postsBeanKey")){
            return PostsDto.class;
        } else throw new IllegalArgumentException();
    }

    public Object get(String BeanKey) {
        if (BeanKey.equals("postsBeanKey")){
            return new PostDto();
        } else throw new IllegalArgumentException();
    }
}
