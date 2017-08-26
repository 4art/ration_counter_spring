package de.metraf.service;

import de.metraf.model.weather.Weather;
import de.metraf.model.weather.WeatherLocation;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@SpringBootTest
public class WeatherTest {
    RestTemplate restTemplate = new RestTemplate();
    @Test
    public void WeatherTest() throws Exception{
        Weather weather = fetchDataFromAPI();
        assertNotNull(weather);
        WeatherLocation location = weather.getLocation();
        assertNotNull(location);
    }

    private Weather fetchDataFromAPI(){
        ResponseEntity<Weather> responseEntity = restTemplate.getForEntity(
                "http://api.apixu.com/v1/current.json?key=b10896caa47947dba2c95319172608&q=Frankfurt",
                Weather.class
        );
        Weather weather = responseEntity.getBody();
        return weather;
    }
}
