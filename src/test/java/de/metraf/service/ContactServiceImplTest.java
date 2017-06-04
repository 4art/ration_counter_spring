package de.metraf.service;

import de.metraf.model.Contact;
import de.metraf.repository.ContactRepository;
import org.hibernate.validator.constraints.br.TituloEleitoral;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by metraf on 04.06.17.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ContactServiceImplTest {
    private String text = "Some text";
    @Autowired
    private ContactService contactService;
    @Before
    public void add2DB() throws Exception {
            Contact contact = new Contact();
            contact.setText(text);
            contact.setName("metraf");
            contact.setEmail("firsov.tyoma@gmail.com");
            contact.setDateTime(LocalDateTime.now());
            contactService.save(contact);
    }
    @Test
    public void findAll() throws Exception {
        Set<Contact> contactSet = contactService.findAll();
        assertNotNull(contactSet);
    }

    @Test
    public void findByText() throws Exception {
        Contact contact1 = contactService.findByText(text);
        assertNotNull(contact1);
    }

    @Test
    public void findById() throws Exception{
        Contact contact = contactService.findByText(text);
        Contact contact1 = contactService.findById(contact.getId());
        assertEquals(contact.getId(), contact1.getId());
    }
    @After
    public void removeFromDB(){
        Contact contact = contactService.findByText(text);
        contactService.delete(contact.getId());
    }


}