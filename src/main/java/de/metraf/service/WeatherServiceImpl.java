package de.metraf.service;

import com.sun.istack.internal.Nullable;
import de.metraf.model.ApiKey;
import de.metraf.model.WeatherModern;
import de.metraf.model.weather.Weather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class WeatherServiceImpl implements WeatherService {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    ApiKeyService apiKeyService;
    private static final Logger logger = LoggerFactory.getLogger(WeatherServiceImpl.class);

    @Override
    public WeatherModern getWeatherModern(String city) {
        try {

            Weather weather = fetchDataFromAPI(city);

            return new WeatherModern(
                    weather.getLocation().getName(),
                    weather.getLocation().getRegion(),
                    weather.getLocation().getCountry(),
                    weather.getLocation().getLocaltime(),
                    weather.getWeatherCurrent().getTemp_c(),
                    weather.getWeatherCurrent().getFeelslike_c(),
                    weather.getWeatherCurrent().getCondition().getText(),
                    weather.getWeatherCurrent().getCondition().getIcon(),
                    weather.getWeatherCurrent().getWind_mph()
            );
        }catch (Exception e){
            logger.error(e.toString());
        }
        return null;
    }

    @Override
    public Weather getWeather(String city){
        try {

            return fetchDataFromAPI(city);
        }catch (Exception e){
            logger.error(e.toString());
        }
        return null;
    }
    private Weather fetchDataFromAPI(String city) {
        ApiKey apiKey = apiKeyService.findByName("weather");
        if(apiKey != null) {
            String url = String.format("%s?key=%s&q=%s", apiKey.getSite(), apiKey.getKey(), city);
            ResponseEntity<Weather> responseEntity = restTemplate.getForEntity(
                    url,
                    Weather.class
            );
            Weather weather = responseEntity.getBody();
            return weather;
        }
        return null;
    }
}
