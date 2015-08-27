package ru.fsep.enterprise.fseper.models;

import java.util.List;

/**
 * Created by Ôëşğ on 26.08.2015.
 */
public class Steps {

    private List<Step> steps;

    public Steps() {
    }

    public Steps(List<Step> steps) {
        this.steps = steps;
    }

    public List<Step> getSteps() {
        return steps;
    }
}
