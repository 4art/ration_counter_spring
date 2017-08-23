package de.metraf.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by metraf on 04.06.17.
 */
@Entity
@Table(name = "contact")
public class Contact {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "text")
    @Length(min = 5, message = "*Das Text ist zu kurz")
    @NotEmpty(message = "*Das ist ein Pflichtfeld")
    private String text;
    @NotEmpty(message = "*Das ist ein Pflichtfeld")
    private String name;

    public Contact(String text, String name, String email, String dateTime) {
        this.text = text;
        this.name = name;
        this.email = email;
        this.dateTime = dateTime;
    }

    public Contact(){}

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

    @Column(name = "email")
    @Email(message = "*Korregieren Sie Bitte email")
    @NotEmpty(message = "*Das ist ein Pflichtfeld")
    private String email;

    private String dateTime;

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
