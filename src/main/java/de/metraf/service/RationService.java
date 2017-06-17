package de.metraf.service;

import de.metraf.model.Ration;
import org.springframework.scheduling.annotation.Async;

import java.util.Collection;

/**
 * Created by metraf on 16.06.17.
 */
@Async
public interface RationService {
    Collection<Ration> findAll();
    Ration save(Ration ration);
    void delete(Long id);
    Ration findOne(Long id);
    Collection<Ration> findByUserID(int user_id);
    Collection<Ration> findByUserIDBetweenTimes(int user_id, String startDatetime, String endDateTime);
    Collection<Ration> findByProductID(Long productID);
}
