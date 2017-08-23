package de.metraf.model;

import com.sun.org.apache.bcel.internal.generic.FALOAD;

import javax.persistence.*;

/**
 * Created by metraf on 15.06.17.
 */
@Entity(name = "ration")
public class Ration {
    @Id @GeneratedValue
    private Long id;
    @Column(name = "weight")
    private double weight;
    private int user_id;
    private Long productID;
    private String datetime;

    public Ration() {}

    public Ration(double weight, int user_id, Long productID, String datetime) {
        this.weight = weight;
        this.user_id = user_id;
        this.productID = productID;
        this.datetime = datetime;
    }


    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

}
