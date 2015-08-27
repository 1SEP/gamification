package ru.fsep.enterprise.fseper.controllers.converters;

import com.inspiresoftware.lib.dto.geda.adapter.DtoToEntityMatcher;
import ru.fsep.enterprise.fseper.controllers.dto.StepDto;
import ru.fsep.enterprise.fseper.models.Step;

/**
 * Created by Ôëþð on 26.08.2015.
 */
public class Matcher implements DtoToEntityMatcher<StepDto, Step> {

    public boolean match(StepDto stepDto, Step step) {
        return true;
    }
}
