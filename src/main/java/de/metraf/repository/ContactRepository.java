package de.metraf.repository;

import de.metraf.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

/**
 * Created by metraf on 04.06.17.
 */
@Async
@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>{
    Contact findByText(String text);
}
