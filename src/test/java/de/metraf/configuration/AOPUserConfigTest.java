package de.metraf.configuration;

import de.metraf.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by metraf on 04.06.17.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AOPUserConfigTest {
    @Autowired
    private UserService userService;
    @Test
    public void serviceCheck() throws Exception{
        assertNotNull(userService);
    }
    @Test
    public void aroundController() throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        assertNotNull(auth);
    }

}