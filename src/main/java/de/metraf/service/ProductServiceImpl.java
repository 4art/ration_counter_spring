package de.metraf.service;

import de.metraf.model.Product;
import de.metraf.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
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
        Set<Product> sProducts = new HashSet<>();
        Collection<Product> products = productsRepository.findAll();
        for(Product p : products){
            sProducts.add(p);
        }
        return sProducts;
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
    public Product save(Product product) {
        Product savedProduct = productsRepository.save(product);
        return savedProduct;
    }

    @Override
    public void delete(Long id) {
        productsRepository.delete(id);
    }
}
