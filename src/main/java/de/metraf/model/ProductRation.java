package de.metraf.model;

public class ProductRation {
    private Long id;
    private String name;
    private double protein;
    private double fat;
    private double carbo;
    private double kcal;
    private double weight;

    public ProductRation(Long id, String name, double protein, double fat, double carbo, double kcal, double weight) {
        this.id = id;
        this.name = name;
        this.protein = protein;
        this.fat = fat;
        this.carbo = carbo;
        this.kcal = kcal;
        this.weight = weight;
    }

    public ProductRation() {
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

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
