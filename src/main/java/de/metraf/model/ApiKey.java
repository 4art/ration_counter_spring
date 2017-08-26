package de.metraf.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "apiKeys")
public class ApiKey {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String key;
    private String login;
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
