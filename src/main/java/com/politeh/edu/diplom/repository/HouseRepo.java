package com.politeh.edu.diplom.repository;

import com.politeh.edu.diplom.model.House;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepo extends JpaRepository<House,Long> {
}
