package de.metraf.service;

import de.metraf.model.Product;

import java.util.Set;

/**
 * Created by metraf on 27.05.17.
 */
public interface ProductService {
    Set<Product> findAll();
    Product findOne(Long id);
    Product findByName(String name);
    void save(Product product);
    void delete(Long id);
}
