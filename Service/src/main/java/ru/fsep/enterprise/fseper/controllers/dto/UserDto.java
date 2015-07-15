package ru.fsep.enterprise.controllers.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.inspiresoftware.lib.dto.geda.annotations.Dto;
import com.inspiresoftware.lib.dto.geda.annotations.DtoField;

/**
 * Created by Fedorov on 14.07.2015.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@Dto
public class UserDto implements DataTransferObject {

    @DtoField (converter = "IntegerToString")
    private String id;

    @DtoField
    private String firstName;

    @DtoField
    private String lastName;

    @DtoField (converter = "DoubleToString")
    private String rating;

    @DtoField
    private String birthday;

    @DtoField
    private String description;

    @DtoField
    private String name;

    @DtoField
    private String role;

    @DtoField (converter = "UrlToString")
    private String photo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
