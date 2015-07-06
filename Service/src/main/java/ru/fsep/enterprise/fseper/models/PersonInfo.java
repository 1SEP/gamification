package ru.fsep.enterprise.fseper.models;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.net.URL;

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

    private String post;

    private String role;

    private URL photo;

    public PersonInfo(String firstName, String lastName, double rating, String birthday, String post, String role,
                      URL photo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.rating = rating;
        this.birthday = birthday;
        this.post = post;
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

    public String getPost() {
        return post;
    }

    public String getRole() {
        return role;
    }

    public URL getPhoto() {
        return photo;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(firstName, lastName, rating, birthday, post, role, photo);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("firstName", firstName)
                .add("lastName", lastName)
                .add("rating", rating)
                .add("birthday", birthday)
                .add("post", post)
                .add("role", role)
                .add("photo", photo)
                .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final PersonInfo other = (PersonInfo) obj;
        return Objects.equal(this.firstName, other.firstName)
                && Objects.equal(this.lastName, other.lastName)
                && Objects.equal(this.rating, other.rating)
                && Objects.equal(this.birthday, other.birthday)
                && Objects.equal(this.post, other.post)
                && Objects.equal(this.role, other.role)
                && Objects.equal(this.photo, other.photo);
    }
// TODO : add equals, toString and hashCode implementations
}
