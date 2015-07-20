package ru.fsep.enterprise.fseper.controllers.converters;
import com.inspiresoftware.lib.dto.geda.adapter.BeanFactory;
import ru.fsep.enterprise.fseper.controllers.dto.StepsDto;

/**
 * Created by Ôëþð on 20.07.2015.
 */
public class DtoBeanFactory  implements BeanFactory {
    public Class getClazz(final String dtoBeanKey) {
        if ("StepsDtoBeanKey".equals(dtoBeanKey))
        return StepsDto.class;
        throw new IllegalArgumentException("Don't mached key" + dtoBeanKey);
    }

    public Object get(final String dtoBeanKey) {
        if ("StepsDtoBeanKey".equals(dtoBeanKey))
        return new StepsDto();
        throw new IllegalArgumentException("don't find key" + dtoBeanKey);
    }
}
