package com.politeh.edu.diplom.repository;


import com.politeh.edu.diplom.model.Meter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeterRepo extends JpaRepository<Meter,Long> {

    @Query(value = "select * from meter " +
            "ORDER BY flat_id",nativeQuery = true)
    List<Meter> findByOrderByFlat();
}
