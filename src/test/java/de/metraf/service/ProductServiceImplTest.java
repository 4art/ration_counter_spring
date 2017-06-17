package de.metraf.service;

import de.metraf.model.Product;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by metraf on 27.05.17.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductServiceImplTest {
    @Autowired
    private ProductService productService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Test
    public void findAll() throws Exception {
        Set<Product> products = productService.findAll();
        assertNotNull(products);
    }
    @Async
    @Test
    public void findOne() throws Exception {
        Product product = productService.findOne(1L);
        assertNotNull(product);
        logger.debug(product.getId() + " " +product.getName());
    }
    @Async
    @Test
    public void findByName() throws Exception {
        Product product = productService.findByName("bla");
        assertNotNull(product);
        logger.debug(product.getId() + " " +product.getName());
    }
    @Async
    @Before
    public void save() throws Exception {
        Product product = new Product();
        product.setCarbo(0.1);
        product.setFat(0.1);
        product.setKcal(0.1);
        product.setProtein(0.1);
        product.setName("bla");
        productService.save(product);
        assertNotNull(getIdByName("bla"));
    }

    @After
    public void delete() throws Exception {
        Product product = productService.findByName("bla");
        assertNotNull(product);
        productService.delete(product.getId());
    }

    private Long getIdByName(String name) throws Exception{
        Product product = productService.findByName(name);
        return product.getId();
    }


}