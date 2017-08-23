package de.metraf.service;

import de.metraf.model.Contact;
import de.metraf.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by metraf on 04.06.17.
 */
@Service
public class ContactServiceImpl implements ContactService {

    private ContactRepository contactRepository;

    @Autowired
    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

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
        String[] dateTimeParts = LocalDateTime.now().toString().split("T");
        contact.setDateTime(dateTimeParts[0] + " " + dateTimeParts[1]);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactServiceImpl that = (ContactServiceImpl) o;

        return contactRepository != null ? contactRepository.equals(that.contactRepository) : that.contactRepository == null;
    }

    @Override
    public int hashCode() {
        return contactRepository != null ? contactRepository.hashCode() : 0;
    }
}
