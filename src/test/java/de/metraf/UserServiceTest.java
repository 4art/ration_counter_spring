package de.metraf;

import de.metraf.model.User;
import de.metraf.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.slf4j.Logger;

/**
 * Created by metraf on 06.05.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void checkUserRepository() throws Exception{
        assertThat(userService).isNotNull();
        User user = userService.findUserByEmail("firsov.tyoma@gmail.com");
//        System.out.println(user.toString());
//        System.out.println("Email: " + user.getEmail() + ", password: " + user.getPassword());
        logger.debug("Email: " + user.getEmail() + ", password: " + user.getPassword());
    }
}
