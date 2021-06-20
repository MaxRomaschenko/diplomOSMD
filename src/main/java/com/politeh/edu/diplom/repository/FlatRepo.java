package com.politeh.edu.diplom.repository;

import com.politeh.edu.diplom.model.Flat;
import com.politeh.edu.diplom.model.Floor;
import com.politeh.edu.diplom.model.House;
import com.politeh.edu.diplom.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlatRepo extends JpaRepository<Flat,Long> {
    List<Flat> findAll();
    Flat findByBankBook(String bankBook);
    Flat findBySectionAndHouseAndFloor(House house, Section section,  Floor floor);
}
