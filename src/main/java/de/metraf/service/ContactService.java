package de.metraf.service;

import de.metraf.model.Contact;

import java.util.Set;

/**
 * Created by metraf on 04.06.17.
 */
public interface ContactService {
    Set<Contact> findAll();
    Contact findById(Long id);
    Contact save(Contact contact);
    Contact findByText(String text);
    void delete(Long id);
}
