package de.metraf.controller;

import com.google.common.collect.ImmutableList;
import de.metraf.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import java.util.LinkedList;
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
    @Test
    public void getAllProducts() throws Exception {
        ResponseEntity<Set<Product>> responseEntity =
        restTemplate.exchange("/api/products", HttpMethod.GET, null,
                new ParameterizedTypeReference<Set<Product>>(){

                });
        Set<Product> actualList = responseEntity.getBody();
        List<String> productNames = actualList.stream()
                .map(product -> product.getName())
                .collect(collectingAndThen(toList(), ImmutableList::copyOf));
        productNames.forEach(System.out::println);
        assertNotNull(actualList);
        assertTrue(!actualList.isEmpty());
    }

//    @Test
//    public void getProductById() throws Exception {
//    }
//
//    @Test
//    public void saveProducts() throws Exception {
//    }
//
//    @Test
//    public void checkNewName() throws Exception {
//    }

}