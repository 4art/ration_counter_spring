package de.metraf.service;

import de.metraf.model.ApiKey;
import de.metraf.model.weather.Weather;
import de.metraf.model.weather.WeatherLocation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WeatherTest {
    @Autowired
    private ApiKeyService apiKeyService;
//    @Autowired
    private RestTemplate restTemplate = new RestTemplate();


    @Test
    public void WeatherTest() throws Exception {
        Weather weather = fetchDataFromAPI();
        assertNotNull(weather);
        WeatherLocation location = weather.getLocation();
        assertNotNull(location);
    }

    private Weather fetchDataFromAPI() {
        ResponseEntity<Weather> responseEntity = restTemplate.getForEntity(
                "http://api.apixu.com/v1/current.json?key=b10896caa47947dba2c95319172608&q=Frankfurt",
                Weather.class
        );
        Weather weather = responseEntity.getBody();
        return weather;
    }

    @Test
    public void checkApiKeys() throws Exception {
        Collection<ApiKey> apiKeys = apiKeyService.findAll();
        assertNotNull(apiKeys);
    }

}
