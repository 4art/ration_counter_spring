package de.metraf.model.weather;

import de.metraf.model.weather.current.Current;
import lombok.Getter;

@Getter
public class Weather {
    private WeatherLocation location;
    private Current current;

    public Weather(WeatherLocation location, Current current) {
        this.location = location;
        this.current = current;
    }

    public Weather() {

    }

    public WeatherLocation getLocation() {
        return location;
    }

    public void setLocation(WeatherLocation location) {
        this.location = location;
    }

    public Current getWeatherCurrent() {
        return current;
    }

    public void setWeatherCurrent(Current current) {
        this.current = current;
    }
}
