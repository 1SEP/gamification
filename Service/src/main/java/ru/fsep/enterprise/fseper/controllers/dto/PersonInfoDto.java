package ru.fsep.enterprise.fseper.controllers.dto;

import com.inspiresoftware.lib.dto.geda.annotations.DtoField;

/**
 * Created by Fedorov on 15.07.2015.
 */
public class PersonInfoDto {

    @DtoField
    private String firstName;

    @DtoField
    private String lastName;

    @DtoField (converter = "DoubleToString")
    private String rating;

    @DtoField
    private String birthday;

    @DtoField
    private String role;

    @DtoField (converter = "UrlToString")
    private String photo;


    @DtoField (value = "instruktions", converter = "PostsToPostsDto", readOnly = true)
    private   PostsDto posts;

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

    public PostsDto getPosts() {
        return posts;
    }

    public void setPosts(PostsDto posts) {
        this.posts = posts;
    }
}
