package de.metraf.service;

import de.metraf.model.Product;
import de.metraf.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by metraf on 27.05.17.
 */
@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductsRepository productsRepository;


    @Override
    public Set<Product> findAll() {
        return (Set<Product>) productsRepository.findAll();
    }

    @Override
    public Product findOne(Long id) {
        return productsRepository.findOne(id);
    }

    @Override
    public Product findByName(String name) {
        return productsRepository.findByName(name);
    }

    @Override
    public void save(Product product) {
        productsRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        productsRepository.delete(id);
    }
}
