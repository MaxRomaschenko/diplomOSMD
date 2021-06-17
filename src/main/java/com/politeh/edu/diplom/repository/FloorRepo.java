package com.politeh.edu.diplom.repository;

import com.politeh.edu.diplom.model.Floor;
import com.politeh.edu.diplom.model.House;
import com.politeh.edu.diplom.services.FloorService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FloorRepo extends JpaRepository<Floor,Long> {
    Floor findByFloorNumber(Integer floorNum);
}
