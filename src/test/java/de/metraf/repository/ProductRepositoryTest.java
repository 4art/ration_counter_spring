package de.metraf.repository;

import de.metraf.model.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by metraf on 27.05.17.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductRepositoryTest {
    @Autowired
    private ProductsRepository productsRepository;
    private Product product;

    @Before
    public void setProduct() {
        product = productsRepository.findByName("kaki");
//        product = productsRepository.
    }

    @Test
    public void isNullProduct() throws Exception {
        assertNotNull(product);
        assertEquals(product.getName(), "kaki");
        System.out.println("name: " + product.getName() + " prot: " + product.getProtein());
    }

    @Test
    public void setTest() {
        Set<Product> sProducts = new HashSet<>();
        Collection<Product> products = productsRepository.findAll();
        for (Product p : products) {
            sProducts.add(p);
        }
        assertNotNull(products);
    }
}