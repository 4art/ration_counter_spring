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

import java.time.LocalDateTime;
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
    private int user_id;
    private Long productID;
    @Before
    public void saveRation() throws Exception {
        Ration ration = new Ration();
        User user = getOneUser();
        Product product = getOneProduct();
        ration.setProductID(product.getId());
        ration.setUser_id(2);
        ration.setWeight(200.3);
        String[] dateTimeParts = LocalDateTime.now().toString().split("T");
        ration.setDatetime(dateTimeParts[0] + " " + dateTimeParts[1]);
        rationRepository.save(ration);
        user_id = ration.getUser_id();
        productID = ration.getProductID();
    }

    @Test
    public void checkRation() throws Exception {
        Collection<Ration> ration = rationRepository.findAll();
        assertNotNull(ration);
    }

    @Test
    public void findByProductID() throws Exception{
        Collection<Ration> rations = rationRepository.findByProductID(productID);
        logger.info(String.valueOf(rations.size()));
        assertNotNull(rations);
    }
    @Test
    public void findByUserID() throws Exception{
        Collection<Ration> rations = rationRepository.findByUserID(user_id);
        assertNotNull(rations);
    }

    @Test
    public void findByUserIDBetweenTimes() throws Exception{
        String[] dateTimeParts = LocalDateTime.now().toString().split("T");
        Collection<Ration> rations = rationRepository.findByUserIDBetweenTimes(user_id, "2017-01-01 00:00:00", dateTimeParts[1] + "23:59:59");
        logger.info(String.valueOf(rations.size()));
        for(Ration r : rations){
            logger.info(String.valueOf(r.getId()) + " " + r.getDatetime());
        }
        assertNotNull(rations);
    }


    @After
    public void removeRation(){
        Collection<Ration> rations = rationRepository.findAll();
        Ration ration = new Ration();
        for (Ration p : rations) {
            ration = p;
            break;
        }
        rationRepository.delete(ration.getId());
        assertNull(rationRepository.findOne(ration.getId()));
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