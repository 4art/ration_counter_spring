package de.metraf.model;

public class WeatherModern {
    private String name;
    private String region;
    private String country;
    private String localtime;
    private double temp;
    private double feelslike;
    private String condition;
    private String icon;
    private double wind;

    public WeatherModern() {
    }

    public WeatherModern(String name, String region, String country, String localtime, double temp, double feelslike, String condition, String icon, double wind) {
        this.name = name;
        this.region = region;
        this.country = country;
        this.localtime = localtime;
        this.temp = temp;
        this.feelslike = feelslike;
        this.condition = condition;
        this.icon = icon;
        this.wind = wind;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocaltime() {
        return localtime;
    }

    public void setLocaltime(String localtime) {
        this.localtime = localtime;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getFeelslike() {
        return feelslike;
    }

    public void setFeelslike(double feelslike) {
        this.feelslike = feelslike;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public double getWind() {
        return wind;
    }

    public void setWind(double wind) {
        this.wind = wind;
    }
}
