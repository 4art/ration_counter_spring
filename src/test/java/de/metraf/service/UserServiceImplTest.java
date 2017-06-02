package de.metraf.service;

import de.metraf.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by metraf on 03.06.17.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceImplTest {
    @Autowired
    private UserService userService;

    @Test
    public void findByName() throws Exception {
        User user = userService.findByName("metraf");
        assertNotNull(user);
        assertEquals("metraf", user.getName());
    }

}