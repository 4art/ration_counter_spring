package de.metraf.repository;

import de.metraf.model.Ration;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by metraf on 15.06.17.
 */
public interface RationRepository extends JpaRepository<Ration, Long>{
}
