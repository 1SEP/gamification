package ru.fsep.enterprise.fseper.controllers.converters;

import com.inspiresoftware.lib.dto.geda.adapter.BeanFactory;
import ru.fsep.enterprise.fseper.controllers.dto.PostDto;

/**
 * Author Fedorov Juriy on 02.09.2015
 */
public class PostsFactory implements BeanFactory{
    public Class getClazz(String beanKey) {
        if (beanKey.equals("postsBeanKey")){
            return PostDto.class;
        } else throw new IllegalArgumentException();
    }

    public Object get(String beanKey) {
        if (beanKey.equals("postsBeanKey")){
            return new PostDto();
        } else throw new IllegalArgumentException();
    }
}
