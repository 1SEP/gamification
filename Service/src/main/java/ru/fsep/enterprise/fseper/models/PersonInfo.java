package ru.fsep.enterprise.fseper.models;

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

    // TODO : add equals, toString and hashCode implementations
}
