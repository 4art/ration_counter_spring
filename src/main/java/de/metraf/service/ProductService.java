package de.metraf.service;

import de.metraf.model.Product;
import org.springframework.scheduling.annotation.Async;

import java.util.Set;

/**
 * Created by metraf on 27.05.17.
 */
@Async
public interface ProductService {
    Set<Product> findAll();
    Product findOne(Long id);
    Product findByName(String name);
    Product save(Product product);
    void delete(Long id);
}
