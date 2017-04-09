package yerchik.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Yerchik on 20.03.2017.
 */
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String secondName;
    @Column(nullable = false, unique = true)
    private String login;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column
    private boolean enable;

    @ManyToOne
    private Role role;

    @OneToMany(mappedBy = "account")
    private List<Result> results;


    public Account() {
    }

    public Account(String name, String secondName, String login, String email, String password) {
        this.name = name;
        this.secondName = secondName;
        this.login = login;
        this.email = email;
        this.password = password;
        this.enable = false;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
