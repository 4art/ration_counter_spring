package de.metraf.service;

import de.metraf.model.Contact;
import de.metraf.repository.ContactRepository;
import org.hamcrest.core.Is;
import org.hibernate.validator.constraints.br.TituloEleitoral;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by metraf on 04.06.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class ContactServiceImplTest {
    private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private ContactServiceImpl contactService;

    @Before
    public void setData() throws Exception {
        List<Contact> contacts = new ArrayList<>();
        Contact contact = new Contact("bla", "name", "firsov.tyoma@gmail.com", getDateTimeNow());
        Contact contact1 = new Contact("bla-bla", "Name", "firsov.tyoma@yahoo.de", getDateTimeNow());
        contact.setId(1L);
        contact1.setId(2L);
        contacts.add(contact);
        contacts.add(contact);
        contacts.add(contact1);
        Mockito.when(contactRepository.findAll()).thenReturn(contacts);
        Mockito.when(contactRepository.findByText("bla")).thenReturn(contact);
        Mockito.when(contactRepository.findByText("bla-bla")).thenReturn(contact1);
    }

    @Test
    public void findAll() throws Exception {
        Set<Contact> contactSet = contactService.findAll();
        Mockito.verify(contactRepository).findAll();
        assertEquals(contactSet.size(), 2);
    }

    @Test
    public void findByText() throws Exception {
        Contact contact1 = contactService.findByText("bla");
        Mockito.verify(contactRepository).findByText("bla");
    }

    @Test
    public void localDatetime() throws Exception {
       assertNotNull(getDateTimeNow());
    }

    private String getDateTimeNow() {
        String[] dateTimeParts = LocalDateTime.now().toString().split("T");
        return dateTimeParts[0] + " " + dateTimeParts[1];
    }
}