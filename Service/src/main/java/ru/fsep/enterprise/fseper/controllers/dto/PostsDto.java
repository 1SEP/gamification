package ru.fsep.enterprise.fseper.controllers.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.inspiresoftware.lib.dto.geda.annotations.Dto;
import com.inspiresoftware.lib.dto.geda.annotations.DtoField;

import java.util.List;

/**
 * Created by Fedorov and Tiglev on 15.07.2015.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Dto
public class PostsDto {

    @DtoField(converter = "IntegerToString")
    private List<PostDto> posts;

    public List<PostDto> getPosts() {
        return posts;
    }

    public void setPosts(List<PostDto> posts) {
        this.posts = posts;
    }


}
