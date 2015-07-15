package ru.fsep.enterprise.fseper.controllers.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.inspiresoftware.lib.dto.geda.annotations.Dto;
import com.inspiresoftware.lib.dto.geda.annotations.DtoField;

/**
 * Created by Fedorov on 15.07.2015.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@Dto
public class PostDto {

    @DtoField(converter = "IntegerToString")
    private String id;

    @DtoField
    private String name;

    @DtoField
    private String description;
}
