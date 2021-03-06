package de.metraf.service;

import de.metraf.model.ApiKey;
import de.metraf.model.WeatherModern;
import de.metraf.model.weather.Weather;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

import static org.junit.Assert.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WeatherTest {
    @Autowired
    private ApiKeyService apiKeyService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WeatherService weatherService;

    @Test
    public void checkApiKeys() throws Exception {
        Collection<ApiKey> apiKeys = apiKeyService.findAll();
        assertNotNull(apiKeys);
        ApiKey apiKey = apiKeyService.findByName("weather");
        assertNotNull(apiKey);
        String site = String.format("%s?key=%s&q=%s", apiKey.getSite(), apiKey.getKey(), "Frankfurt");
        System.out.println(site);
    }

    @Test
    public void weatherServiceCheck() throws Exception{
        WeatherModern weatherModern = weatherService.getWeatherModern("Frankfurt");
        assertNotNull(weatherModern);
        System.out.println(weatherModern.getTemp());
    }

}
