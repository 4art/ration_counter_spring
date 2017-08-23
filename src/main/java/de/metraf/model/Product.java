package de.metraf.model;


import javax.persistence.*;

/**
 * Created by metraf on 27.05.17.
 */
@Entity
@Table(name = "products")
public class Product {
    @Id @GeneratedValue
    private Long id;
    @Column(name = "name", unique = true)
    private String name;
    private double protein;
    private double fat;
    private double carbo;
    private double kcal;

    public Product(String name, double protein, double fat, double carbo, double kcal) {
        this.name = name;
        this.protein = protein;
        this.fat = fat;
        this.carbo = carbo;
        this.kcal = kcal;
    }

    public Product(){}

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

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getCarbo() {
        return carbo;
    }

    public void setCarbo(double carbo) {
        this.carbo = carbo;
    }

    public double getKcal() {
        return kcal;
    }

    public void setKcal(double kcal) {
        this.kcal = kcal;
    }
}
