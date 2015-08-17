package ru.fsep.enterprise.fseper.controllers.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.inspiresoftware.lib.dto.geda.annotations.Dto;
import com.inspiresoftware.lib.dto.geda.annotations.DtoField;
import ru.fsep.enterprise.fseper.models.Post;

import java.util.List;

/**
 * Created by Fedorov on 15.07.2015.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@Dto
public class PersonInfoDto {

    //@DtoField
    private String firstName;

    //@DtoField
    private String lastName;

    @DtoField (converter = "DoubleToString")
    private String rating;

    //@DtoField
    private String birthday;

    //@DtoField
    private String role;

    @Override
    public int hashCode() {
        return Objects.hashCode(firstName, lastName, rating, birthday, role, photo, posts);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final PersonInfoDto other = (PersonInfoDto) obj;
        return Objects.equal(this.firstName, other.firstName)
                && Objects.equal(this.lastName, other.lastName)
                && Objects.equal(this.rating, other.rating)
                && Objects.equal(this.birthday, other.birthday)
                && Objects.equal(this.role, other.role)
                && Objects.equal(this.photo, other.photo)
                && Objects.equal(this.posts, other.posts);
    }

    @DtoField (converter = "UrlToString")
    private String photo;

    //@DtoField (value = "instruktions", converter = "PostsToPostsDto", readOnly = true)
    private List<PostDto> posts;

    public PersonInfoDto() {
    }

    public PersonInfoDto(String firstName, String lastName, String rating, String birthday, String role, String photo, List<PostDto> posts) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.rating = rating;
        this.birthday = birthday;
        this.role = role;
        this.photo = photo;
        this.posts = posts;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<PostDto> getPosts() {
        return posts;
    }

    public void setPosts(List<PostDto> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("firstName", firstName)
                .add("lastName", lastName)
                .add("rating", rating)
                .add("birthday", birthday)
                .add("role", role)
                .add("photo", photo)
                .add("posts", posts)
                .toString();
    }
}
