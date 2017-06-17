package de.metraf.repository;

import de.metraf.model.Ration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Created by metraf on 15.06.17.
 */
@Async
@Repository
public interface RationRepository extends JpaRepository<Ration, Long>{
    @Query(nativeQuery = true, value = "SELECT * FROM ration WHERE user_id = :user_id ORDER BY datetime DESC")
    Collection<Ration> findByUserID(@Param("user_id") int user_id);

    @Query(nativeQuery = true, value = "SELECT * FROM ration WHERE user_id = :user_id AND datetime > :startDatetime AND datetime < :endDateTime ORDER BY datetime DESC")
    Collection<Ration> findByUserIDBetweenTimes(@Param("user_id") int user_id, @Param("startDatetime") String startDatetime, @Param("endDateTime") String endDateTime);
}
