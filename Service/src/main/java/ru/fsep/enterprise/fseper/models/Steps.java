package ru.fsep.enterprise.fseper.models;

import java.util.List;

/**
 * Created by Ôëşğ on 20.07.2015.
 */
public class Steps {
    List<Step> steps;

    public Steps(List<Step> steps) {
        this.steps = steps;
    }

    public List<Step> getSteps() {
        return steps;
    }
}
