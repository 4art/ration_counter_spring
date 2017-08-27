package de.metraf.service;

import de.metraf.model.ApiKey;
import de.metraf.model.WeatherModern;
import de.metraf.model.weather.Weather;

public interface WeatherService {
    WeatherModern getWeatherModern(String city);
    Weather getWeather(String city);
}
