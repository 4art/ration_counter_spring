package de.metraf.repository;

import de.metraf.model.Contact;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

/**
 * Created by metraf on 04.06.17.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ContactRepositoryTest {
    @Autowired
    private ContactRepository contactRepository;
    private String text = "blabla";

    @Before
    public void setData() throws Exception {
        Contact contact = new Contact();
        contact.setText(text);
        contact.setDateTime(LocalDateTime.now());
        contact.setEmail("firsov.tyoma@gmail.com");
        contact.setName("metraf");
        contactRepository.save(contact);
    }

    @Test
    public void checkData() throws Exception {
        Contact contact = contactRepository.findByText(text);
        assertNotNull(contact);
        assertEquals(text, contact.getText());
        System.out.println("Datetime is: " + contact.getDateTime());
    }

    @After
    public void deleteData() throws Exception {
        Contact contact = contactRepository.findByText(text);
        assertNotNull(contact);
        contactRepository.delete(contact.getId());
    }
}