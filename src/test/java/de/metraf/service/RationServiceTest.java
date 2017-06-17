package de.metraf.service;

import de.metraf.model.Product;
import de.metraf.model.Ration;
import de.metraf.model.User;
import de.metraf.repository.ProductsRepository;
import de.metraf.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by metraf on 16.06.17.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RationServiceTest {
    @Autowired
    private RationService rationService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductsRepository productsRepository;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private Long id;
    @Before
    public void save() throws Exception {
        Ration ration = new Ration();
        Product product = getOneProduct();
        User user = getOneUser();
        ration.setUser_id(user.getId());
        ration.setProductID(product.getId());
        ration.setWeight(200.02);
        rationService.save(ration);
    }

    @Test
    public void findAll() throws Exception {
        Collection<Ration> rations = rationService.findAll();
        assertNotNull(rations);
        for(Ration r : rations){
            id = r.getId();
            break;
        }

    }

//    @Test
//    public void findByUserID(){
//        Collection<Ration> rations = rationService.findByUserID(2);
//        assertNotNull(rations);
//    }

    @After
    public void delete() throws Exception {
        rationService.delete(id);
        assertNull(rationService.findOne(id));
    }

    private User getOneUser() throws Exception {
        Collection<User> users = userRepository.findAll();
        User user = new User();
        for (User u : users) {
            user = u;
            break;
        }
        return user;
    }

    private Product getOneProduct() throws Exception {
        Collection<Product> products = productsRepository.findAll();
        Product product = new Product();
        for (Product p : products) {
            product = p;
            break;
        }
        return product;
    }
}