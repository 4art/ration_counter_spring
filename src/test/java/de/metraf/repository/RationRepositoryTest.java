package de.metraf.repository;

import de.metraf.model.Product;
import de.metraf.model.Ration;
import de.metraf.model.User;
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
 * Created by metraf on 15.06.17.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RationRepositoryTest {
    @Autowired
    private RationRepository rationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductsRepository productsRepository;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private Long id = 500L;
    private double weight = 200.00;

//    @Before
//    public void saveRation() throws Exception {
//        Ration ration = new Ration();
//        ration.setId(id);
//        ration.setProduct(getOneProduct());
//        ration.setUser(getOneUser());
//        ration.setWeight(weight);
//        rationRepository.save(ration);
//    }

    @Test
    public void checkRation() throws Exception {
        Collection<Ration> ration = rationRepository.findAll();
        assertNotNull(ration);
        for(Ration r : ration){

            logger.info(r.toString());
        }
    }

    @Test
    public void checkGetOne() throws Exception {
        Ration ration = rationRepository.findOne(2L);
        assertNotNull(ration);
    }

    @Test
    public void checkUsersAndProducts() throws Exception{
        assertNotNull(getOneUser());
        assertNotNull(getOneProduct());
    }

//    @After
//    private void deleteByID(){
//        rationRepository.delete(id);
////        assertNull(rationRepository.findOne(id));
//    }

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