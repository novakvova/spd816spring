package app.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable=false)
    @NotBlank(message = "Ім'я є обовзяковий")
    private String name;

    @Column(nullable=false)
    @NotBlank(message = "Емейл є обовзяковий")
    private String email;

    @Column(nullable=true)
    @NotBlank(message = "Пароль є обовзяковий")
    private String password;

    @ManyToMany(cascade=CascadeType.MERGE)
    @JoinTable(
            name="tblUserRoles",
            joinColumns={@JoinColumn(name="userId", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="roleId", referencedColumnName="id")})
    private List<Role> roles;

    public User() {
        roles = new ArrayList<Role>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
