package de.metraf.service;

import de.metraf.model.Ration;

import java.util.Collection;

/**
 * Created by metraf on 16.06.17.
 */
public interface RationService {
    Collection<Ration> findAll();
    Ration save(Ration ration);
    void delete(Long id);
    Ration findOne(Long id);

//    Collection<Ration> findByUserID(int id);
//    Collection<Ration> findByProductID();
}
