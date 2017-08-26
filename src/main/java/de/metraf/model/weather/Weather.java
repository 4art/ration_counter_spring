package de.metraf.model.weather;


public class Weather {
    private WeatherLocation location;

    public Weather(WeatherLocation location) {
        this.location = location;
    }

    public Weather() {

    }

    public WeatherLocation getLocation() {
        return location;
    }

    public void setLocation(WeatherLocation location) {
        this.location = location;
    }
}
