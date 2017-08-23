package de.metraf.controller;

import com.google.common.collect.ImmutableList;
import de.metraf.model.Product;
import de.metraf.repository.RationRepository;
import de.metraf.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ApiControllerTest2 {
    @Mock
    private ProductService productService;

    @InjectMocks
    private ApiController apiController;

    private Product product;


    @Before
    public void setProductsList() {
        Set<Product> productsEmpty = new HashSet<>();
        Product product = new Product();
        product.setCarbo(12);
        product.setFat(12);
        product.setKcal(12);
        product.setName("bla");
        product.setId(1L);
        productsEmpty.add(product);

        //prepare
        when(productService.findAll()).thenReturn(productsEmpty);
    }

    @Test
    public void checkFindAll() throws Exception{

        Set<Product> products = productService.findAll();
        assertTrue(!products.isEmpty());
    }

    @Test
    public void getAllProducts() throws Exception {

        //testing
        ResponseEntity<Set<Product>> responseEntity = apiController.getAllProducts();
        //validate
        verify(productService).findAll();
    }

}