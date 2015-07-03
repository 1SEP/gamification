import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by ramil on 03.07.2015.
 */
public class User {
    private int id;
    private String firstname;
    private String lastname;
    private String patronoymic; 
    private String post;
    private String role;
    private String birthday;
    private double rating;
    public ArrayList<Task> PublicTasks;
    private ArrayList<Task> PrivateTasks;
    private String login;
    private String passwordHesh;
    private URL foto;
    private Security security = new Security();

    public User(int id, String firstname, String lastname, String patronoymic, String post, String role, String birthday, double rating, ArrayList<Task> publicTasks, ArrayList<Task> privateTasks, String login, String passwordHesh, URL foto) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.patronoymic = patronoymic;
        this.post = post;
        this.role = role;
        this.birthday = birthday;
        this.rating = rating;
        PublicTasks = publicTasks;
        PrivateTasks = privateTasks;
        this.login = login;
        this.passwordHesh = security.md5(passwordHesh);
        this.foto = foto;
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPatronoymic() {
        return patronoymic;
    }


    public String getPost() {
        return post;
    }

    public String getRole() {
        return role;
    }

    public String getBirthday() {
        return birthday;
    }

    public double getRating() {
        return rating;
    }

    public ArrayList<Task> getPublicTasks() {
        return PublicTasks;
    }

    public ArrayList<Task> getPrivateTasks() {
        return PrivateTasks;
    }

    public String getLogin() {
        return login;
    }

    public String getpasswordHesh() {  //getPasswordHesh
        return passwordHesh;
    }

    public URL getFoto() {
        return foto;
    }

    public Security getSecurity() {
        return security;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, firstname, lastname, patronoymic, birthday);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("firstname", firstname)
                .add("lastname", lastname)
                .add("patronoymic", patronoymic)
                .add("post", post)
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
        final User other = (User) obj;
        return Objects.equal(this.id, other.id)
                && Objects.equal(this.firstname, other.firstname)
                && Objects.equal(this.lastname, other.lastname)
                && Objects.equal(this.patronoymic, other.patronoymic)
                && Objects.equal(this.birthday, other.birthday);
    }
}
