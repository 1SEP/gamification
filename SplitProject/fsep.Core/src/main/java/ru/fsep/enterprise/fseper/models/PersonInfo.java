package ru.fsep.enterprise.fseper.models;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.net.URL;
import java.util.List;

/**
 * 05.07.15
 * PersonInfo
 *
 * @author Marsel Sidikov, Ramil Fakhrutdinov (First Software Engineering Platform)
 * @version v1.0
 */

public class PersonInfo {

    private String firstName;

    private String lastName;

    private double rating;

    private String birthday;

    private Posts posts;

    private String role;

    private URL photo;

    public PersonInfo() {
    }

    public PersonInfo(String firstName, String lastName, double rating, String birthday, Posts posts, String role, URL photo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.rating = rating;
        this.birthday = birthday;
        this.posts = posts;
        this.role = role;
        this.photo = photo;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getRating() {
        return rating;
    }

    public String getBirthday() {
        return birthday;
    }

    public Posts getPosts() {
        return this.posts;
    }

    public String getRole() {
        return role;
    }

    public URL getPhoto() {
        return photo;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setPosts(Posts posts) {
        this.posts = posts;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPhoto(URL photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("firstName", firstName)
                .add("lastName", lastName)
                .add("rating", rating)
                .add("birthday", birthday)
                .add("posts", posts)
                .add("role", role)
                .add("photo", photo)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonInfo that = (PersonInfo) o;
        return Objects.equal(rating, that.rating) &&
                Objects.equal(firstName, that.firstName) &&
                Objects.equal(lastName, that.lastName) &&
                Objects.equal(birthday, that.birthday) &&
                Objects.equal(posts, that.posts) &&
                Objects.equal(role, that.role) &&
                Objects.equal(photo, that.photo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(firstName, lastName, rating, birthday, posts, role, photo);
    }
}
