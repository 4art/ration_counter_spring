package de.metraf.controller;

import com.google.common.collect.ImmutableList;
import de.metraf.model.Product;
import de.metraf.model.ProductRation;
import de.metraf.model.Ration;
import de.metraf.model.WeatherModern;
import de.metraf.service.RationService;
import de.metraf.service.RationServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Base64Utils;
import sun.misc.BASE64Encoder;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiControllerITest {
    @Autowired
    private org.springframework.boot.test.web.client.TestRestTemplate restTemplate;
    @Autowired
    RationService rationService;

    @Test
    public void getAllProducts() throws Exception {
        ResponseEntity<Set<Product>> responseEntity =
                restTemplate.exchange("/api/products", HttpMethod.GET, null,
                        new ParameterizedTypeReference<Set<Product>>() {

                        });
        Set<Product> actualList = responseEntity.getBody();
        List<String> productNames = actualList.stream()
                .map(product -> product.getName())
                .collect(collectingAndThen(toList(), ImmutableList::copyOf));
        //productNames.forEach(System.out::println);
        assertNotNull(actualList);
        assertTrue(!actualList.isEmpty());
        System.out.println(actualList);
    }

    @Test
    public void getWeatherByCity() throws Exception {
        String city = "Frankfurt";
        ResponseEntity<WeatherModern> weatherModernResponseEntity = restTemplate.exchange("/api/weather/Frankfurt", HttpMethod.GET, null, new ParameterizedTypeReference<WeatherModern>() {

        });
        WeatherModern weatherModern = weatherModernResponseEntity.getBody();
        assertNotNull(weatherModern);
        assertEquals(city, weatherModern.getName());
    }

    @Test
    public void checkFindAllRation() throws Exception{
        Collection<Ration> rations = rationService.findAll();
        assertNotNull(rations);
        Collection<ProductRation> productRations = rationService.getListProductRationToListRation(rationService.findAll());
        assertNotNull(productRations);
        assertTrue(!productRations.isEmpty());
    }
}
