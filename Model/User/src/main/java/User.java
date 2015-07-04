import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by ramil on 03.07.2015.
 */
public class User {
    private int id;
    private String name;
    private String surname;
    private String patronoymic;
    private int age;
    private String post;
    private String role;
    private String birthday;
    private double ration;
    public ArrayList<Task> tasksPublic;
    private ArrayList<Task> tasksPrivate;
    private String login;
    private String password;
    private URL foto;
    private Security security = new Security();

    public int getId() {

        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronoymic() {
        return patronoymic;
    }

    public int getAge() {
        return age;
    }

    public String getPost() {
        return post;
    }

    public String getRole() {
        return role;
    }

    public double getRation() {
        return ration;
    }

    public ArrayList<Task> getTasksPrivate() {
        return tasksPrivate;
    }

    public String getBirthday() {
        return birthday;
    }

    public User(int id, String name, String surname, String patronoymic, int age, String post, String role, double ration, ArrayList<Task> tasksPublic, ArrayList<Task> tasksPrivate, String login, String password, URL foto) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronoymic = patronoymic;
        this.age = age;
        this.post = post;
        this.role = role;
        this.ration = ration;
        this.tasksPublic = tasksPublic;
        this.tasksPrivate = tasksPrivate;
        this.login = login;

        this.password = security.md5(password);
        this.foto = foto;
    }

    public ArrayList<Task> getTasksPublic() {
        return tasksPublic;
    }
    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public URL getFoto() {
        return foto;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("surname", surname)
                .toString();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, age);
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
                && Objects.equal(this.name, other.name)
                && Objects.equal(this.age, other.age);
    }
}
