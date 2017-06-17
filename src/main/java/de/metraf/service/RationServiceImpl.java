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

//    @Override
//    public Collection<Ration> findByUserID(int userID) {
//        Collection<Ration> rations = findByUserID(userID);
//        return rations;
//    }
}

