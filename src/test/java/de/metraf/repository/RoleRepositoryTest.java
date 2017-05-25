package de.metraf.repository;

import de.metraf.model.Role;
import de.metraf.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by metraf on 22.05.17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleRepositoryTest {
    @Autowired
    private RoleRepository roleRepository;

    private Role role;

    @Before
    public void roleInit(){
        role = roleRepository.findByRole("USER");
    }
    @Test
    public void checkRole(){
        assertNotNull(role);
    }

    @Test
    public void checkUsers(){
        Set<User> users = role.getUsers();
        assertNotNull(users);
        for(User u : users){
            System.out.println(u.getEmail() + " \n");
        }

    }

}