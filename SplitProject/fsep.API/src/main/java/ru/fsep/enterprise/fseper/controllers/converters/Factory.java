package ru.fsep.enterprise.fseper.controllers.converters;

import com.inspiresoftware.lib.dto.geda.adapter.BeanFactory;
import ru.fsep.enterprise.fseper.controllers.dto.StepDto;
import ru.fsep.enterprise.fseper.controllers.dto.StepsDto;

/**
 * Created by Ôëþð on 26.08.2015.
 */
public class Factory implements BeanFactory {

    public Class getClazz(String entityBeanKey) {
        if (entityBeanKey.equals("stepsBeanKey")) {
            return StepsDto.class;
        } else throw new IllegalArgumentException();
    }

    public Object get(String entityBeanKey) {
        if (entityBeanKey.equals("stepsBeanKey")){
            return new StepDto();
        } else throw new IllegalArgumentException();

    }
}
