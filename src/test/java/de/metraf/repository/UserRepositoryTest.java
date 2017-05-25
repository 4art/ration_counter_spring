package de.metraf.repository;

import de.metraf.model.Role;
import de.metraf.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by metraf on 21.05.17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void repositoryValid() {
        User user = userRepository.findByEmail("firsov.tyoma@yahoo.de");
        assertNotNull(user);
        assertNotNull(user.getRoles());
        Set<Role> roles = user.getRoles();
        for (Role r : roles) {
            System.out.println("id = " + r.getId() + ", role = " + r.getRole());
        }
//        System.out.println(roles.toString());
    }
}