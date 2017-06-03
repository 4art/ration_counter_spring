package de.metraf.model;

import java.util.Set;

import javax.persistence.*;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int id;
    @Column(name = "email")
    @Email(message = "*Korregieren Sie Bitte email")
    @NotEmpty(message = "*Das ist ein Pflichtfeld")
    private String email;
    @Column(name = "password")
    @Length(min = 5, message = "*Das Kennwort ist zu kurz")
    @NotEmpty(message = "*Das ist ein Pflichtfeld")
    private String password;
    @Transient
    private String confirmPassword;

    @Column(name = "name")
    @NotEmpty(message = "*Das ist ein Pflichtfeld")
    private String name;
    @Column(name = "active")
    private boolean active;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
    private Set<Role> roles;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        String result =
                "User [id='" + id + "', name='" + name + "', email='" + email + "', password='" + password + "']\n";
        if (roles != null) {
            result += "Roles [\n";
            for (Role r : roles) {
                result += "id='" + r.getId() + "', name='" + r.getRole() + "'\n";
            }
            result += "]";
        }
        return result;
    }
}
