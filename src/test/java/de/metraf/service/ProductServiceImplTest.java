package de.metraf.service;

import de.metraf.model.Product;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by metraf on 27.05.17.
 */
public class ProductServiceImplTest {
    @Autowired
    private ProductService productService;
    @Test
    public void findAll() throws Exception {
        Set<Product> products = productService.findAll();
        assertNotNull(products);
        for(Product p : products){
            System.out.println(p.getId() + " " +p.getName());
        }
    }

    @Test
    public void findOne() throws Exception {
        Product product = productService.findOne(1L);
        assertNotNull(product);
        System.out.println(product.getId() + " " +product.getName());
    }

    @Test
    public void findByName() throws Exception {
        Product product = productService.findByName("kaki");
        assertNotNull(product);
        System.out.println(product.getId() + " " +product.getName());
    }

    @Test
    public void save() throws Exception {
    }

    @Test
    public void delete() throws Exception {
    }


}