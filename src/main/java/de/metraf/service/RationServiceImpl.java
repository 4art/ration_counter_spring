package de.metraf.service;

import de.metraf.model.Ration;
import de.metraf.repository.RationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * Created by metraf on 16.06.17.
 */
@Service
public class RationServiceImpl implements RationService {
    @Autowired
    private RationRepository rationRepository;
    @Override
    public Collection<Ration> findAll() {
        Collection<Ration> rations = rationRepository.findAll();
        return rations;
    }

    @Override
    public Ration save(Ration ration) {
        String[] dateTimeParts = LocalDateTime.now().toString().split("T");
        ration.setDatetime(dateTimeParts[0] + " " + dateTimeParts[1]);
        rationRepository.save(ration);
        return ration;
    }

    @Override
    public void delete(Long id) {
        rationRepository.delete(id);
    }
    @Override
    public Ration findOne(Long id) {
        return rationRepository.findOne(id);
    }

    @Override
    public Collection<Ration> findByUserID(int user_id) {
        return rationRepository.findByUserID(user_id);
    }

    @Override
    public Collection<Ration> findByUserIDBetweenTimes(int user_id, String startDatetime, String endDateTime) {

        return rationRepository.findByUserIDBetweenTimes(user_id, startDatetime, endDateTime);
    }

    @Override
    public Collection<Ration> findByProductID(Long productID) {
        return rationRepository.findByProductID(productID);
    }

}

