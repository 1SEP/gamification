package ru.fsep.enterprise.fseper.service.dao;

import ru.fsep.enterprise.fseper.models.Step;

import java.util.List;

public interface StepsDao {
    List<Step> getSteps(int taskId);
}
