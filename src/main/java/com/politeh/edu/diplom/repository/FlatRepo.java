package com.politeh.edu.diplom.repository;

import com.politeh.edu.diplom.model.Flat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlatRepo extends JpaRepository<Flat,Long> {
    List<Flat> findAll();
}
