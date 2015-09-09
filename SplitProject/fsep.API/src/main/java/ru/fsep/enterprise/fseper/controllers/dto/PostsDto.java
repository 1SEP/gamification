package ru.fsep.enterprise.fseper.controllers.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.Objects;
import com.inspiresoftware.lib.dto.geda.annotations.Dto;
import com.inspiresoftware.lib.dto.geda.annotations.DtoCollection;
import com.inspiresoftware.lib.dto.geda.annotations.DtoField;

import java.util.List;

/**
 * Created by Fedorov and Tiglev on 15.07.2015.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Dto
public class PostsDto {

    @DtoCollection()
    private List<PostDto> posts;

    public List<PostDto> getPosts() {
        return posts;
    }

    public PostsDto(List<PostDto> posts) {
        this.posts = posts;
    }

    public void setPosts(List<PostDto> posts) {
        this.posts = posts;
    }


    @Override
    public int hashCode() {
        return Objects.hashCode(posts);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final PostsDto other = (PostsDto) obj;
        return Objects.equal(this.posts, other.posts);
    }
}
