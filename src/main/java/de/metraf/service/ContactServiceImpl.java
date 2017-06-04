package de.metraf.service;

import de.metraf.model.Contact;
import de.metraf.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by metraf on 04.06.17.
 */
@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactRepository contactRepository;
    @Override
    public Set<Contact> findAll() {
        Collection<Contact> contacts = contactRepository.findAll();
        Set<Contact> contactSet = new HashSet<>();
        for(Contact contact : contacts){
            contactSet.add(contact);
        }
        return contactSet;
    }

    @Override
    public Contact findById(Long id) {
        return contactRepository.findOne(id);
    }

    @Override
    public Contact save(Contact contact) {
        Contact savedContact = contactRepository.save(contact);
        return savedContact;
    }

    @Override
    public Contact findByText(String text) {
        return contactRepository.findByText(text);
    }

    @Override
    public void delete(Long id) {
        contactRepository.delete(id);
    }
}
