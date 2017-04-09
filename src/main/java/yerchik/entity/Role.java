package yerchik.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Yerchik on 22.03.2017.
 */
@Entity
public class Role {
    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "role")
    private List<Account> accounts;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
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

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
