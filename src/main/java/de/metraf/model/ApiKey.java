package de.metraf.model;


import javax.persistence.*;

@Entity
public class ApiKey {
    @Id @GeneratedValue
    @Column(name = "id", unique = false)
    private Long id;
    @Column(name = "name", unique = false)
    private String name;
    @Column(name = "key", unique = false)
    private String key;
    @Column(name = "login", unique = false)
    private String login;
    @Column(name = "site", unique = false)
    private String site;

    public ApiKey() {
    }

    public ApiKey(String name, String key, String login, String site) {
        this.name = name;
        this.key = key;
        this.login = login;
        this.site = site;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
