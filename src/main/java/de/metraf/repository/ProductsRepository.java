package de.metraf.repository;

import de.metraf.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by metraf on 27.05.17.
 */
@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);
}
