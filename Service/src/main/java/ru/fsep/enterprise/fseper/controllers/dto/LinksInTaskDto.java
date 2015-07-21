package ru.fsep.enterprise.fseper.controllers.dto;

import com.inspiresoftware.lib.dto.geda.annotations.Dto;

/**
 * Author Fedorov on 20.07.2015
 */

@Dto
public class LinksInTaskDto {
    private String instructions;

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}





